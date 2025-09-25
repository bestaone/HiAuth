package cn.hiauth.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomGatewayFilterFactory.Config> {

    public CustomGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 前置处理
            System.out.println("网关过滤器 pre-processing with config: " + config.getName());
            if (config.isEnabled()) {
                // 可以添加自定义逻辑
                exchange.getRequest().mutate().header("X-Custom-Header", config.getName());
                exchange.getRequest().mutate().header("Authorization", "test");
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 后置处理
                System.out.println("网关过滤器 post-processing");
            }));
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name", "enabled");
    }

    public static class Config {
        private String name;
        private boolean enabled;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}