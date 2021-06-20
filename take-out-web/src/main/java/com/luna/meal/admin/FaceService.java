package com.luna.meal.admin;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.luna.baidu.api.BaiduUserFaceApi;
import com.luna.baidu.config.BaiduProperties;
import com.luna.baidu.dto.face.UserFaceResultDTO;
import com.luna.baidu.dto.face.UserInfoListDTO;
import com.luna.baidu.dto.face.UserInfoResultDTO;
import com.luna.common.date.DateUtil;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.encrypt.HashUtils;
import com.luna.common.file.FileTools;
import com.luna.common.text.Base64Util;
import com.luna.common.text.RandomStrUtil;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.entity.User;
import com.luna.meal.exception.UserException;
import com.luna.meal.mapper.UserMapper;
import com.luna.meal.tools.UserTools;
import com.luna.meal.util.FileUploadUtils;
import com.luna.meal.vo.UserVO;
import com.luna.redis.util.RedisHashUtil;
import com.luna.redis.util.RedisKeyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luna@mac
 * 2021年05月07日 19:06
 */
@Service
public class FaceService {

    private static final Logger log       = LoggerFactory.getLogger(FaceService.class);
    /** 人员组名 */
    public static final String  LUNA_MEAL = "luna_meal";
    /** 控制检测分数 */
    public static final int     SCORE     = 90;

    @Autowired
    private UserMapper          userMapper;

    @Autowired
    private UserTools           userTools;

    @Autowired
    private RedisHashUtil       redisHashUtil;

    @Autowired
    private BaiduProperties     baiduProperties;

    @Autowired
    private RedisKeyUtil        redisKeyUtil;

    /**
     * Nginx 文件服务器
     */
    @Value("${luna.nginx.path}")
    private String              path      = "http://127.0.0.1:8081";

    @Value("${luna.file.path}")
    private String              filePath  = "/Users/luna/Document/project/meal";

    public Boolean registerFace(String sessionKey, String base64) {
        User user = userTools.getUser(sessionKey);
        User byId = userMapper.getById(user.getId());
        if (byId == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }
        String checkPath = getPath(base64, user);
        UserFaceResultDTO userFaceResultDTO = BaiduUserFaceApi.faceUserAdd(baiduProperties.getBaiduKey(),
            base64, "BASE64", LUNA_MEAL, byId.getId().toString(), byId.getAdmin());
        byId.setFaceData(userFaceResultDTO.getFaceToken());
        byId.setFacePath(path + checkPath);
        return userMapper.update(byId) == 1;
    }

    private String getPath(String base64, User user) {
        String checkPath = filePath + "/" + DateUtil.datePath() + "/"
            + HashUtils.md5(String.valueOf(user.getId())) + ".jpg";

        Path path = Paths.get(checkPath);

        if (!Files.isDirectory(path)) {
            path = path.getParent();
        }
        try {
            Files.createDirectories(path);
        } catch (IOException ignored) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, "保存人脸异常，请重试");
        }
        FileTools.write(Base64Util.decodeBase64(base64), checkPath);
        return checkPath;
    }

    /**
     * 人脸检查
     * 
     * @param base64 人脸信息
     * @param site 登陆站点
     * @return
     */
    public UserVO login(String base64, String site) {
        log.info("face login site={}", site);

        UserInfoListDTO userInfoListDTO =
            BaiduUserFaceApi.userFaceSearchWithBase64(baiduProperties.getBaiduKey(), base64, 5, LUNA_MEAL);

        if (userInfoListDTO == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "光线遮挡，请选择合适地点重试！");
        }

        List<UserInfoResultDTO> userList = userInfoListDTO.getUserList();
        String nonceStrWithUUID = RandomStrUtil.generateNonceStrWithUUID();

        // 存在对应站点，并且分数满足阀值
        List<UserInfoResultDTO> collect = userList.stream()
            .filter(userInfoResultDTO -> {
                long l = Long.parseLong(userInfoResultDTO.getUserId());
                User temp = userMapper.getById(l);
                return Objects.equals(temp.getAdmin(), site);
            }).filter(userInfoResultDTO -> userInfoResultDTO.getScore() > SCORE).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(collect)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "光线遮挡，未检测到人脸，请选择合适地点重试！");
        }

        Optional<UserInfoResultDTO> first = collect.stream().findFirst();

        if (!first.isPresent()) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "该角色权限未注册人脸，请登陆后注册人脸后重试");
        }

        UserInfoResultDTO userInfoResultDTO = first.get();
        User user = userMapper.getById(Long.parseLong(userInfoResultDTO.getUserId()));
        // 登陆图片
        String checkPath = getPath(base64, user);
        user.setFacePath(path + checkPath);
        userMapper.update(user);
        redisHashUtil.set(UserConstant.SESSION_KEY + nonceStrWithUUID,
            ImmutableMap.of(nonceStrWithUUID, user));
        redisKeyUtil.expire(UserConstant.SESSION_KEY + nonceStrWithUUID,
            UserConstant.SESSION_TIME * UserConstant.SESSION_EXPIRED);

        return new UserVO(user.getUsername(), nonceStrWithUUID, Integer.parseInt(user.getAdmin()));
    }
}
