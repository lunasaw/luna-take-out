package com.luna.meal.vo;

/**
 * @author luna
 * 2021/6/18
 */
public class UserVO {

    private String  username;

    private String  sessionKey;

    private Integer admin;

    public UserVO(String username, String sessionKey, Integer admin) {
        this.username = username;
        this.sessionKey = sessionKey;
        this.admin = admin;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
