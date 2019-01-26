package com.bestaone.aiwan.auth.web.controller;

import com.bestaone.aiwan.auth.web.config.smscode.Cache;
import com.bestaone.aiwan.auth.web.config.validatecode.CaptchaAuthenticationFilter;
import com.bestaone.aiwan.auth.web.config.validatecode.ImageCode;
import com.bestaone.aiwan.auth.web.config.validatecode.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ValidateCodeController {

	@Autowired
	private ImageCodeGenerator imageCodeGenerator;

    @RequestMapping("/code/image")
    public void image(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generate();
        session.setAttribute(CaptchaAuthenticationFilter.SESSION_CAPTCHA_KEY, imageCode.getCode());
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @RequestMapping("/code/mobile")
    public void mobile(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        Cache.send("137", "1234");
    }

}
