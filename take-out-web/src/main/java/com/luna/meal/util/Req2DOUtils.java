package com.luna.meal.util;

import com.luna.meal.entity.User;
import com.luna.meal.req.RegisterReq;

/**
 * @author luna
 * 2021/6/18
 */
public class Req2DOUtils {

    public static User RegisterReq2UserDO(RegisterReq registerReq) {
        if (registerReq == null) {
            return null;
        }

        User user = new User();
        user.setUsername(registerReq.getUsername());
        user.setPassword(registerReq.getPassword());
        user.setRealName(registerReq.getRealName());
        user.setEmail(registerReq.getEmail());
        user.setPhone(registerReq.getPhone());
        user.setAddress(registerReq.getAddress());
        user.setAdmin("1");
        return user;
    }
}
