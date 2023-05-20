package com.tw.heima.chidebao.controller;

import com.tw.heima.chidebao.common.enums.MessageInfoType;
import com.tw.heima.chidebao.controller.model.CommonResponse;
import com.tw.heima.chidebao.controller.model.OrderSignDTO;
import com.tw.heima.chidebao.controller.model.PaymentDTO;
import com.tw.heima.chidebao.domain.BidderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-20 7:46 下午
 */
@SpringBootTest
class BidderControllerTest {

    @InjectMocks
    BidderController bidderController;

    @Mock
    BidderService bidderService;


    @Test
    public void should_return_payment_succeeded_response () {

        PaymentDTO paymentDTO = PaymentDTO.builder().paymentAmount(68.53).bankAccount("ACQUISITION").build();
        Mockito.when(bidderService.handlePayment(anyString(),anyString(),anyDouble()))
                .thenReturn(CommonResponse.builder().code(MessageInfoType.PAYMENT_SUCCEEDED.getCode()).message(MessageInfoType.PAYMENT_SUCCEEDED.getName()).build());

        CommonResponse commonResponse = bidderController.depositPayment("11223344", paymentDTO);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.PAYMENT_SUCCEEDED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.PAYMENT_SUCCEEDED.getName());
    }

    @Test
    public void should_return_order_not_exist_response_when_payment_give_not_exist_id () {
        PaymentDTO paymentDTO = PaymentDTO.builder().paymentAmount(68.53).bankAccount("ACQUISITION").build();
        Mockito.when(bidderService.handlePayment(anyString(),anyString(),anyDouble()))
                .thenReturn(CommonResponse.builder().code(MessageInfoType.ORDER_NOT_EXIST.getCode()).message(MessageInfoType.ORDER_NOT_EXIST.getName()).build());

        CommonResponse commonResponse = bidderController.depositPayment("11223344", paymentDTO);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
    }

    @Test
    public void should_return_not_sufficient_funds_response () {

        PaymentDTO paymentDTO = PaymentDTO.builder().paymentAmount(68.53).bankAccount("ACQUISITION").build();
        Mockito.when(bidderService.handlePayment(anyString(),anyString(),anyDouble()))
                .thenReturn(CommonResponse.builder().code(MessageInfoType.NOT_SUFFICIENT_FUNDS.getCode()).message(MessageInfoType.NOT_SUFFICIENT_FUNDS.getName()).build());

        CommonResponse commonResponse = bidderController.depositPayment("11223344", paymentDTO);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.NOT_SUFFICIENT_FUNDS.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.NOT_SUFFICIENT_FUNDS.getName());
    }

    @Test
    public void should_return_payment_system_exception_response () {
        PaymentDTO paymentDTO = PaymentDTO.builder().paymentAmount(68.53).bankAccount("ACQUISITION").build();
        Mockito.when(bidderService.handlePayment(anyString(),anyString(),anyDouble()))
                .thenReturn(CommonResponse.builder().code(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getCode()).message(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getName()).build());

        CommonResponse commonResponse = bidderController.depositPayment("11223344", paymentDTO);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getName());
    }

    @Test
    public void should_return_sign_succeeded_response_when_user_send_order_sign_request () {
        OrderSignDTO orderSignDTO = OrderSignDTO.builder().signName("小明").signTime("2023-3-19 12:33:00").build();
        Mockito.when(bidderService.handleSign(anyString(),anyString(),anyString()))
                .thenReturn(CommonResponse.builder().code(MessageInfoType.SIGN_SUCCEEDED.getCode()).message(MessageInfoType.SIGN_SUCCEEDED.getName()).build());

        CommonResponse commonResponse = bidderController.dealSign("11223344", orderSignDTO);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_SUCCEEDED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_SUCCEEDED.getName());
    }

    @Test
    public void should_return_order_not_exist_response_when_user_send_reimbursement_request_when_payment_give_not_exist_id () {
        PaymentDTO paymentDTO = PaymentDTO.builder().paymentAmount(68.53).bankAccount("ACQUISITION").build();
        Mockito.when(bidderService.handlePayment(anyString(),anyString(),anyDouble()))
                .thenReturn(CommonResponse.builder().code(MessageInfoType.ORDER_NOT_EXIST.getCode()).message(MessageInfoType.ORDER_NOT_EXIST.getName()).build());

        CommonResponse commonResponse = bidderController.depositPayment("11223344", paymentDTO);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
    }


    @Test
    public void should_return_sign_failed_when_user_send_order_sign_request () {
        OrderSignDTO orderSignDTO = OrderSignDTO.builder().signName("小明").signTime("2023-3-19 12:33:00").build();
        Mockito.when(bidderService.handleSign(anyString(),anyString(),anyString()))
                .thenReturn(CommonResponse.builder().code(MessageInfoType.SIGN_FAILED.getCode()).message(MessageInfoType.SIGN_FAILED.getName()).build());

        CommonResponse commonResponse = bidderController.dealSign("11223344", orderSignDTO);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_FAILED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_FAILED.getName());
    }

}