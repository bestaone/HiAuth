package cn.hiauth.server;

import cn.webestar.scms.commons.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"cn.hiauth.server", Constant.SCMS_BASIC_PKG})
@SpringBootApplication
public class ServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ServerStarter.class, args);
    }

}
