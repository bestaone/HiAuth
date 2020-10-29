package com.bestaone.himall.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.nio.charset.Charset;
import java.util.Base64;

public class AiAuthService extends OAuth20Service {

    private Base64.Encoder encoder = Base64.getEncoder();

    public AiAuthService(DefaultApi20 api, String apiKey, String apiSecret, String callback, String scope, String responseType, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        super(api, apiKey, apiSecret, callback, scope, responseType, userAgent, httpClientConfig, httpClient);
    }

	@Override
    protected OAuthRequest createAccessTokenRequest(AccessTokenRequestParams params) {
        final OAuthRequest request = new OAuthRequest(getApi().getAccessTokenVerb(), getApi().getAccessTokenEndpoint());
        request.addParameter(OAuthConstants.CLIENT_ID, this.getApiKey());
        final String apiSecret = this.getApiSecret();
        if (apiSecret != null) {
            request.addParameter(OAuthConstants.CLIENT_SECRET, apiSecret);
        }
        request.addParameter(OAuthConstants.CODE, params.getCode());
        request.addParameter(OAuthConstants.REDIRECT_URI, this.getCallback());
        final String scope = this.getDefaultScope();
        if (scope != null) {
            request.addParameter(OAuthConstants.SCOPE, scope);
        }
        String auth = encoder.encodeToString(String.format("%s:%s", this.getApiKey(), apiSecret).getBytes(Charset.forName("UTF-8")));
        request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
        request.addHeader(OAuthConstants.HEADER, OAuthConstants.BASIC + ' ' + auth);
        return request;
    }

}
