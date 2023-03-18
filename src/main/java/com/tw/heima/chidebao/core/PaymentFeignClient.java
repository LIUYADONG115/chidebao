package com.tw.heima.chidebao.core;


import com.sun.istack.Nullable;
import com.tw.heima.chidebao.infrastructure.model.PaymentRequestInfo;
import com.tw.heima.chidebao.infrastructure.model.PaymentResponse;
import org.springframework.stereotype.Component;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:46 下午
 */
@Component
public class PaymentFeignClient {

    @Nullable
    public PaymentResponse payment(PaymentRequestInfo paymentRequestInfo) {
        return PaymentResponse.builder().code(2000).message("支付成功").build();
    };
}
