package com.tw.heima.chidebao.common.enums;

import lombok.Getter;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 11:47 下午
 */
@Getter
public enum SignStatus {
    ORDER_NOT_SIGN(1, "成交协议未签署"),
    ORDER_SIGNING(2, "成交协议签署中"),
    ORDER_SIGN_SUCCESS(3, "成交协议签署成功"),
    ORDER_SIGN_FAILED(4, "成交协议签署失败");

    int code;
    String name;

    SignStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
