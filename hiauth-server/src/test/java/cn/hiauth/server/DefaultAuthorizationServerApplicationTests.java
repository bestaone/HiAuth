package cn.hiauth.server;

import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
public class DefaultAuthorizationServerApplicationTests {

    private static final String AUTHORIZATION_REQUEST = UriComponentsBuilder
            .fromPath("/oauth2/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", AuthServerTests.clientId)
            .queryParam("scope", AuthServerTests.scope)
            .queryParam("state", "some-state")
            .queryParam("redirect_uri", AuthServerTests.redirectUri)
            .toUriString();

    @Autowired
    private WebClient webClient;

    @BeforeEach
    public void setUp() {
        this.webClient.getOptions().setThrowExceptionOnScriptError(false);
        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
        this.webClient.getOptions().setRedirectEnabled(true);
        this.webClient.getCookieManager().clearCookies();    // log out
    }

//    @Test
//    public void whenLoginSuccessfulThenDisplayOk() throws IOException {
//        HtmlPage page = this.webClient.getPage("/");
//        assertLoginPage(page);
//        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        WebResponse signInResponse = signIn(page, "corpadmin", "123456").getWebResponse();
//        assertThat(signInResponse.getStatusCode()).isEqualTo(HttpStatus.OK.value());	// there is no "default" index page
//    }

//    @Test
//    public void whenLoginFailsThenDisplayBadCredentials() throws IOException {
//        this.webClient.getOptions().setRedirectEnabled(true);
//        HtmlPage page = this.webClient.getPage("/");
//        HtmlPage loginErrorPage = signIn(page, "corpadmin", "111111");
//        HtmlElement alert = loginErrorPage.querySelector(".error-box");
//        assertThat(alert.getTextContent()).isNotBlank();
//    }

//    @Test
//    public void whenNotLoggedInAndRequestingTokenThenRedirectsToLogin() throws IOException {
//        HtmlPage page = this.webClient.getPage(AUTHORIZATION_REQUEST);
//        assertLoginPage(page);
//    }

//    @Test
//    public void whenLoggingInAndRequestingTokenThenRedirectsToClientApplication() throws IOException {
//        // Log in
//        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        this.webClient.getOptions().setRedirectEnabled(false);
//        HtmlPage page = this.webClient.getPage("/");
//        signIn(page, "corpadmin", "111111");
//
//        // Request token
//        WebResponse response = this.webClient.getPage(AUTHORIZATION_REQUEST).getWebResponse();
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value());
//        String location = response.getResponseHeaderValue("location");
////        assertThat(location).startsWith(AuthServerTests.redirectUri);
////        assertThat(location).contains("code=");
//    }

//    private static <P extends Page> P signIn(HtmlPage page, String username, String password) throws IOException {
//        HtmlInput usernameInput = page.querySelector("input[name=\"username\"]");
//        HtmlInput passwordInput = page.querySelector("input[name=\"password\"]");
//        HtmlButton signInButton = page.querySelector("button.submit");
//        usernameInput.type(username);
//        passwordInput.type(password);
//        return signInButton.click();
//    }
//
//    private static void assertLoginPage(HtmlPage page) {
//        assertThat(page.getUrl().toString()).contains("/login");
//        HtmlInput csrfInput = page.querySelector("input[name=\"_csrf\"]");
//        HtmlInput clientIdInput = page.querySelector("input[name=\"clientId\"]");
//        HtmlInput formTokenInput = page.querySelector("input[name=\"formToken\"]");
//        HtmlInput usernameInput = page.querySelector("input[name=\"username\"]");
//        HtmlInput passwordInput = page.querySelector("input[name=\"password\"]");
//        HtmlButton signInButton = page.querySelector("button.submit");
//        assertThat(csrfInput).isNotNull();
//        assertThat(clientIdInput).isNotNull();
//        assertThat(formTokenInput).isNotNull();
//        assertThat(usernameInput).isNotNull();
//        assertThat(passwordInput).isNotNull();
//        assertThat(signInButton.getTextContent()).isEqualTo("登录");
//    }

}
