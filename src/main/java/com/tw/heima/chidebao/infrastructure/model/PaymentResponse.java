package com.tw.heima.chidebao.infrastructure.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentResponse {

    private Integer code;
    private String message;

}
