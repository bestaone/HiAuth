package com.bestaone.hiauth.controller;

import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.domain.App;
import com.bestaone.hiauth.domain.enums.ResourceDomainType;
import com.bestaone.hiauth.service.AppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
@SessionAttributes({ "authorizationRequest" })
public class OAuthController {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @Autowired
    AppService appService;

    @RequestMapping({ "/my_approval_page" })
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request, @RequestParam("client_id") String clientId) {

        if(StringUtils.isNotEmpty(clientId)){
            App app = appService.findByClientId(clientId);
            if(app!=null){
                model.put("appName", app.getName());
            }
        }

        @SuppressWarnings("unchecked")
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
        List<Map<String,String>> scopeList = new ArrayList<>();
        for (String scope : scopes.keySet()) {
            ResourceDomainType rdt = ResourceDomainType.valueOf(scope.replace("scope.",""));
            Map<String,String> scopMap = new HashMap<>();
            scopMap.put("name", scope);
            scopMap.put("text", rdt.getText());
            scopeList.add(scopMap);
        }
        model.put("scopeList", scopeList);
        return new ModelAndView("/oauth/oauth_approval");
    }

    @RequestMapping({ "/my_error_page" })
    public ModelAndView handleError(Map<String, Object> model, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());
        } else {
            errorSummary = "Unknown error";
        }
        model.put("errorSummary", errorSummary);
        return new ModelAndView("/oauth/oauth_error");
    }

    @RequestMapping({ "/login" })
    public String login(Map<String, Object> model, HttpServletRequest request) {
        return "forward:/oauth/my_approval_page";
    }

    @ResponseBody
    @RequestMapping("/revoke_token")
    public ApiResponse revokeToken(@RequestParam("token") String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return ApiResponse.sucess();
        } else {
            return ApiResponse.fail("注销失败");
        }
    }

}