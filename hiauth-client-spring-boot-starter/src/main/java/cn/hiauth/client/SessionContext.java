package cn.hiauth.client;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author zgs
 */
@Data
public class SessionContext implements Serializable {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime expire;
    private Authentication auth;
    private HiAuthToken token;
    private Map<String, String> extend;

}
