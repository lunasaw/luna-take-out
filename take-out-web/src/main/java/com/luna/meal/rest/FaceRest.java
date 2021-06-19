package com.luna.meal.rest;

import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.meal.admin.FaceService;
import com.luna.meal.admin.LoginService;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.util.CookieUtils;
import com.luna.meal.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luna@mac
 * 2021年05月07日 19:02
 */
@RestController
@RequestMapping("/user/api")
public class FaceRest {

    @Autowired
    private FaceService faceService;

    @PostMapping("/registerFace")
    public ResultDTO<Boolean> registerFace(HttpServletRequest request, String faceBase64) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            faceService.registerFace(CookieUtils.getOneSessionKey(request), faceBase64));
    }

    @PostMapping("/faceLogin/{site}")
    public ResultDTO<UserVO> checkFace(HttpServletResponse response, String faceBase64,
        @PathVariable(name = "site") String site) {
        UserVO userVO = faceService.login(faceBase64, site);
        Cookie cookie = new Cookie(CookieUtils.SESSION_KEY_NAME, userVO.getSessionKey());
        cookie.setPath("/");
        cookie.setMaxAge(UserConstant.SESSION_TIME);
        response.addCookie(cookie);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userVO);
    }

}
