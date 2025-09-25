package cn.hiauth.server.api.dto.login;

import lombok.Data;

@Data
public class CaptchaVerifyDto {

    private String token;
    private Integer startX;
    private Integer endX;
    private Long startTime;
    private Long endTime;

}
