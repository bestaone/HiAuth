package cn.hiauth.mgr.service.impl;

import cn.hiauth.mgr.common.IdGenerator;
import cn.hiauth.mgr.domain.RegisteredClient;
import cn.hiauth.mgr.mapper.RegisteredClientMapper;
import cn.hiauth.mgr.service.RegisteredClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisteredClientServiceImpl extends ServiceImpl<RegisteredClientMapper, RegisteredClient> implements RegisteredClientService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static String clientSettings = """
            {"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}
            """;

    private final static String tokenSettings = """
            {"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",7200.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000.000000000]}
            """;

    @Override
    public String create(RegisteredClient client) {

        String clientId = idGenerator.generate().toString();
        String clientSecret = UUID.randomUUID().toString().replaceAll("-","");
        String clientSecretEncode = passwordEncoder.encode(clientSecret);

        client.setClientId(clientId);
        client.setClientSecret(clientSecretEncode);
        client.setAuthorizationGrantTypes("refresh_token,client_credentials,password,authorization_code");
        client.setClientSettings(clientSettings);
        client.setTokenSettings(tokenSettings);
        this.save(client);

        return clientSecret;
    }

}
