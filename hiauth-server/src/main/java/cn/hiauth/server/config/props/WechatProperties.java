package cn.hiauth.server.config.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Configuration
public class WechatProperties implements Serializable {

    @Value(value = "${wechat.open.appid:}")
    private String appid;

    @Value(value = "${wechat.open.appSecret:}")
    private String appSecret;

    @Value(value = "${wechat.open.redirectUri:}")
    private String redirectUri;

    @Value(value = "${wechat.open.style:}")
    private String style;

    @Value(value = "${wechat.open.href:}")
    private String href;

}
