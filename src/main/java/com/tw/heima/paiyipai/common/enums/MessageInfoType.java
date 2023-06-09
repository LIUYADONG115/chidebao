package com.tw.heima.paiyipai.common.enums;

import lombok.Getter;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:55 下午
 */
@Getter
public enum MessageInfoType {
    PAYMENT_SUCCEEDED("200", "保证金支付成功"),
    ORDER_NOT_EXIST("300", "请先办理竞买登记"),
    NOT_SUFFICIENT_FUNDS("400", "支付失败，账户余额不足"),
    PAYMENT_SYSTEM_EXCEPTION("500", "系统异常，请稍后重试"),
    MQ_EXCEPTION("600", "系统异常，请稍后重试"),

    SIGN_ING("200", "成交协议签约已受理"),
    SIGN_SUCCEEDED("200", "成交协议签署成功"),
    SIGN_FAILED("200", "成交协议签署失败");



    String code;
    String name;

    MessageInfoType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
