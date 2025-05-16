package cn.hiauth.client;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HiAuthToken {

    private String accessToken;
    private String refreshToken;
    private String scope;
    private LocalDateTime expire;

}
