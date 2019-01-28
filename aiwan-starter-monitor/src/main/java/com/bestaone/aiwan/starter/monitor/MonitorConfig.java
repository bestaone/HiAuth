package com.bestaone.aiwan.starter.monitor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "monitor", name = "enable", havingValue = "true")
public class MonitorConfig {

}