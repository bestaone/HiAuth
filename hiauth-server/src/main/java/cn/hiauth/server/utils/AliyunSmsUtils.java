package cn.hiauth.server.utils;

import cn.hutool.json.JSONUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.CreateSmsTemplateRequest;
import com.aliyun.dysmsapi20170525.models.CreateSmsTemplateResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class AliyunSmsUtils implements SmsUtils {

    private String sign;

    private Client client;

    private AliyunSmsUtils() {
    }

    public AliyunSmsUtils(String accessKeyId, String accessKeySecret, String endpoint, String sign) {
        this.sign = sign;
        Config config = new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(accessKeySecret);
        config.endpoint = endpoint;
        try {
            client = new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String accessKeyId = "", accessKeySecret = "", endpoint = "dysmsapi.aliyuncs.com";
        AliyunSmsUtils aliyunSmsUtil = new AliyunSmsUtils(accessKeyId, accessKeySecret, endpoint, "蚂蚁智飞");
        aliyunSmsUtil.testSendSmsVerify();

        //aliyunSmsUtil.sendVerificationCode(phone, code);
        // 0-验证码 1-通知 2-推广
        /*int type = 1;
        String applySceneContent = "http://mayizhifei.com";
        List<SmsTemplate> templates = getTemplates();
        for(SmsTemplate e : templates){
            String rule = "{\"order_sn\":\"other_number2\"}";
            if(e.getTitle().contains("任务签到通知")){
                rule = "{\"order_sn\":\"other_number2\",\"time\":\"time\"}";
            }
            if(e.getTitle().contains("钱包账户")){
                rule = "{\"order_sn\":\"other_number2\",\"amount\":\"money\"}";
            }
            if(e.getTitle().contains("验证码")){
                type = 0;
                rule = "{\"code\":\"numberCaptcha\"}";
            }
            CreateSmsTemplateResponse response = aliyunSmsUtil.createSmsTemplate(signName, type, e.getTitle(), e.getContent(), rule, applySceneContent);
            if(null != response){
                String templateCode = response.getBody().getTemplateCode();
                System.out.println("templateName: " + e.getTitle() + "   templateCode: " + templateCode);
            }
            Thread.sleep(1500);
        }*/
    }

    public void sendSms(String phoneNumbers, String templateCode, Map<String, String> templateParam) {
        sendSms(phoneNumbers, sign, templateCode, templateParam);
    }

    /**
     * 发送短信
     *
     * @param phoneNumbers  手机号（多个用逗号分隔）
     * @param templateParam 模板参数（JSON字符串）
     * @return 发送结果
     */
    public void sendSms(String phoneNumbers, String signName, String templateCode, Map<String, String> templateParam) {
        String param = JSONUtil.toJsonStr(templateParam);
        SendSmsRequest sendSmsRequest = new SendSmsRequest().setPhoneNumbers(phoneNumbers)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam(param);
        log.info("phoneNumbers:{}, signName:{}, templateCode:{}, templateParam:{}", phoneNumbers, signName, templateCode, param);
        try {
            RuntimeOptions runtime = new RuntimeOptions();
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtime);
            if (!Objects.equals(response.getBody().getCode(), "OK")) {
                throw new RuntimeException("短信发送失败: " + response.getBody().getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建短信模板
     *
     * @return 结果
     */
    private CreateSmsTemplateResponse createSmsTemplate(String signName, Integer templateType, String templateName, String templateContent, String templateRule, String applySceneContent) throws Exception {
        CreateSmsTemplateRequest request = new CreateSmsTemplateRequest();
        request.setTemplateName(templateName);
        request.setTemplateContent(templateContent);
        request.setTemplateType(templateType);
        request.setApplySceneContent(applySceneContent);
        request.setRelatedSignName(signName);
        request.setTemplateRule(templateRule);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            CreateSmsTemplateResponse response = client.createSmsTemplateWithOptions(request, runtime);
            if (!Objects.equals(response.getBody().getCode(), "OK")) {
                throw new RuntimeException("创建模板失败: " + response.getBody().getMessage());
            }
            return response;
        } catch (Exception e) {
            throw new Exception("创建模板异常: " + e.getMessage(), e);
        }
    }

    /**
     * [默认验证码]发送验证码短信
     *
     * @param phone 手机号
     * @param code  验证码
     */
    private void sendVerificationCode(String phone, String code) throws Exception {
        //String templateParam = "{\"code\":\""+code+"\"}";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", code);
        //String templateParam = JSONUtil.toJsonStr(paramMap);
        //System.out.println(templateParam);
        String signName = "蚂蚁智飞";
        String templateCode = "SMS_479050505";
        sendSms(phone, templateCode, paramMap);
    }

    private void testSendSmsVerify() throws Exception {
        //String phone = "13916383295";
        String phone = "13761379816";
        String code = "112233";
        sendVerificationCode(phone, code);
    }

    /*public static List<SmsTemplate> getTemplates(){
        List<SmsTemplate> list = new ArrayList<>();
        list.add(new SmsTemplate("服务订单/驻点订单审核通过通知", "您的订单${order_sn}审核完成，请尽快前往“蚂蚁智飞平台-我的订单-我的用工”支付!"));
        list.add(new SmsTemplate("服务订单/驻点订单报价调整通知", "您的订单${order_sn}报价有调整，请尽快前往“蚂蚁智飞平台-我的订单-我的用工”查看和支付!"));
        list.add(new SmsTemplate("团长结束任务通知", "您的订单${order_sn}已完成服务，请前往“蚂蚁智飞平台-我的订单-我的用工”进行验收!"));
        list.add(new SmsTemplate("任务订单状态变更通知", "您的任务${order_sn}需确认设备，请尽快前往“蚂蚁智飞小程序-订单-服务中”进行确认！"));
        list.add(new SmsTemplate("任务设备状态变更通知", "您的任务${order_sn}需确认任务信息，请尽快前往“蚂蚁智飞小程序-订单-服务中”进行确认！"));
        list.add(new SmsTemplate("任务签到通知", "您的任务${order_sn}需现场签到，请在${time}前抵达现场，并前往“蚂蚁智飞小程序-订单-服务中”进行签到！"));
        list.add(new SmsTemplate("用户钱包账户通知", "恭喜您，您的“${order_sn}”任务报酬${amount}已到账！请前往“蚂蚁智飞小程序-我的-钱包”中进行查看和提现。"));
        list.add(new SmsTemplate("用户注册/登录验证码", "您的短信验证码是${code}，验证码有效时间10分钟。您正在登录蚂蚁智飞账号，如非本人操作，请忽略该短信。"));
        return list;
    }*/


    /*static class SmsTemplate {
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private String content;

        public SmsTemplate(String title, String content){
            this.title = title;
            this.content = content;
        }
    }*/

}
