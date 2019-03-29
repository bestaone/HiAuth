package com.bestaone.himall.config;

import com.bestaone.himall.api.goods.GoodsApi;
import com.bestaone.himall.api.order.OrderApi;
import feign.Request;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    GoodsApi goodsApi(){
        return HystrixFeign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
//                .errorDecoder()
                .options(new Request.Options(2000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(GoodsApi.class, "http://localhost:8281/goods/api");
    }

    @Bean
    OrderApi orderApi(){
        return HystrixFeign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(2000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(OrderApi.class, "http://localhost:8282/order/api");
    }

}
