package cn.hiauth.hiauthclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"cn.hiauth.client", "cn.hiauth.hiauthclient"})
@SpringBootApplication
public class HiauthClientStarter {

    public static void main(String[] args) {
        SpringApplication.run(HiauthClientStarter.class, args);
    }

}
