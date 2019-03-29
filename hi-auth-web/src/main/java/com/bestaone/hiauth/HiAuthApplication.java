package com.bestaone.hiauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.bestaone.hiauth"})
@SpringBootApplication
public class HiAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiAuthApplication.class, args);
    }

}