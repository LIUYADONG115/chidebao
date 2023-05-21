package com.tw.heima.paiyipai.infrastructure.model;

import com.tw.heima.paiyipai.common.enums.MessageInfoType;
import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException{

    private final MessageInfoType type;

    public PaymentException(MessageInfoType type) {
        super(type.getName());
        this.type = type;
    }
}
