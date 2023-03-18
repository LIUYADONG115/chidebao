package com.tw.heima.chidebao.controller.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:54 下午
 */
@Builder
@Data
public class PaymentDTO {
    private String bankAccount;
    private Double paymentAmount;
}
