package com.tw.heima.chidebao.infrastructure.model;

import com.tw.heima.chidebao.common.enums.MessageInfoType;
import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException{

    private final MessageInfoType type;

    public PaymentException(MessageInfoType type) {
        super(type.getName());
        this.type = type;
    }
}
