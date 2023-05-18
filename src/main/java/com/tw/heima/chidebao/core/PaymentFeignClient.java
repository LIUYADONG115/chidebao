package com.tw.heima.chidebao.core;


import com.sun.istack.Nullable;
import com.tw.heima.chidebao.infrastructure.model.PaymentRequestInfo;
import com.tw.heima.chidebao.infrastructure.model.PaymentResponse;
import com.tw.heima.chidebao.infrastructure.model.ThirdPartyExceptionCode;
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
        if(paymentRequestInfo.getPaymentAmount() > 100 ){
            return PaymentResponse.builder().code(ThirdPartyExceptionCode.INSUFFICIENT_BALANCE.getCode()).message(ThirdPartyExceptionCode.INSUFFICIENT_BALANCE.getMessage()).build();
        }else {
            return PaymentResponse.builder().code(ThirdPartyExceptionCode.SUCCESS.getCode()).message(ThirdPartyExceptionCode.SUCCESS.getMessage()).build();
        }

    };
}
