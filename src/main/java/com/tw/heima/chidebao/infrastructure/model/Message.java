package com.tw.heima.chidebao.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
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

    public static class Topic {
        public static final String SIGN_ORDER_TOPIC= "T_order_sign";
        public static final String SIGN_ORDER_TAG = "order_sign_tag";
    }
}
