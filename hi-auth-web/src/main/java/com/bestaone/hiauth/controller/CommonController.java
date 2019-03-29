package com.bestaone.hiauth.controller;

import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.config.validatecode.ImageCode;
import com.bestaone.hiauth.config.validatecode.ImageCodeGenerator;
import com.bestaone.hiauth.core.exception.Assert;
import com.bestaone.hiauth.core.exception.CommonException;
import com.bestaone.hiauth.utils.Constant;
import com.bestaone.hiauth.utils.IdGenerator;
import com.bestaone.hiauth.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Controller
public class CommonController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static long timeout = 180;

	@Autowired
	private ImageCodeGenerator imageCodeGenerator;

    @Autowired
    private RedisUtil cacheUtil;

    @Autowired
    private IdGenerator idGenerator;

    private Random random = new Random();

    @GetMapping(value = {"/signin"}, produces = "text/html")
    public String signin(Model model) {
        model.addAttribute(Constant.REQUEST_KEY_FORM_TOKEN, idGenerator.generate());
        return "/signin";
    }

    /**
     * 生成一个有效期为180秒的图形验证码，
     * 并将此验证码和需要保护的数据进行绑定（如:账号、手机号）
     */
    @ResponseBody
    @RequestMapping("/code/image")
    public void image(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String formToken = request.getParameter(Constant.REQUEST_KEY_FORM_TOKEN);
        ImageCode imageCode = imageCodeGenerator.generate();
        cacheUtil.set(Constant.CACHE_KEY_IMAGE_YZM + ":" + formToken, imageCode.getCode(), timeout);
        logger.debug("生成图形验证码：{}, 有效期:{}妙", imageCode.getCode(), timeout);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * 发送一个短信验证码，验证码有效时间为180秒，
     * 每申请发送一次短信验证码，就需要提供一次不同的imgCode，从而防止接口被刷
     */
    @ResponseBody
    @RequestMapping("/code/mobile")
    public ApiResponse mobile(HttpServletRequest request) throws CommonException {

        String formToken = request.getParameter(Constant.REQUEST_KEY_FORM_TOKEN);
        String imgCode = request.getParameter("imgCode");
        String telNo = request.getParameter("telNo");

        Assert.notNull(formToken,20001, "formToken不能为null");
        Assert.notNull(imgCode,20002, "imgCode不能为null");
        Assert.notNull(telNo,20003, "telNo不能为null");

        //校验图形验证码是否有效
        {
            String imgCodeCache = (String) cacheUtil.get(Constant.CACHE_KEY_IMAGE_YZM + ":" + formToken);
            Assert.isTrue(imgCodeCache!=null && imgCodeCache.equals(imgCode),20101,"图形验证码错误" );
        }

        //检查imgCode是否已经用于发送短信，一个imgCode只运行发送一次短信
        {
            // TODO 暂时未实现
            String telNoCache = (String) cacheUtil.get(Constant.CACHE_KEY_SMS_YZM + ":" + formToken + ":" + imgCode);
            Assert.isTrue(telNoCache==null,20102,"图形验证码已经使用过" );
        }

        Integer smsCode = random.nextInt(8999)+ 1000;

        //记录数据到缓存
        {
            //记录发往telNo的短信验证码为smsCode
            cacheUtil.set(Constant.CACHE_KEY_SMS_YZM + ":" + telNo, smsCode, timeout);
            //记录imgCode已经用于telNo的短信验证码发送
            cacheUtil.set(Constant.CACHE_KEY_SMS_YZM + ":" + formToken + ":" + imgCode, telNo, timeout);
        }

        logger.debug("生成短信验证码：{}, 有效期:{}妙", smsCode, timeout);
        return ApiResponse.sucess(smsCode);
    }


}
