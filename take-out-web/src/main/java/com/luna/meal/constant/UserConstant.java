package com.luna.meal.constant;

/**
 * @author luna
 * 2021/6/18
 */
public interface UserConstant {

    String SESSION_KEY     = "luna:session:";

    /**
     * session过期时间，单位天
     */
    int    SESSION_TIME    = 24 * 60 * 60;

    int    SESSION_EXPIRED = 7;

    /** 管理员 */
    String ADMIN           = "0";

    /** 普通用户 */
    String USER            = "1";
}
