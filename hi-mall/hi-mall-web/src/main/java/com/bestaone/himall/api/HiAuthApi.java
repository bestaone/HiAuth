package com.bestaone.himall.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;

public class HiAuthApi extends DefaultApi20  {

    private String accessTokenUri;
    private String authorizationBaseUri;
    private String revokeTokenUri;

    public HiAuthApi(String accessTokenUri, String authorizationBaseUri, String revokeTokenUri) {
        this.accessTokenUri = accessTokenUri;
        this.authorizationBaseUri = authorizationBaseUri;
        this.revokeTokenUri = revokeTokenUri;
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenJsonExtractor.instance();
    }

    /**
     * 添加appId跟appKey采用在http的请求body中添加
     * @return
     */
    @Override
    public ClientAuthentication getClientAuthentication() {
        return RequestBodyAuthenticationScheme.instance();
    }

    /**
     * 授权的token在http请求的body中传递
     * @return
     */
    @Override
    public BearerSignature getBearerSignature() {
        return BearerSignatureURIQueryParameter.instance();
    }

    @Override
    public String getRevokeTokenEndpoint() {
        return revokeTokenUri;
    }

    @Override
	public String getAccessTokenEndpoint() {
		return accessTokenUri;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return authorizationBaseUri;
	}

    @Override
    public AiAuthService createService(String apiKey, String apiSecret, String callback, String defaultScope, String responseType, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        return new AiAuthService(this, apiKey, apiSecret, callback, defaultScope, responseType, userAgent, httpClientConfig, httpClient);
    }

}
