package com.bestaone.himall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.bestaone.himall"})
@SpringBootApplication
public class HiMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiMallApplication.class, args);
    }

}