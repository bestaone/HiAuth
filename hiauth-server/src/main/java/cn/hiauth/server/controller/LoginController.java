package cn.hiauth.server.controller;

import cn.hiauth.server.api.dto.login.CaptchaVerifyDto;
import cn.hiauth.server.config.props.AppProperties;
import cn.hiauth.server.config.props.WechatProperties;
import cn.hiauth.server.config.web.security.MultiAppHttpSessionRequestCache;
import cn.hiauth.server.entity.App;
import cn.hiauth.server.mapper.AppMapper;
import cn.hiauth.server.utils.Constant;
import cn.hiauth.server.utils.SmsUtils;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import cn.webestar.scms.cache.CacheUtil;
import cn.webestar.scms.commons.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
public class LoginController {

    private final static long timeout = 600;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private WechatProperties wechatProperties;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private Snowflake idGenerator;

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private AppMapper appMapper;

    @Value("${smsUils.smsTemplateCode:}")
    private String smsTemplateCode;

    @Autowired
    private RandomGenerator randomGenerator;

    @GetMapping(value = {"/login"}, produces = "text/html")
    public String login(@RequestParam(value = OAuth2ParameterNames.CLIENT_ID, required = false) String clientId, HttpServletRequest request, Model model) {
        Set<String> loginTypes = appProperties.getLoginTypes();
        String loginPage = appProperties.getLoginPagePath();
        // 获取当前授权应用，查询自定义配
        if (clientId != null) {
            App app = appMapper.findByClientId(clientId);
            if (app != null) {
                model.addAttribute("appName", app.getName());
                if (app.getExtend() != null) {
                    if (app.getExtend().containsKey("loginTypes")) {
                        String str = (String) app.getExtend().get("loginTypes");
                        loginTypes = Stream.of(str.split(",")).collect(Collectors.toSet());
                    }
                    if (app.getExtend().containsKey("loginPage")) {
                        loginPage = (String) app.getExtend().get("loginPage");
                    }
                }
            }
        }
        // URL中指定要登录的应用
        model.addAttribute(MultiAppHttpSessionRequestCache.CLIENT_ID_KEY, clientId);
        // 登录方式
        model.addAttribute("loginTypes", loginTypes);
        // 登录页面配置
        model.addAttribute(Constant.REQUEST_KEY_FORM_TOKEN, idGenerator.nextId());
        model.addAttribute("title", appProperties.getLoginPageTitle());
        model.addAttribute("username", appProperties.getLoginPageUsername());
        model.addAttribute("password", appProperties.getLoginPagePassword());
        model.addAttribute("usernamePlaceholder", appProperties.getLoginPageUsernamePlaceholder());
        model.addAttribute("passwordPlaceholder", appProperties.getLoginPagePasswordPlaceholder());
        // 微信登录配置
        String wechatRedirectUri = wechatProperties.getRedirectUri();
        if (StringUtils.hasText(clientId)) {
            wechatRedirectUri += "?" + MultiAppHttpSessionRequestCache.CLIENT_ID_KEY + "=" + clientId;
        }
        model.addAttribute("wechatAppid", wechatProperties.getAppid());
        model.addAttribute("wechatRedirectUri", wechatRedirectUri);
        model.addAttribute("wechatStyle", wechatProperties.getStyle());
        model.addAttribute("wechatHref", wechatProperties.getHref());
        model.addAttribute("wechaState", idGenerator.nextId());
        return loginPage;
    }

    /**
     * 生成一个有效期为180秒的图形验证码，
     * 并将此验证码和需要保护的数据进行绑定（如:账号、手机号）
     */
    @GetMapping("/auth/code/image")
    public void image(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String formToken = request.getParameter(Constant.REQUEST_KEY_FORM_TOKEN);
        ICaptcha captcha = CaptchaUtil.createCircleCaptcha(90, 30, randomGenerator, 10);
        cacheUtil.set(Constant.CACHE_KEY_CAPTCHA + ":" + formToken, captcha.getCode(), timeout);
        log.debug("生成图形验证码：{}, 有效期:{}妙", captcha.getCode(), timeout);
        captcha.write(response.getOutputStream());
    }

    /**
     * 生成一个有效期为180秒的验证码，适用于滑动验证码，需要验证用户行为，验证成功后返回验证码
     * 并将此验证码和需要保护的数据进行绑定（如:账号、手机号）
     */
    @ResponseBody
    @PostMapping("/auth/code/captcha")
    public R<String> captcha(@RequestBody CaptchaVerifyDto dto) {
        String formToken = dto.getToken();
        ICaptcha captcha = CaptchaUtil.createCircleCaptcha(90, 30, randomGenerator, 10);
        cacheUtil.set(Constant.CACHE_KEY_CAPTCHA + ":" + formToken, captcha.getCode(), 60);
        log.debug("生成图形验证码：{}, 有效期:{}妙", captcha.getCode(), timeout);
        return R.success(captcha.getCode());
    }

    /**
     * 发送一个短信验证码，验证码有效时间为180秒，
     * 每申请发送一次短信验证码，就需要提供一次不同的imgCode，从而防止接口被刷
     */
    @ResponseBody
    @GetMapping("/auth/code/sms")
    public R<?> sms(HttpServletRequest request) {

        String formToken = request.getParameter(Constant.REQUEST_KEY_FORM_TOKEN);
        String imgCode = request.getParameter("imgCode");
        String telNo = request.getParameter("telNo");

        if (formToken == null || imgCode == null || telNo == null) {
            return R.fail(20001, "参数不能为null");
        }

        //校验图形验证码是否有效
        {
            String imgCodeCache = (String) cacheUtil.get(Constant.CACHE_KEY_CAPTCHA + ":" + formToken);
            if (imgCodeCache == null || !imgCodeCache.equalsIgnoreCase(imgCode)) {
                return R.fail(20101, "图形验证码错误");
            }
        }

        //检查imgCode是否已经用于发送短信，一个imgCode只运行发送一次短信
        {
            // TODO 暂时未实现
            String telNoCache = (String) cacheUtil.get(Constant.CACHE_KEY_SMS_CODE + ":" + formToken + ":" + imgCode);
            if (telNoCache != null) {
                return R.fail(20102, "图形验证码已经使用过");
            }
        }

        String smsCode = RandomUtil.randomNumbers(6);

        //记录数据到缓存
        {
            //记录发往telNo的短信验证码为smsCode
            cacheUtil.set(Constant.CACHE_KEY_SMS_CODE + ":" + telNo, smsCode, timeout);
            //记录imgCode已经用于telNo的短信验证码发送
            cacheUtil.set(Constant.CACHE_KEY_SMS_CODE + ":" + formToken + ":" + imgCode, telNo, timeout);
        }

        Map<String, String> paramMap = new HashMap<>(2);
        paramMap.put("code", smsCode);
        smsUtils.sendSms(telNo, smsTemplateCode, paramMap);

        log.debug("生成短信验证码：{}, 有效期:{}妙", smsCode, timeout);
        return R.success();
    }

    @GetMapping("/unpapi/logoutWithRedirect")
    public String logoutWithRedirect(@RequestParam("redirect_uri") String redirectUri, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:" + redirectUri;
    }

}
