package com.bestaone.himall.config;

import com.bestaone.himall.api.goods.GoodsApi;
import com.bestaone.himall.api.order.OrderApi;
import com.netflix.client.ClientFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.ribbon.LBClient;
import feign.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FeignConfig {

    @Bean
    RibbonClient ribbonClient() {
        try {
            // 加载 ribbon 相关配置，可以抽取出来独立文件放到classpath下
            ConfigurationManager.loadPropertiesFromResources("application.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 自己是想路由规则
        return RibbonClient.builder().lbClientFactory(clientName -> {
            IClientConfig config = ClientFactory.getNamedConfig(clientName);
            ILoadBalancer lb = ClientFactory.getNamedLoadBalancer(clientName);
            ZoneAwareLoadBalancer zb = (ZoneAwareLoadBalancer) lb;
            zb.setRule(new RandomRule());
            return LBClient.create(lb, config);
        }).build();
    }

    @Bean
    GoodsApi goodsApi() {
        return HystrixFeign.builder()
                .client(ribbonClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(GoodsApi.class, "http://goods-svr/goods/api");
    }

    @Bean
    OrderApi orderApi(){
        return HystrixFeign.builder()
                .client(ribbonClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(OrderApi.class, "http://order-svr/order/api");
    }

}
