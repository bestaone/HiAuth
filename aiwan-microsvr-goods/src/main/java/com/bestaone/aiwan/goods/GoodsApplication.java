package com.bestaone.aiwan.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.bestaone.aiwan", "com.bestaone.aiwan.core", "com.bestaone.aiwan.starter.**"})
@SpringBootApplication
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

}