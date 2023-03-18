package com.tw.heima.chidebao.common.enums;

import lombok.Getter;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 11:47 下午
 */
@Getter
public enum SignStatus {
    ORDER_NOT_SIGN(1, "餐品未签收"),
    ORDER_SIGNING(2, "签收确认中"),
    ORDER_SIGN_SUCCESS(3, "签收成功"),
    ORDER_SIGN_FAILED(4, "签收失败");

    int code;
    String name;

    SignStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
