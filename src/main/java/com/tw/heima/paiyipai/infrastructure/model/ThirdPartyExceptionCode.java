package com.tw.heima.paiyipai.infrastructure.model;

import lombok.Getter;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:46 下午
 */
@Getter
public enum ThirdPartyExceptionCode {
    SUCCESS(2000, "保证金支付成功"),
    INSUFFICIENT_BALANCE(2001, "保证金支付失败，账户余额不足"),
    SYSTEM_ERROR(3005, "保证金支付失败，支付发生错误");

    private final Integer code;
    private final String message;

    ThirdPartyExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}