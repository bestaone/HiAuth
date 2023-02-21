package cn.hiauth.auth.controller;

import cn.hiauth.auth.common.ApiResponse;
import cn.hiauth.auth.config.multilogin.ImageCode;
import cn.hiauth.auth.config.multilogin.ImageCodeGenerator;
import cn.hiauth.auth.utils.CacheUtil;
import cn.hiauth.auth.utils.Constant;
import cn.hiauth.auth.utils.IdGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

@Slf4j
@Controller
public class AuthController {

    private final static long timeout = 180;

    @Autowired
    private ImageCodeGenerator imageCodeGenerator;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private IdGenerator idGenerator;

    private final Random random = new Random();

    @GetMapping(value = {"/login"}, produces = "text/html")
    public String login(Model model) {
        model.addAttribute(Constant.REQUEST_KEY_FORM_TOKEN, idGenerator.generate());
        return "login";
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
        log.debug("生成图形验证码：{}, 有效期:{}妙", imageCode.getCode(), timeout);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * 发送一个短信验证码，验证码有效时间为180秒，
     * 每申请发送一次短信验证码，就需要提供一次不同的imgCode，从而防止接口被刷
     */
    @ResponseBody
    @RequestMapping("/code/sms")
    public ApiResponse sms(HttpServletRequest request) {

        String formToken = request.getParameter(Constant.REQUEST_KEY_FORM_TOKEN);
        String imgCode = request.getParameter("imgCode");
        String telNo = request.getParameter("telNo");

        if(formToken==null || imgCode==null || telNo==null){
            return ApiResponse.fail(20001, "参数不能为null");
        }

        //校验图形验证码是否有效
        {
            String imgCodeCache = (String) cacheUtil.get(Constant.CACHE_KEY_IMAGE_YZM + ":" + formToken);
            if(imgCodeCache==null || !imgCodeCache.equals(imgCode)){
               return ApiResponse.fail(20101, "图形验证码错误");
            }
        }

        //检查imgCode是否已经用于发送短信，一个imgCode只运行发送一次短信
        {
            // TODO 暂时未实现
            String telNoCache = (String) cacheUtil.get(Constant.CACHE_KEY_SMS_YZM + ":" + formToken + ":" + imgCode);
            if(telNoCache!=null){
                return ApiResponse.fail(20102, "图形验证码已经使用过");
            }
        }

        Integer smsCode = random.nextInt(8999)+ 1000;

        //记录数据到缓存
        {
            //记录发往telNo的短信验证码为smsCode
            cacheUtil.set(Constant.CACHE_KEY_SMS_YZM + ":" + telNo, smsCode, timeout);
            //记录imgCode已经用于telNo的短信验证码发送
            cacheUtil.set(Constant.CACHE_KEY_SMS_YZM + ":" + formToken + ":" + imgCode, telNo, timeout);
        }

        log.debug("生成短信验证码：{}, 有效期:{}妙", smsCode, timeout);
        return ApiResponse.success(smsCode);
    }

}
