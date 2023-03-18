package com.tw.heima.chidebao.infrastructure.model.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-19 2:00 上午
 */
@SpringBootTest
public class MessageRepoTest {
    @Autowired
    MessageRepo messageRepo;

    @Test
    public void should_find_message_data_when_insert_before() {
        MessageEntity messageEntity = MessageEntity.builder().businessId("11223344").content("{\"orderId\":\"123\",\"signTime\":\"2023-3-19 12:33:00\",\"storeName\":\"KFC\",\"topic\":\"topic\", \"tag\": \"tag\"}").build();
        messageRepo.save(messageEntity);

        MessageEntity entity = messageRepo.findByBusinessId("11223344");

        assertThat(entity.getBusinessId()).isEqualTo("11223344");
        assertThat(entity.getContent()).isEqualTo("{\"orderId\":\"123\",\"signTime\":\"2023-3-19 12:33:00\",\"storeName\":\"KFC\",\"topic\":\"topic\", \"tag\": \"tag\"}");
    }

    @AfterEach
    public void clear()  {
        messageRepo.deleteAll();
    }
}