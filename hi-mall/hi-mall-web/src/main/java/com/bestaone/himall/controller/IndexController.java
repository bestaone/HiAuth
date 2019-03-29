package com.bestaone.himall.controller;

import com.bestaone.himall.api.goods.GoodsApi;
import com.bestaone.himall.api.order.OrderApi;
import com.bestaone.himall.common.api.ApiResponse;
import com.bestaone.himall.common.api.PageVo;
import com.bestaone.himall.config.Oauth2Config;
import com.bestaone.himall.goods.domain.Goods;
import com.bestaone.himall.order.domain.Order;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class IndexController {

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Value("${security.oauth2.hiAuth.resource.userInfoUri:}")
	private String userInfoUri;

	@Value("${security.oauth2.hiAuth.client.revokeTokenUri:}")
	private String revokeTokenUri;

	private static final String SESSION_KEY_ACCESS_TOKEN = "MY_ACCESS_TOKEN";

	@Autowired
	private OAuth20Service hiAuthApi;

	@GetMapping("/")
	public String index() {
		return "/index";
	}

	@Autowired
	private GoodsApi goodsApi;

	@Autowired
	private OrderApi orderApi;

	@GetMapping("/signin")
	public void signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.debug("signin");
	    logger.info("session id:{}", request.getSession().getId());
		String authorizationUrl = hiAuthApi.getAuthorizationUrl();
		logger.info("redirectURL:{}", authorizationUrl);
		response.sendRedirect(authorizationUrl);
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws InterruptedException, ExecutionException, IOException {
		//注销access_token
		OAuth2AccessToken accessToken = (OAuth2AccessToken) request.getSession().getAttribute(SESSION_KEY_ACCESS_TOKEN);
		hiAuthApi.revokeToken(accessToken.getAccessToken());
		//清除session中保存的状态
		request.getSession().removeAttribute(SESSION_KEY_ACCESS_TOKEN);
		request.getSession().setAttribute("isAuth", false);
		return "/index";
	}

	@GetMapping("/callback")
	public String callback(@RequestParam(value = "code", required = false) String code,  @RequestParam(value = "state", required = false) String state, HttpServletRequest request, Model model) throws Exception {

		logger.debug("callback [code:{}],[state:{}],[sessionId:{}]", code, state, request.getSession().getId());
		if (Oauth2Config.STATE.equals(state)) {
			logger.info("State OK!");
		} else {
			logger.error("State NG!");
		}

		OAuth2AccessToken accessToken = hiAuthApi.getAccessToken(code);
		request.getSession().setAttribute(SESSION_KEY_ACCESS_TOKEN, accessToken);
		request.getSession().setAttribute("isAuth", true);

		getProfile(model, accessToken);
		getMyOrder(model);
		getMyGoods(model);

		return "/index";
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		try {
			logger.debug("profile");
			OAuth2AccessToken accessToken = (OAuth2AccessToken) request.getSession().getAttribute(SESSION_KEY_ACCESS_TOKEN);
			getProfile(model, accessToken);
			getMyOrder(model);
			getMyGoods(model);
		} catch (Exception e) {
			e.printStackTrace();
			this.signin(request, response);
		}
		return "/profile";
	}

	/**
	 * 应该从auth中取，但是目前auth的接口没有调整好
	 */
	private void getProfile(Model model, OAuth2AccessToken accessToken) throws Exception {
		OAuthRequest apiRequest = new OAuthRequest(Verb.GET, userInfoUri);
		hiAuthApi.signRequest(accessToken, apiRequest);
		
		Response resourceResponse = hiAuthApi.execute(apiRequest);
		
		logger.info("code:{}", resourceResponse.getCode());
		logger.info("message:{}", resourceResponse.getMessage());
		logger.info("body:{}", resourceResponse.getBody());

		JSONObject obj = new JSONObject(resourceResponse.getBody());
		logger.info("json:{}", obj.toString());
		JSONObject data = obj.getJSONObject("data");
		Long userId = data.getLong("userId");
		String name = data.getString("name");
		String username = data.getString("username");
		String tel = data.getString("tel");
		Long lastLoginTime = data.getLong("lastLoginTime");

		model.addAttribute("userId", userId);
		model.addAttribute("name", name);
		model.addAttribute("username", username);
		model.addAttribute("tel", tel);
		model.addAttribute("lastLoginTime", lastLoginTime);
	}

	/**
	 * 连接order服务获取订单信息
	 */
	private void getMyOrder(Model model) throws Exception {
		ApiResponse<Order> res =  orderApi.getInfo(1L);
		Order order = res.getData();
		model.addAttribute("orderId", order.getId());
		model.addAttribute("orderNo", order.getNo());
		model.addAttribute("orderTitle", order.getTitle());
		model.addAttribute("orderCreateTime", order.getCreateTime());
		model.addAttribute("orderTotalAmount", order.getTotalAmount());
	}

	/**
	 * 连接goods服务获取商品信息
	 */
	private void getMyGoods(Model model) {
		ApiResponse<PageVo<Goods>> res = goodsApi.query(1,10);
		logger.info("res:{}", res);
		PageVo<Goods> page = res.getData();
		model.addAttribute("goodsList", page.getList());
	}

}
