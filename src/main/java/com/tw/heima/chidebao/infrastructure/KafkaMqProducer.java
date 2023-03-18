package com.tw.heima.chidebao.infrastructure;

import com.tw.heima.chidebao.infrastructure.model.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaMqProducer {

    MqResponse send(Message message) {
        return MqResponse.builder().code(200).message("发送成功").build();
    }
}
