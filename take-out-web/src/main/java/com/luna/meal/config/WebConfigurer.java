package com.luna.meal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luna
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 所有路径都被拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
            .excludePathPatterns( // 添加不拦截路径
                "/user/api/registerFace",
                "/user/api/faceLogin/**",
                "/user/api/logout",
                "/user/api/login",
                "/user/api/register",
                "/userLogin.html",
                "/adminLogin.html",
                "/userLogin.html",
                "/userFace.html",
                "/adminFace.html",
                "/userRegister.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.woff",
                "/**/*.jif",
                "/**/*.ttf");
    }
}
