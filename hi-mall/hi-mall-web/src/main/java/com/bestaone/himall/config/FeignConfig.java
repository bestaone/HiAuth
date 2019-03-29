package com.bestaone.himall.config;

import com.bestaone.himall.api.goods.GoodsApi;
import com.bestaone.himall.api.order.OrderApi;
import feign.Request;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${hiMall.microsvr.goods.url:}")
    private String goodsUrl;

    @Value("${hiMall.microsvr.order.url:}")
    private String orderUrl;

    @Bean
    GoodsApi goodsApi(){
        return HystrixFeign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
//                .errorDecoder()
                .options(new Request.Options(2000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(GoodsApi.class, goodsUrl);
    }

    @Bean
    OrderApi orderApi(){
        return HystrixFeign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(2000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(OrderApi.class, orderUrl);
    }

}
