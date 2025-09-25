package cn.hiauth.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("HiAuth接口文档")
                        .version("v3")
                        .description("提供HiAuth的业务接口")
                        .contact(new Contact()
                                .name("技术支持")
                                .url("http://hiauth.cn")
                                .email("bestaone@163.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("http://hiauth.cn")));
    }

}
