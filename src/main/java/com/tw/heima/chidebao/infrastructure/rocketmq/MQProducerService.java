package com.tw.heima.chidebao.infrastructure.rocketmq;

import com.alibaba.fastjson.JSON;
import com.tw.heima.chidebao.common.enums.MessageInfoType;
import com.tw.heima.chidebao.controller.model.User;
import com.tw.heima.chidebao.infrastructure.MqResponse;
import com.tw.heima.chidebao.infrastructure.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-16 9:15 下午
 */
@Slf4j
@Component
public class MQProducerService {

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer messageTimeOut;

    // 建议正常规模项目统一用一个TOPIC
    private static final String topic = "ORDER_SIGN_TOPIC";

    // 直接注入使用，用于发送消息到broker服务器
    @Resource
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 普通发送（这里的参数对象User可以随意定义，可以发送个对象，也可以是字符串等）
     */
    public void send(User user) {
        System.out.println("发送消息send");
        rocketMQTemplate.convertAndSend("RLT_TEST_TOPIC" + ":tag1", user);
//        rocketMQTemplate.send(topic + ":tag1", MessageBuilder.withPayload(user).build()); // 等价于上面一行
    }


    /**
     * 发送异步消息（通过线程池执行发送到broker的消息任务，执行完后回调：在SendCallback中可处理相关成功失败时的逻辑）
     * （适合对响应时间敏感的业务场景）
     */
    public MqResponse sendAsyncMsg(Message message) {
        System.out.println("发送消息sendAsyncMsg");
        String msgBody = JSON.toJSONString(message);
        rocketMQTemplate.asyncSend(topic + ":tag1", MessageBuilder.withPayload(msgBody).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
            }

            @Override
            public void onException(Throwable throwable) {
                // 处理消息发送异常逻辑
                throw new MqException(MessageInfoType.MQ_EXCEPTION);
            }
        });
        return MqResponse.builder().code(200).message("发送成功").build();
    }
}
