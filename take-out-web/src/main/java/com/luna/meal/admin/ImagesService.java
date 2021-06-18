package com.luna.meal.admin;

import com.luna.common.dto.constant.ResultCode;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.entity.User;
import com.luna.meal.exception.UserException;
import com.luna.meal.tools.UserTools;
import com.luna.meal.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.jvm.hotspot.asm.Register;

import java.io.IOException;
import java.util.Objects;

/**
 * @author luna
 * 2021/6/18
 */
@Service
public class ImagesService {

    @Autowired
    private UserTools userTools;

    /**
     * Nginx 文件服务器
     */
    @Value("${luna.nginx.path}")
    private String    path     = "http://127.0.0.1:8081";

    @Value("${luna.file.path}")
    private String    filePath = "/Users/luna/Document/project/meal";

    public String uploadImg(String sessionKey, MultipartFile file) {
        User doUser = userTools.getUser(sessionKey);
        if (!Objects.equals(UserConstant.ADMIN, doUser.getAdmin())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "鉴权异常");
        }
        try {
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            return path + fileName;
        } catch (IOException e) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "上传失败");
        }
    }
}
