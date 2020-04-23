///**
// * Title: PublicUserBannedMessageListener.java
// * Description:
// * Company: 长江数字
// * @author JIMO
// * @date 2019年8月5日
// * @version 1.0
// */
//package com.example.mygateway.listener;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
///**
// * Title: PublicUserBannedMessageListener
// * Description: 监听topic = "iop-public-user-banned"的消息，当收到消息时，将redis里的密钥删除
// * @author JIMO
// * @date 2019年8月5日
// */
//@Service
//@RocketMQMessageListener(topic = "iop-public-user-banned", consumerGroup = "iop-user")
//public class PublicUserBannedMessageListener implements RocketMQListener<String> {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Override
//    public void onMessage(String message) {
//        // 接受到用户信息后，删除reids里面用户的private_key
//        String key = DigestUtils.md5Hex(message + "_private_key");
//        // 删除私钥。
//        redisTemplate.delete(key);
//    }
//
//}
