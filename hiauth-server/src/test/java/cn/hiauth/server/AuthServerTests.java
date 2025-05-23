/*
 * Copyright 2020-2022 the original author or authors.
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
package cn.hiauth.server;

import cn.hutool.core.codec.Base64;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
public class AuthServerTests {

//    @Autowired
//    MockMvc mvc;
//
//    @Autowired
//    private WebClient webClient;
//
//    @MockitoBean
//    private OAuth2AuthorizationConsentService authorizationConsentService;
//
//    public static final String redirectUri = "http://himall.hiauth.com/login/oauth2/code/hiauth-code";
//
//    public static final String clientId = "himall", scope = "openid profile", authorization = "Basic " + Base64.encode("himall:secret".getBytes());
//
//    private final String authorizationRequestUri = UriComponentsBuilder
//            .fromPath("/oauth2/authorize")
//            .queryParam("response_type", "code")
//            .queryParam("client_id", clientId)
//            .queryParam("scope", scope)
//            .queryParam("state", "state")
//            .queryParam("redirect_uri", this.redirectUri)
//            .toUriString();
//
//    @BeforeEach
//    public void setUp() {
//        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        this.webClient.getOptions().setRedirectEnabled(true);
//        this.webClient.getCookieManager().clearCookies();
//        when(this.authorizationConsentService.findById(any(), any())).thenReturn(null);
//    }
//
//    @Test
//    public void whenClientCredentialsSuccess() throws Exception {
//        String accessToken = getAccessTokenWithClientCredentia();
//        assertThat(accessToken).isNotBlank();
//    }
//
//    @Test
//    @WithMockUser("admin")
//    public void whenAuthorizationCodeSuccess() throws Exception {
//        String code = getCode();
//        assertThat(code).isNotBlank();
//        log.info("code：{}", code);
//        String at = getAccessTokenWithCode(code);
//        assertThat(at).isNotBlank();
//        log.info("accessToken：{}", at);
//    }
//
//    private String getCode() throws Exception {
//        final HtmlPage consentPage = this.webClient.getPage(this.authorizationRequestUri);
//        assertThat(consentPage.getTitleText()).isEqualTo("AUTH");
//
//        List<HtmlCheckBoxInput> scopes = new ArrayList<>();
//        consentPage.querySelectorAll("input[name='scope']").forEach(scope -> scopes.add((HtmlCheckBoxInput) scope));
//        for (HtmlCheckBoxInput scope : scopes) {
//            scope.click();
//        }
//
//        List<String> scopeIds = new ArrayList<>();
//        scopes.forEach(scope -> {
//            assertThat(scope.isChecked()).isTrue();
//            scopeIds.add(scope.getId());
//        });
////        assertThat(scopeIds).containsExactlyInAnyOrder("profile", "openid");
//        assertThat(scopeIds).containsExactlyInAnyOrder("profile");
//
//        DomElement submitConsentButton = consentPage.querySelector("button[id='submit-consent']");
//        this.webClient.getOptions().setRedirectEnabled(false);
//
//        WebResponse approveConsentResponse = submitConsentButton.click().getWebResponse();
//        assertThat(approveConsentResponse.getStatusCode()).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value());
//        String location = approveConsentResponse.getResponseHeaderValue("location");
//        assertThat(location).startsWith(this.redirectUri);
//        assertThat(location).contains("code=");
//
//        URIBuilder builder = new URIBuilder(location);
//        List<NameValuePair> params = builder.getQueryParams();
//        Optional<NameValuePair> opt = params.stream().filter(e -> e.getName().equals("code")).findFirst();
//        return opt.get().getValue();
//    }
//
//    private String getAccessTokenWithCode(String code) throws Exception {
//        MvcResult result = mvc.perform(
//                        post("/oauth2/token")
//                                .param("grant_type", "authorization_code")
//                                .param("code", code)
//                                .param("redirect_uri", redirectUri)
//                                .header("Authorization", authorization)
//                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andReturn();
//        String content = result.getResponse().getContentAsString();
//        log.debug("返回结果：{}", content);
//        String at = JsonPath.read(content, "$.access_token");
//        assertThat(at).isNotBlank();
//        return at;
//    }
//
//    private String getAccessTokenWithClientCredentia() throws Exception {
//        MvcResult result = mvc.perform(
//                        post("/oauth2/token")
//                                .param("grant_type", "client_credentials")
//                                .param("scope", "profile")
//                                .header("Authorization", authorization)
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andReturn();
//        String content = result.getResponse().getContentAsString();
//        log.debug("返回结果：{}", content);
//        String at = JsonPath.read(content, "$.access_token");
//        assertThat(at).isNotBlank();
//        return at;
//    }


}
