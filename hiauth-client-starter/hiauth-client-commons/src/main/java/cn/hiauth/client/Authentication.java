package cn.hiauth.client;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Authentication {

    private Long appId;
    private Long cid;
    private Long userId;
    private Long empId;
    private String name;
    private String username;
    private String phoneNum;
    private String avatarUrl;
    private List<Map<String, String>> authorities;
    private SecurityUser principal;

}
