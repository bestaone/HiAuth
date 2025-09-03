/*
 * Copyright 2020-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.hiauth.server.config.web.auth;

import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An {@link OAuth2TokenCustomizer} to map claims from a federated identity to
 * the {@code id_token} produced by this authorization server.
 *
 * @author Steve Riesenberg
 * @since 1.1
 */
public final class FederatedIdentityIdTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private static final Set<String> ID_TOKEN_CLAIMS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            IdTokenClaimNames.ISS,
            IdTokenClaimNames.SUB,
            IdTokenClaimNames.AUD,
            IdTokenClaimNames.EXP,
            IdTokenClaimNames.IAT,
            IdTokenClaimNames.AUTH_TIME,
            IdTokenClaimNames.NONCE,
            IdTokenClaimNames.ACR,
            IdTokenClaimNames.AMR,
            IdTokenClaimNames.AZP,
            IdTokenClaimNames.AT_HASH,
            IdTokenClaimNames.C_HASH
    )));

    @Override
    public void customize(JwtEncodingContext context) {
        if (context.getPrincipal().getPrincipal() instanceof AuthUser user) {
            String cid = context.getRegisteredClient().getClientSettings().getSetting("cid");
            JwtClaimsSet.Builder claims = context.getClaims();
            claims.claim("cid", Long.parseLong(cid));
            claims.claim("userId", user.getUserId());
            claims.claim("empId", user.getEmpId());
        }
//		if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
//			Map<String, Object> thirdPartyClaims = extractClaims(context.getPrincipal());
//			context.getClaims().claims(existingClaims -> {
//				// Remove conflicting claims set by this authorization server
//				existingClaims.keySet().forEach(thirdPartyClaims::remove);
//
//				// Remove standard id_token claims that could cause problems with clients
//				ID_TOKEN_CLAIMS.forEach(thirdPartyClaims::remove);
//
//				// Add all other claims directly to id_token
//				existingClaims.putAll(thirdPartyClaims);
//			});
//		}
    }

//	private Map<String, Object> extractClaims(Authentication principal) {
//		Map<String, Object> claims;
//		if (principal.getPrincipal() instanceof OidcUser oidcUser) {
//			OidcIdToken idToken = oidcUser.getIdToken();
//			claims = idToken.getClaims();
//		} else if (principal.getPrincipal() instanceof OAuth2User oauth2User) {
//			claims = oauth2User.getAttributes();
//		} else {
//			claims = Collections.emptyMap();
//		}
//		return new HashMap<>(claims);
//	}

}
