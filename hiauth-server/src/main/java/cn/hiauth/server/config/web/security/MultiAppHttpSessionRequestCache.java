package cn.hiauth.server.config.web.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.log.LogMessage;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

/**
 * 记录请求，重写缓存工具，实现需求：登录页如果携带了client_id，指定了具体的应用，则登录成功后跳转到指定应用
 */
public class MultiAppHttpSessionRequestCache extends HttpSessionRequestCache {

    public static final String CLIENT_ID_KEY = "clientId";

    static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";

    private PortResolver portResolver = new PortResolverImpl();

    private boolean createSessionAllowed = true;

    private RequestMatcher requestMatcher = AnyRequestMatcher.INSTANCE;

    private String sessionAttrName = SAVED_REQUEST;

    private String matchingRequestParameterName = "continue";

    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if (!this.requestMatcher.matches(request)) {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace(LogMessage.format("Did not save request since it did not match [%s]", this.requestMatcher));
            }
            return;
        }
        if (this.createSessionAllowed || request.getSession(false) != null) {
            DefaultSavedRequest savedRequest = new DefaultSavedRequest(request, this.portResolver, this.matchingRequestParameterName);
            request.getSession().setAttribute(this.sessionAttrName, savedRequest);
            // 自定义逻辑
            String clientId = request.getParameter(OAuth2ParameterNames.CLIENT_ID);
            if (StringUtils.hasText(clientId)) {
                request.getSession().setAttribute(this.sessionAttrName + "_" + clientId, savedRequest);
            }
            // 自定义逻辑
            if (this.logger.isDebugEnabled()) {
                this.logger.debug(LogMessage.format("Saved request %s to session", savedRequest.getRedirectUrl()));
            }
        } else {
            this.logger.trace("Did not save request since there's no session and createSessionAllowed is false");
        }
    }

    @Override
    public SavedRequest getRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        if (session == null) {
            return null;
        }
        // 自定义逻辑
        String clientId = currentRequest.getParameter(CLIENT_ID_KEY);
        if (StringUtils.hasText(clientId)) {
            return (SavedRequest) session.getAttribute(this.sessionAttrName + "_" + clientId);
        }
        // 自定义逻辑
        return (SavedRequest) session.getAttribute(this.sessionAttrName);
    }

    @Override
    public void removeRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        if (session != null) {
            this.logger.trace("Removing DefaultSavedRequest from session if present");
            session.removeAttribute(this.sessionAttrName);
            // 自定义逻辑
            String clientId = currentRequest.getParameter(CLIENT_ID_KEY);
            if (StringUtils.hasText(clientId)) {
                session.removeAttribute(this.sessionAttrName + "_" + clientId);
            }
            // 自定义逻辑
        }
    }

}
