package com.bestaone.aiwan.api.user.vo;

import com.bestaone.aiwan.api.utils.JsonLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Set;

public class AuthUserInfoVo {

    @JsonSerialize(using = JsonLongSerializer.class)
    private Long userId;
    private String username;
    private String name;
    private String avatar;
    private Set<String> roles;
    private String introduction;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
