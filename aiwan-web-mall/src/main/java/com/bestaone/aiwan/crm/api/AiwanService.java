package com.bestaone.aiwan.crm.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.services.Base64Encoder;

import java.nio.charset.Charset;

public class AiwanService extends OAuth20Service {

    public AiwanService(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }
    
	@Override
    protected OAuthRequest createAccessTokenRequest(String code) {
        final OAuthRequest request = new OAuthRequest(getApi().getAccessTokenVerb(), getApi().getAccessTokenEndpoint());
        final OAuthConfig config = getConfig();
        request.addParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
        final String apiSecret = config.getApiSecret();
        if (apiSecret != null) {
            request.addParameter(OAuthConstants.CLIENT_SECRET, apiSecret);
        }
        request.addParameter(OAuthConstants.CODE, code);
        request.addParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
        final String scope = config.getScope();
        if (scope != null) {
            request.addParameter(OAuthConstants.SCOPE, scope);
        }
        request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
        request.addHeader(OAuthConstants.HEADER,
                OAuthConstants.BASIC + ' '
                + Base64Encoder.getInstance()
                .encode(String.format("%s:%s", config.getApiKey(), apiSecret).getBytes(Charset.forName("UTF-8"))));
        return request;
    }
}
