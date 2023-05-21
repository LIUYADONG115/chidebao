package com.tw.heima.chidebao.infrastructure.rocketmq;

import com.tw.heima.chidebao.common.enums.MessageInfoType;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-21 11:09 上午
 */
public class MqException extends RuntimeException{

    private final MessageInfoType type;

    public MqException(MessageInfoType type) {
        super(type.getName());
        this.type = type;
    }
}

