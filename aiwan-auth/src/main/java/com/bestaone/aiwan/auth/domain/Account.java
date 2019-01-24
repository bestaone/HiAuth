package com.bestaone.aiwan.auth.domain;

import com.bestaone.aiwan.auth.domain.enums.AccountType;

import java.util.Date;

public class Account {

    private Long id;
    private User user;
    private AccountType type;
    private String openid;
    private String accessToken;
    private String refreshToken;
    private Date expireTime;
    private Date createTime;
    private Date lastLoginTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
