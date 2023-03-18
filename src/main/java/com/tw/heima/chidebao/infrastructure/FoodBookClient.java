package com.tw.heima.chidebao.infrastructure;


import com.tw.heima.chidebao.common.enums.MessageInfoType;
import com.tw.heima.chidebao.core.PaymentFeignClient;
import com.tw.heima.chidebao.infrastructure.model.PaymentException;
import com.tw.heima.chidebao.infrastructure.model.PaymentRequestInfo;
import com.tw.heima.chidebao.infrastructure.model.PaymentResponse;
import com.tw.heima.chidebao.infrastructure.model.ThirdPartyExceptionCode;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:46 下午
 */
@Component
public class FoodBookClient {

    private final PaymentFeignClient paymentFeignClient;

    public FoodBookClient(PaymentFeignClient paymentFeignClient) {
        this.paymentFeignClient = paymentFeignClient;
    }

    public PaymentResponse pay(PaymentRequestInfo request) throws PaymentException {

        PaymentResponse response = paymentFeignClient.payment(request);

        if (Objects.isNull(response) || ThirdPartyExceptionCode.SYSTEM_ERROR.getCode().equals(response.getCode())) {
            throw new PaymentException(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION);
        }
        if (ThirdPartyExceptionCode.INSUFFICIENT_BALANCE.getCode().equals(response.getCode())) {
            throw new PaymentException(MessageInfoType.NOT_SUFFICIENT_FUNDS);
        }

        return response;
    }


}
