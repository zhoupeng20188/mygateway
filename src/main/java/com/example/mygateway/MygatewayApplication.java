package com.example.mygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class MygatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MygatewayApplication.class, args);
    }

    /**
     *
     * Title: hiddenHttpMethodFilter
     * Description: 禁用HiddenHttpMethodFilter 过滤器
     *             注：主要是防止过滤器出现java.lang.IllegalStateException: Only one connection receive subscriber allowed.的异常
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                return chain.filter(exchange);
            }
        };
    }

}
