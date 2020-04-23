//package com.example.mygateway.config;
//
//import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
//import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
//import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.cjsz.iopgateway.handler.JsonSentinelGatewayBlockExceptionHandler;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.web.reactive.result.view.ViewResolver;
//
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//public class SentinelGatewayConfiguration {
//
//    private final List<ViewResolver> viewResolvers;
//    private final ServerCodecConfigurer serverCodecConfigurer;
//
//    @Value("${sentinel.remoteAddress}")
//    private String remoteAddress;
//    @Value("${sentinel.groupId}")
//    private String groupId;
//    @Value("${sentinel.dataId}")
//    private String dataId;
//
//    public SentinelGatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
//                                        ServerCodecConfigurer serverCodecConfigurer) {
//        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
//        this.serverCodecConfigurer = serverCodecConfigurer;
//    }
//
//
//    /**
//     * 配置SentinelGatewayBlockExceptionHandler，限流后异常处理
//     * @return
//     */
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    // 自定义限流msg
//    public JsonSentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
//        return new JsonSentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
//    }
//
//    /**
//     * 配置SentinelGatewayFilter
//     * @return
//     */
//    @Bean
//    @Order(-1)
//    public GlobalFilter sentinelGatewayFilter() {
//        return new SentinelGatewayFilter();
//    }
//
//    @PostConstruct
//    public void doInit() {
//        initGatewayRules();
//    }
//
//    /**
//     * 配置限流规则
//     */
//    private void initGatewayRules() {
//
//        // 手动加载规则
////        Set<GatewayFlowRule> rules = new HashSet<>();
////        rules.add(new GatewayFlowRule("provider1111")
////            .setCount(1) // 限流阈值
////            .setIntervalSec(1) // 统计时间窗口，单位是秒，默认是 1 秒
////        );
////        GatewayRuleManager.loadRules(rules);
//
//        // 动态加载加载规则
//
//        // remoteAddress 代表 Nacos 服务端的地址
//        // groupId 和 dataId 对应 Nacos 中相应配置
//        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
//                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
//        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
//    }
//}