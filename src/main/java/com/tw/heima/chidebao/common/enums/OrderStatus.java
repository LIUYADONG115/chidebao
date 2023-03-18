package com.tw.heima.chidebao.common.enums;

import lombok.Getter;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:50 下午
 */
@Getter
public enum OrderStatus {
    ORDER_NOT_PAYMENT(1, "餐品订单未支付"),
    ORDER_PAYING(2, "餐品订单正在支付"),
    ORDER_PAYMENT_SUCCESS(3, "餐品订单支付成功"),
    ORDER_PAYMENT_FAILED(4, "餐品订单支付失败");

    int code;
    String name;

    OrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
