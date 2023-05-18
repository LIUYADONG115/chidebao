package com.tw.heima.chidebao.infrastructure;

import com.tw.heima.chidebao.infrastructure.model.Message;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-19 1:56 上午
 */
@SpringBootTest
public class MessageProviderTest {

    @InjectMocks
    MessageProvider messageProvider;

    @Mock
    KafkaMqProducer producer;

    @Test
    public void should_return_send_message_successful_when_send_refund_request() {
        Message message = new Message(
                "order",
                "order_tag",
                LocalDateTime.now(),
                "11223344",
                "2023-3-19 12:33:00",
                "小明",
                "KFC");

        Mockito.when(producer.send(message)).thenReturn(MqResponse.builder().code(200).message("发送消息成功").build());

        MqResponse mqResponse = messageProvider.send(message);

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        Mockito.verify(producer).send(captor.capture());

        Assertions.assertThat(message.getOrderId()).isEqualTo(captor.getValue().getOrderId());
        Assertions.assertThat(message.getSignTime()).isEqualTo(captor.getValue().getSignTime());
        Assertions.assertThat(message.getSignName()).isEqualTo(captor.getValue().getSignName());
        Assertions.assertThat(message.getStoreName()).isEqualTo(captor.getValue().getStoreName());
        Assertions.assertThat(message.getTopic()).isEqualTo(captor.getValue().getTopic());
        Assertions.assertThat(message.getTag()).isEqualTo(captor.getValue().getTag());


        Assertions.assertThat(mqResponse.getCode()).isEqualTo(200);
        Assertions.assertThat(mqResponse.getMessage()).isEqualTo("发送消息成功");
    }
}