package com.tw.heima.chidebao.common.enums;

import lombok.Getter;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:55 下午
 */
@Getter
public enum MessageInfoType {
    PAYMENT_SUCCEEDED("200", "订单支付成功"),
    ORDER_NOT_EXIST("300", "订单不存在"),
    NOT_SUFFICIENT_FUNDS("400", "订单支付失败，账户余额不足"),
    PAYMENT_SYSTEM_EXCEPTION("500", "系统异常，请稍后重试"),

    SIGN_SUCCEEDED("200", "餐品签收成功"),
    SIGN_FAILED("200", "餐品签收失败");



    String code;
    String name;

    MessageInfoType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
