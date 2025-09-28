package cn.hiauth.server;

import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.htmlunit.WebResponse;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.HtmlCheckBoxInput;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
public class DefaultAuthorizationServerConsentTests {

    private final String authorizationRequestUri = UriComponentsBuilder
            .fromPath("/oauth2/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", AuthServerTests.clientId)
            .queryParam("scope", AuthServerTests.scope)
            .queryParam("state", "state")
            .queryParam("redirect_uri", AuthServerTests.redirectUri)
            .toUriString();
    @Autowired
    private WebClient webClient;
    @MockitoBean
    private OAuth2AuthorizationConsentService authorizationConsentService;

    @BeforeEach
    public void setUp() {
        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        this.webClient.getOptions().setRedirectEnabled(true);
        this.webClient.getCookieManager().clearCookies();
        when(this.authorizationConsentService.findById(any(), any())).thenReturn(null);
    }

    @Test
    @WithMockUser("corpadmin")
    public void whenUserConsentsToAllScopesThenReturnAuthorizationCode() throws IOException {
        final HtmlPage consentPage = this.webClient.getPage(this.authorizationRequestUri);
        assertThat(consentPage.getTitleText()).isEqualTo("统一认证中心");
        List<HtmlCheckBoxInput> scopes = new ArrayList<>();
        consentPage.querySelectorAll("input[name='scope']").forEach(scope -> scopes.add((HtmlCheckBoxInput) scope));
        for (HtmlCheckBoxInput scope : scopes) {
            scope.click();
            scope.click();
        }
        List<String> scopeIds = new ArrayList<>();
        scopes.forEach(scope -> {
            assertThat(scope.isChecked()).isTrue();
            scopeIds.add(scope.getId());
        });
        assertThat(scopeIds).containsExactlyInAnyOrder("profile");
        DomElement submitConsentButton = consentPage.querySelector("button[id='submit-consent']");
        this.webClient.getOptions().setRedirectEnabled(false);
        WebResponse approveConsentResponse = submitConsentButton.click().getWebResponse();
        assertThat(approveConsentResponse.getStatusCode()).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value());
        String location = approveConsentResponse.getResponseHeaderValue("location");
        assertThat(location).startsWith(AuthServerTests.redirectUri);
        assertThat(location).contains("code=");
    }

//    @Test
//    @WithMockUser("corpadmin")
//    public void whenUserCancelsConsentThenReturnAccessDeniedError() throws IOException {
//        final HtmlPage consentPage = this.webClient.getPage(this.authorizationRequestUri);
//        assertThat(consentPage.getTitleText()).isEqualTo("统一认证中心");
//        DomElement cancelConsentButton = consentPage.querySelector("button[id='cancel-consent']");
//        this.webClient.getOptions().setRedirectEnabled(false);
//        //以后处理
//        WebResponse cancelConsentResponse = cancelConsentButton.click().getWebResponse();
//        assertThat(cancelConsentResponse.getStatusCode()).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value());
//        String location = cancelConsentResponse.getResponseHeaderValue("location");
//        assertThat(location).startsWith(AuthServerTests.redirectUri);
//        assertThat(location).contains("error=access_denied");
//    }

}
