package com.bestaone.aiwan.crm.controller;

import com.bestaone.aiwan.crm.api.AiwanApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	private static final String PROFILE_URL = "http://localhost:9080/api/user/profile";
	private static final String GET_ORDER_URL = "http://localhost:9081/api/order";

	private static final String SESSION_KEY_ACCESS_TOKEN = "MY_ACCESS_TOKEN";

	/**
	 * 为防止CSRF跨站攻击，每次请求STATE的值应该不同，可以放入Session！
	 * 由于都是localhost测试，所以session无法保持，用一个固定值。
	 */
	private static final String STATE = "secret-rensanning";
	private static final String CLIENT_ID = "client";
	private static final String CLIENT_SECRET = "123456";
	private static final String CALLBACK_URL = "http://localhost:8081/callback";
	private static final String SCOPE = "read";
	private OAuth20Service aiwanApi = new ServiceBuilder(CLIENT_ID)
			.apiSecret(CLIENT_SECRET)
			.scope(SCOPE)
			.state(STATE)
			.callback(CALLBACK_URL)
			.build(AiwanApi.instance());

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public void signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.debug("signin");
	    logger.info("session id:{}", request.getSession().getId());
		String authorizationUrl = aiwanApi.getAuthorizationUrl();
		logger.info("redirectURL:{}", authorizationUrl);
		response.sendRedirect(authorizationUrl);
	}

	@GetMapping("/callback")
	public String callback(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "state", required = false) String state, HttpServletRequest request,
                           HttpServletResponse response, Model model) throws Exception {

		logger.debug("callback [code:{}],[state:{}],[sessionId:{}]", code, state, request.getSession().getId());
		
		if (STATE.equals(state)) {
			logger.info("State OK!");
		} else {
			logger.error("State NG!");
		}

		OAuth2AccessToken accessToken = aiwanApi.getAccessToken(code);
		request.getSession().setAttribute(SESSION_KEY_ACCESS_TOKEN, accessToken);

		getProfile(model, aiwanApi, accessToken);
		getMyOrder(model, aiwanApi, accessToken);

		return "profile";
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		logger.debug("profile");
		OAuth2AccessToken accessToken = (OAuth2AccessToken) request.getSession().getAttribute(SESSION_KEY_ACCESS_TOKEN);
		getProfile(model, aiwanApi, accessToken);
		getMyOrder(model, aiwanApi, accessToken);
		return "profile";
	}

	private void getProfile(Model model, final OAuth20Service aiwanApi, OAuth2AccessToken accessToken) throws Exception {
		OAuthRequest apiRequest = new OAuthRequest(Verb.GET, PROFILE_URL);
		aiwanApi.signRequest(accessToken, apiRequest);
		
		Response resourceResponse = aiwanApi.execute(apiRequest);
		
		logger.info("code:{}", resourceResponse.getCode());
		logger.info("message:{}", resourceResponse.getMessage());
		logger.info("body:{}", resourceResponse.getBody());

		JSONObject obj = new JSONObject(resourceResponse.getBody());
		logger.info("json:{}", obj.toString());
		JSONObject data = obj.getJSONObject("data");
		String id = data.getString("id");
		String name = data.getString("name");
		String email = data.getString("email");

		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("email", email);
	}

	private void getMyOrder(Model model, final OAuth20Service service, OAuth2AccessToken accessToken) throws Exception {
		OAuthRequest apiRequest = new OAuthRequest(Verb.GET, GET_ORDER_URL + "/1");
		aiwanApi.signRequest(accessToken, apiRequest);

		Response resourceResponse = aiwanApi.execute(apiRequest);

		logger.info("code:{}", resourceResponse.getCode());
		logger.info("message:{}", resourceResponse.getMessage());
		logger.info("body:{}", resourceResponse.getBody());

		JSONObject obj = new JSONObject(resourceResponse.getBody());
		logger.info("json:{}", obj.toString());
		JSONObject data = obj.getJSONObject("data");
		Long id = data.getLong("id");
		String title = data.getString("title");
		Float totalAmount = data.getBigDecimal("totalAmount").floatValue();

		model.addAttribute("orderId", id);
		model.addAttribute("orderTitle", title);
		model.addAttribute("orderTotalAmount", totalAmount);
	}

}
