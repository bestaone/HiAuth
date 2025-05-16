package cn.hiauth.client;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zgs
 */
@Data
public class SessionContext implements Serializable {

    private String clientName;
    private String cachePrefix;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expire;
    private Authentication auth;
    private HiAuthToken token;
    private Map<String, String> extend = new HashMap<>();

    public SessionContext(String clientName, String cachePrefix){
        this.clientName = clientName;
        this.cachePrefix = cachePrefix;
    }

}
