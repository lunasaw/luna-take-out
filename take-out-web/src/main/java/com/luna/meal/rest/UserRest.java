package com.luna.meal.rest;

import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.meal.admin.LoginService;
import com.luna.meal.constant.UserConstant;
import com.luna.meal.entity.User;
import com.luna.meal.req.LoginReq;
import com.luna.meal.req.RegisterReq;
import com.luna.meal.util.CookieUtils;
import com.luna.meal.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author luna
 * 2021/6/18
 */
@RestController
@RequestMapping("user/api")
public class UserRest {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResultDTO<UserVO> login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        UserVO userVO = loginService.login(loginReq.getUsername(), loginReq.getPassword());
        Cookie cookie = new Cookie(CookieUtils.SESSION_KEY_NAME, userVO.getSessionKey());
        cookie.setPath("/");
        if (Objects.equals(loginReq.getRememberPwd(), "on")) {
            cookie.setMaxAge(UserConstant.SESSION_TIME * UserConstant.SESSION_EXPIRED);
        } else {
            cookie.setMaxAge(UserConstant.SESSION_TIME);
        }
        response.addCookie(cookie);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userVO);
    }


    @GetMapping("/sysUser")
    public ResultDTO<User> sysUser(HttpServletRequest request) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
                loginService.sysUser(CookieUtils.getOneSessionKey(request)));
    }

    @PostMapping("/register")
    public ResultDTO<Boolean> register(@RequestBody RegisterReq registerReq) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, loginService.register(registerReq));
    }
}
