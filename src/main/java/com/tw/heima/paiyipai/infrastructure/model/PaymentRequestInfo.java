package com.tw.heima.paiyipai.infrastructure.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentRequestInfo {
    private String bankAccount;
    private Double paymentAmount;
}
