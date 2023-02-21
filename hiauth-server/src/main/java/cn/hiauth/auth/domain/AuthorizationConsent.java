package cn.hiauth.auth.domain;

import lombok.Data;

@Data
public class AuthorizationConsent {

    private String registeredClientId;
    private String clientName;
    private String principalName;
    private String authorities;

}

