package com.tw.heima.chidebao.infrastructure.model;

import lombok.Getter;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:46 下午
 */
@Getter
public enum ThirdPartyExceptionCode {
    SYSTEM_ERROR(3005, "订单支付失败，支付发生错误"),
    INSUFFICIENT_BALANCE(2001, "订单支付失败，账户余额不足"),
    SUCCESS(2000, "订单支付成功");

    private final Integer code;
    private final String message;

    ThirdPartyExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}