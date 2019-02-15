package com.bestaone.aiwan.api.user.vo;

import java.util.Set;

public class AuthUserInfoVo {

    private Long userId;
    private String username;
    private String avator;
    private Set<String> access;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public Set<String> getAccess() {
        return access;
    }

    public void setAccess(Set<String> access) {
        this.access = access;
    }

}
