package cn.hiauth.client.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zgs
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({HiAuthClientResourceProperties.class})
public class HiAuthClientResourceAutoConfig {


}
