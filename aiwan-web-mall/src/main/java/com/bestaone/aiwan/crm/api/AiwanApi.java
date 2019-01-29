package com.bestaone.aiwan.crm.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

public class AiwanApi extends DefaultApi20  {

    private String accessTokenEndpoint = "http://localhost:8080/oauth/token";
    private String authorizationBaseUrl = "http://localhost:8080/oauth/authorize";

    protected AiwanApi() {}

    private static class InstanceHolder {
        private static final AiwanApi INSTANCE = new AiwanApi();
    }

    public static AiwanApi instance() {
        return InstanceHolder.INSTANCE;
    }

	@Override
	public String getAccessTokenEndpoint() {
		return accessTokenEndpoint;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return authorizationBaseUrl;
	}
	
    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenJsonExtractor.instance();
    }

    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new AiwanService(this, config);
    }

}
