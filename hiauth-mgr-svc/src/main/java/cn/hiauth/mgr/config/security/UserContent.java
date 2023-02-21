package cn.hiauth.mgr.config.security;

import lombok.Data;

import java.util.Set;

@Data
public class UserContent {

    private Long userId;
    private String username;
    private String phoneNum;
    private String avatarUrl;
    private Set<String> permissions;

}
