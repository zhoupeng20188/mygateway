///**
// * Title: JWTCheckGatewayFilterFactory.java
// * Description:
// * Company: 长江数字
// * @author JIMO
// * @date 2019年7月12日
// * @version 1.0
// */
//package com.example.mygateway.filter;
//
//import com.cjsz.iopcommon.constants.Constants;
//import com.cjsz.iopcommon.dto.ApiResultDto;
//import com.cjsz.iopcommon.dto.JWTDto;
//import com.cjsz.iopcommon.dto.PublicJWTDto;
//import com.cjsz.iopcommon.utils.JsonUtils;
//import com.cjsz.iopcommon.utils.StringUtils;
//import com.cjsz.iopgateway.utils.HttpUtils;
//import com.example.mygateway.utils.HttpUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.TimeUnit;
//
///**
// * Title: JWTCheckGatewayFilterFactory
// * Description:
// * @author JIMO
// * @date 2019年7月12日
// */
//@Component
//public class JWTCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
//
//    @Value("${server.port}")
//    public String port;
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    /* (non-Javadoc)
//     * @see org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory#apply(java.lang.Object)
//     */
//    @Override
//    public GatewayFilter apply(Object config) {
//        // TODO Auto-generated method stub
//        return (exchange, chain) -> {
//            // 这里可以用来写JWT验证逻辑
//            String jwt = exchange.getRequest().getHeaders().getFirst("JWT");
//            if (StringUtils.isEmpty(jwt)) {
//                return getVoidMono(exchange,
//                        new ApiResultDto().setCode(Constants.JWT.Null.getCode())
//                                          .setMessage(Constants.JWT.Null.getMessage()));
//            }
//            ApiResultDto apiResultDto = new ApiResultDto();
////            // 解码JWT获取key
////            String decodeJwt = Base64Utils.decode(jwt);
////            // 转成jwt
////            JWTDto jwtDto = JsonUtils.toClass(decodeJwt, JWTDto.class);
//            JWTDto jwtDto = new JWTDto(jwt);
//            // 根据jwt从redis获取private_key
//            String privateKey = redisTemplate.opsForValue().get(jwtDto.getKey());
//            if (StringUtils.isEmpty(privateKey)) {
//                // 如果没有redis，要么是第一次通过网关访问服务，要么是用户被封了。
//                try {
//                    // 获取privateKey
//                    String rts = HttpUtils.get("http://127.0.0.1:" + port + "/iop-auth/public/getPrivateKey?jwt=" + jwt);
//                    apiResultDto = JsonUtils.toClass(rts, ApiResultDto.class);
//                    if (apiResultDto.isSuccess()) {
//                        // 如果返回成功，表示帐号正常，获取私钥
//                        privateKey = apiResultDto.getData().toString();
//                        // 将获得的私钥放入redis，有效期7天
//                        redisTemplate.opsForValue().set(jwtDto.getKey(), privateKey, 7, TimeUnit.DAYS);
//                    } else {
//                        // 获取异常，返回错误消息
//                        return getVoidMono(exchange, apiResultDto);
//                    }
//                } catch (IOException e) {
//                    // 系统异常
//                    return getVoidMono(exchange, apiResultDto.getSystemError("解析JWT异常"));
//                }
//            }
//            // 获得私钥后，判断JWT是否有效
//            PublicJWTDto publicJWTDto = jwtDto.getPublicJWTDto(privateKey);
//            if (publicJWTDto.isExpired()) {
//                // JWT已经过期
//                return getVoidMono(exchange,
//                        apiResultDto.setCode(Constants.JWT.Expired.getCode())
//                                    .setMessage(Constants.JWT.Expired.getMessage())
//                                    .setData(null));
//            }
//            // JWT未过期，表示通过认证
//
//            // 验证通过后返回信息
//            return chain.filter(exchange);
//        };
//    }
//
//    /**
//     *
//     * Title: getVoidMono
//     * Description: JWT验证异常
//     * @param exchange
//     * @param apiResultDto
//     * @return
//     */
//    private Mono<Void> getVoidMono(ServerWebExchange exchange, ApiResultDto apiResultDto) {
//        ServerHttpResponse serverHttpResponse = exchange.getResponse();
//        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
//        // 错误信息
//        byte[] bytes = apiResultDto.toString().getBytes(StandardCharsets.UTF_8);
//        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//        return exchange.getResponse().writeWith(Flux.just(buffer));
//    }
//
////    /**
////     * Title: getVoidMono
////     * Description: 网关异常
////     * @param exchange
////     * @param jwtBadException
////     * @return
////     */
////    private Mono<Void> getVoidMono(ServerWebExchange exchange, Exception e) {
////        ServerHttpResponse serverHttpResponse = exchange.getResponse();
////        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
////        // 错误信息
////        String rts = "{\"code\":\"-1\",\"message\":\"" + e.getMessage() + "\"}";
////        byte[] bytes = rts.getBytes(StandardCharsets.UTF_8);
////        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
////        return exchange.getResponse().writeWith(Flux.just(buffer));
////    }
//}
