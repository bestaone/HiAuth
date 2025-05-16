package cn.hiauth.gateway.filter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 前置处理逻辑
        System.out.println("全局过滤器 pre-processing");
        // 可以修改请求或响应
        // exchange.getRequest().mutate()...
        // exchange.getResponse().getHeaders().add(...)
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 后置处理逻辑
            System.out.println("全局过滤器 post-processing");
        }));
    }

    @Override
    public int getOrder() {
        // 设置过滤器执行顺序，值越小优先级越高
        return -1;
    }

}