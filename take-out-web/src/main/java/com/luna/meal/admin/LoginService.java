package com.luna.meal.admin;

import com.google.common.collect.ImmutableMap;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.encrypt.HashTools;
import com.luna.common.text.RandomStrUtil;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.entity.User;
import com.luna.meal.exception.UserException;
import com.luna.meal.mapper.UserMapper;
import com.luna.meal.req.RegisterReq;
import com.luna.meal.service.UserService;
import com.luna.meal.tools.UserTools;
import com.luna.meal.util.CookieUtils;
import com.luna.meal.util.Req2DOUtils;
import com.luna.meal.vo.UserVO;
import com.luna.redis.util.RedisHashUtil;
import com.luna.redis.util.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author luna
 * 2021/6/18
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper    userMapper;

    @Autowired
    private UserTools     userTools;

    @Autowired
    private RedisHashUtil redisHashUtil;

    @Autowired
    private RedisKeyUtil  redisKeyUtil;

    public UserVO login(String username, String password) {
        User user = userMapper.getByEntity(new User(username));
        if (user == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }

        String s = HashTools.md5(HashTools.md5(password));
        System.out.println(s);
        if (!StringUtils.equals(user.getPassword(), s)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "密码错误");
        }

        String nonceStrWithUUID = RandomStrUtil.generateNonceStrWithUUID();
        redisHashUtil.set(UserConstant.SESSION_KEY + nonceStrWithUUID,
            ImmutableMap.of(nonceStrWithUUID, user));
        redisKeyUtil.expire(UserConstant.SESSION_KEY + nonceStrWithUUID,
            UserConstant.SESSION_TIME * UserConstant.SESSION_EXPIRED,
            null);
        return new UserVO(username, nonceStrWithUUID, Integer.parseInt(user.getAdmin()));
    }

    public static void main(String[] args) {
        System.out.println(HashTools.md5(HashTools.md5("luna")));
    }

    public User sysUser(String sessionKey) {
        return userTools.getUser(sessionKey);
    }

    public boolean register(RegisterReq registerReq) {
        User user = Req2DOUtils.RegisterReq2UserDO(registerReq);
        if (userMapper.countByEntity(user) == 1) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户已经存在");
        }
        user.setPassword(HashTools.md5(HashTools.md5(registerReq.getPassword())));
        return userMapper.insert(user) == 1;
    }

    public Boolean editPassword(String sessionKey, String oldPassword, String newPassword) {
        User user = userTools.getUser(sessionKey);
        User byId = userMapper.getById(user.getId());

        if (!byId.getPassword().equals(HashTools.md5(HashTools.md5(oldPassword)))) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "密码错误");
        }

        byId.setPassword(HashTools.md5(HashTools.md5(newPassword)));
        return userMapper.update(byId) == 1;
    }
}
