package com.tw.heima.paiyipai.infrastructure;


import com.tw.heima.paiyipai.common.enums.MessageInfoType;
import com.tw.heima.paiyipai.core.PaymentFeignClient;
import com.tw.heima.paiyipai.infrastructure.model.PaymentException;
import com.tw.heima.paiyipai.infrastructure.model.PaymentRequestInfo;
import com.tw.heima.paiyipai.infrastructure.model.PaymentResponse;
import com.tw.heima.paiyipai.infrastructure.model.ThirdPartyExceptionCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-19 1:45 上午
 */
@SpringBootTest
public class ThirdPaymentClientTest {

    @InjectMocks
    ThirdPaymentClient thirdPaymentClient;

    @Mock
    PaymentFeignClient paymentFeignClient;

    @Test
    public void should_return_successful_response () {
        PaymentRequestInfo request = PaymentRequestInfo.builder().bankAccount("zhongguoyinhang123").paymentAmount(35.5).build();
        Mockito.when(paymentFeignClient.payment(request))
                .thenReturn(PaymentResponse.builder().code(200).message("支付成功").build());
        PaymentResponse paymentResponse = thirdPaymentClient.pay(request);

        Assertions.assertThat(paymentResponse.getCode()).isEqualTo(200);
        Assertions.assertThat(paymentResponse.getMessage()).isEqualTo("支付成功");

        ArgumentCaptor<PaymentRequestInfo> captor = ArgumentCaptor.forClass(PaymentRequestInfo.class);
        Mockito.verify(paymentFeignClient).payment(captor.capture());
        Assertions.assertThat(35.5).isEqualTo(captor.getValue().getPaymentAmount());
        Assertions.assertThat("zhongguoyinhang123").isEqualTo(captor.getValue().getBankAccount());

    }

    @Test
    public void should_return_not_sufficient_funds_when_send_request_to_payment_system () {
        PaymentRequestInfo request = PaymentRequestInfo.builder().bankAccount("zhongguoyinhang123").paymentAmount(35.5).build();
        Mockito.when(paymentFeignClient.payment(request))
                .thenReturn(PaymentResponse.builder()
                        .code(ThirdPartyExceptionCode.INSUFFICIENT_BALANCE.getCode())
                        .message(ThirdPartyExceptionCode.INSUFFICIENT_BALANCE.getMessage())
                        .build());

        Throwable throwable = Assertions.catchThrowable(() -> thirdPaymentClient.pay(request));
        Assertions.assertThat(throwable).isInstanceOf(PaymentException.class).hasMessage(MessageInfoType.NOT_SUFFICIENT_FUNDS.getName());

        ArgumentCaptor<PaymentRequestInfo> captor = ArgumentCaptor.forClass(PaymentRequestInfo.class);
        Mockito.verify(paymentFeignClient).payment(captor.capture());
        Assertions.assertThat(35.5).isEqualTo(captor.getValue().getPaymentAmount());
        Assertions.assertThat("zhongguoyinhang123").isEqualTo(captor.getValue().getBankAccount());
    }


    @Test
    public void should_return_payment_system_exception_when_send_request_to_payment_system () {
        PaymentRequestInfo request = PaymentRequestInfo.builder().bankAccount("zhongguoyinhang123").paymentAmount(35.5).build();
        Mockito.when(paymentFeignClient.payment(request))
                .thenReturn(PaymentResponse.builder()
                        .code(ThirdPartyExceptionCode.SYSTEM_ERROR.getCode())
                        .message(ThirdPartyExceptionCode.SYSTEM_ERROR.getMessage())
                        .build());

        Throwable throwable = Assertions.catchThrowable(() -> thirdPaymentClient.pay(request));
        Assertions.assertThat(throwable).isInstanceOf(PaymentException.class).hasMessage(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getName());

        ArgumentCaptor<PaymentRequestInfo> captor = ArgumentCaptor.forClass(PaymentRequestInfo.class);
        Mockito.verify(paymentFeignClient).payment(captor.capture());
        Assertions.assertThat(35.5).isEqualTo(captor.getValue().getPaymentAmount());
        Assertions.assertThat("zhongguoyinhang123").isEqualTo(captor.getValue().getBankAccount());
    }
}