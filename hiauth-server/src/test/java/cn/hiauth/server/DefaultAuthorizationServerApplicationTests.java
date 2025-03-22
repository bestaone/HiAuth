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

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for the sample Authorization Server.
 *
 * @author Daniel Garnier-Moiroux
 */
@Slf4j
@Transactional
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
public class DefaultAuthorizationServerApplicationTests {

//    private static final String AUTHORIZATION_REQUEST = UriComponentsBuilder
//            .fromPath("/oauth2/authorize")
//            .queryParam("response_type", "code")
//            .queryParam("client_id", AuthServerTests.clientId)
//            .queryParam("scope", AuthServerTests.scope)
//            .queryParam("state", "some-state")
//            .queryParam("redirect_uri", AuthServerTests.redirectUri)
//            .toUriString();
//
//    @Autowired
//    private WebClient webClient;
//
//    @BeforeEach
//    public void setUp() {
//        this.webClient.getOptions().setThrowExceptionOnScriptError(false);
//        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
//        this.webClient.getOptions().setRedirectEnabled(true);
//        this.webClient.getCookieManager().clearCookies();	// log out
//    }
//
//    @Test
//    public void whenLoginSuccessfulThenDisplayOk() throws IOException {
//        HtmlPage page = this.webClient.getPage("/");
//
//        assertLoginPage(page);
//
//        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        WebResponse signInResponse = signIn(page, "pwd","admin", "123456").getWebResponse();
//        assertThat(signInResponse.getStatusCode()).isEqualTo(HttpStatus.OK.value());	// there is no "default" index page
//    }
//
////    @Test
//    public void whenLoginFailsThenDisplayBadCredentials() throws IOException {
//        HtmlPage page = this.webClient.getPage("/");
//
//        HtmlPage loginErrorPage = signIn(page, "pwd","admin", "11111");
//
//        HtmlElement alert = loginErrorPage.querySelector("small[class=\"help-block\"]");
//        assertThat(alert.getTextContent()).isNotBlank();
//    }
//
//    @Test
//    public void whenNotLoggedInAndRequestingTokenThenRedirectsToLogin() throws IOException {
//        HtmlPage page = this.webClient.getPage(AUTHORIZATION_REQUEST);
//
//        assertLoginPage(page);
//    }
//
//    @Test
//    public void whenLoggingInAndRequestingTokenThenRedirectsToClientApplication() throws IOException {
//        // Log in
//        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        this.webClient.getOptions().setRedirectEnabled(false);
//        signIn(this.webClient.getPage("/login"), "pwd", "admin", "123456");
//
//        // Request token
//        WebResponse response = this.webClient.getPage(AUTHORIZATION_REQUEST).getWebResponse();
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value());
//        String location = response.getResponseHeaderValue("location");
////        assertThat(location).startsWith(AuthServerTests.redirectUri);
////        assertThat(location).contains("code=");
//    }
//
//    private static <P extends Page> P signIn(HtmlPage page, String loginType, String username, String password) throws IOException {
//        HtmlInput loginTypeInput = page.querySelector("input[name=\"loginType\"]");
//        HtmlInput usernameInput = page.querySelector("input[name=\"account\"]");
//        HtmlInput passwordInput = page.querySelector("input[name=\"password\"]");
//        HtmlButton signInButton = page.querySelector("button.submit");
//
//        loginTypeInput.type(loginType);
//        usernameInput.type(username);
//        passwordInput.type(password);
//        return signInButton.click();
//    }
//
//    private static void assertLoginPage(HtmlPage page) {
//        assertThat(page.getUrl().toString()).endsWith("/login");
//
//        HtmlInput loginTypeInput = page.querySelector("input[name=\"loginType\"]");
//        HtmlInput usernameInput = page.querySelector("input[name=\"account\"]");
//        HtmlInput passwordInput = page.querySelector("input[name=\"password\"]");
//        HtmlButton signInButton = page.querySelector("button.submit");
//
//        assertThat(loginTypeInput).isNotNull();
//        assertThat(usernameInput).isNotNull();
//        assertThat(passwordInput).isNotNull();
//        assertThat(signInButton.getTextContent()).isEqualTo("登录");
//    }

}
