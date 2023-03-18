package com.tw.heima.chidebao.infrastructure;

import com.tw.heima.chidebao.infrastructure.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageProvider {

    private final KafkaMqProducer kafkaMqProducer;

    public MessageProvider(KafkaMqProducer kafkaMqProducer) {
        this.kafkaMqProducer = kafkaMqProducer;
    }

    public MqResponse send(Message message) {
        MqResponse response = kafkaMqProducer.send(message);
        return MqResponse.builder().code(response.getCode()).message(response.getMessage()).build();
    }
}
