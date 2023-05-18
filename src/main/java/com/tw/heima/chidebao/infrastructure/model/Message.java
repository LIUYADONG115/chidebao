package com.tw.heima.chidebao.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String topic;
    private String tag;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime messageStartTime;
    private String orderId;
    private String signTime;
    private String signName;
    private String storeName;

    public Message(String topic, String tag, LocalDateTime messageStartTime, String orderId, String signTime, String signName, String storeName) {
        this.topic = topic;
        this.tag = tag;
        this.messageStartTime = messageStartTime;
        this.orderId = orderId;
        this.signTime = signTime;
        this.signName = signName;
        this.storeName = storeName;
    }

    public static class Topic {
        public static final String SIGN_ORDER_TOPIC = "ORDER_SIGN_TOPIC";
        public static final String SIGN_ORDER_TAG = "order_sign_tag";
    }
}
