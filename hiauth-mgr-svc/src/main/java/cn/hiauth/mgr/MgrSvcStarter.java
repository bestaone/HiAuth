package cn.hiauth.mgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "cn.hiauth")
@SpringBootApplication
public class MgrSvcStarter {

    public static void main(String[] args) {
        SpringApplication.run(MgrSvcStarter.class, args);
    }

}