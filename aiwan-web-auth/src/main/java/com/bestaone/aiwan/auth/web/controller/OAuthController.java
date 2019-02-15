package com.bestaone.aiwan.auth.web.controller;

import com.bestaone.aiwan.auth.domain.enums.ResourceDomainType;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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

    @RequestMapping({ "/my_approval_page" })
    public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) {
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
        return "/oauth/oauth_approval";
    }

//    @RequestMapping({ "/my_approval_page" })
//    public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) {
//        @SuppressWarnings("unchecked")
//        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
//        List<String> scopeList = new ArrayList<>();
//        for (String scope : scopes.keySet()) {
//            scopeList.add(scope);
//        }
//        model.put("scopeList", scopeList);
//        return "/oauth/oauth_approval";
//    }

    @RequestMapping({ "/my_error_page" })
    public String handleError(Map<String, Object> model, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());
        } else {
            errorSummary = "Unknown error";
        }
        model.put("errorSummary", errorSummary);
        return "/oauth/oauth_error";
    }

    @RequestMapping({ "/login" })
    public String login(Map<String, Object> model, HttpServletRequest request) {
        return "forward:/oauth/my_approval_page";
    }

}