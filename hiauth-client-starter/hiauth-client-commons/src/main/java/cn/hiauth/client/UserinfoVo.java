package cn.hiauth.client;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserinfoVo {

    private Long cid;
    private Long appId;
    private Long userId;
    private Long empId;
    private String name;
    private String username;
    private String phoneNum;
    private String avatarUrl;
    private List<String> authorities;
    private Boolean isCorpAdmin;

    public static UserinfoVo toVo(Authentication auth) {
        UserinfoVo vo = new UserinfoVo();
        vo.setCid(auth.getCid());
        vo.setAppId(auth.getAppId());
        vo.setUserId(auth.getUserId());
        vo.setEmpId(auth.getEmpId());
        vo.setName(auth.getName());
        vo.setUsername(auth.getUsername());
        vo.setPhoneNum(auth.getPhoneNum());
        vo.setAvatarUrl(auth.getAvatarUrl());
        if (auth.getAuthorities() != null && !auth.getAuthorities().isEmpty()) {
            vo.setAuthorities(new ArrayList<>());
            auth.getAuthorities().forEach(item -> vo.getAuthorities().add(item.get("code")));
        }
        vo.setIsCorpAdmin(auth.getIsCorpAdmin());
        return vo;
    }

}
