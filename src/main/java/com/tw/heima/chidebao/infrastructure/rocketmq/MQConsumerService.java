package com.tw.heima.chidebao.infrastructure.rocketmq;

import com.alibaba.fastjson.JSON;
import com.tw.heima.chidebao.common.enums.SignStatus;
import com.tw.heima.chidebao.controller.model.User;
import com.tw.heima.chidebao.infrastructure.model.Message;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderEntity;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderProcessRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-16 9:20 下午
 */
@Slf4j
@Component
public class MQConsumerService {

    @Resource
    private OrderProcessRepo orderProcessRepo;

    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", selectorExpression = "tag1", consumerGroup = "Con_Group_One")
    public class ConsumerSend implements RocketMQListener<User> {
        // 监听到消息就会执行此方法
        @Override
        public void onMessage(User user) {
            log.info("监听到消息：user={}", JSON.toJSONString(user));
        }
    }


    @Service
    @RocketMQMessageListener(topic = "ORDER_SIGN_TOPIC", selectorExpression = "tag1", consumerGroup = "Con_Group_Two")
    public class ConsumerSend2 implements RocketMQListener<String> {
        @Override
        public void onMessage(String str) {
            log.info("监听到消息：str={}", str);
            Message message = JSON.parseObject(str,Message.class);
            OrderEntity order = orderProcessRepo.findByUserId(message.getUserId());
            order.setSignStatus(SignStatus.ORDER_SIGN_SUCCESS.getCode());
            System.out.println("存储签署成功的orderEntity："+order.toString());
            orderProcessRepo.save(order);
        }
    }

}

