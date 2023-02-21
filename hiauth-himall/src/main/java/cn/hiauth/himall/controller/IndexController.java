/*
 * Copyright 2020 the original author or authors.
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
package cn.hiauth.himall.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Slf4j
@Controller
public class IndexController {

	private final WebClient webClient;
	@Value("${hiauth.resource.base-uri}")
	private String resourceBaseUri;

	public IndexController(WebClient webClient) {
		this.webClient = webClient;
	}

	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}

	@GetMapping("/demo")
	public String demo() {
		return "demo";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextHolder.clearContext();
		Cookie newCookie = new Cookie("HIMALL_JSESSIONID","");
		newCookie.setMaxAge(0);
		response.addCookie(newCookie);
		newCookie = new Cookie("JSESSIONID","");
		newCookie.setMaxAge(0);
		response.addCookie(newCookie);
		request.getSession().setAttribute("isAuth", false);
		return "index";
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request, Model model) {
		Map<String, Map> res = this.webClient
				.post()
				.uri(this.resourceBaseUri + "/api/profile")
				.attributes(clientRegistrationId("hiauth-client-credentials"))
				.retrieve()
				.bodyToMono(Map.class)
				.block();

		log.info("res:{}", res);

		model.addAllAttributes(res.get("currnetUser"));
		model.addAllAttributes(res.get("order"));
		model.addAttribute("goodsList", res.get("goods"));
		request.getSession().setAttribute("isAuth", true);
		return "profile";
	}

}
