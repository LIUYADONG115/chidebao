package com.tw.heima.chidebao.domain;

import com.tw.heima.chidebao.common.enums.MessageInfoType;
import com.tw.heima.chidebao.controller.model.CommonResponse;
import com.tw.heima.chidebao.infrastructure.MqResponse;
import com.tw.heima.chidebao.infrastructure.ThirdPaymentClient;
import com.tw.heima.chidebao.infrastructure.model.Message;
import com.tw.heima.chidebao.infrastructure.model.PaymentException;
import com.tw.heima.chidebao.infrastructure.model.PaymentRequestInfo;
import com.tw.heima.chidebao.infrastructure.model.entity.MessageEntity;
import com.tw.heima.chidebao.infrastructure.model.entity.MessageRepo;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderEntity;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderProcessRepo;
import com.tw.heima.chidebao.infrastructure.rocketmq.MQProducerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-20 7:49 下午
 */
@SpringBootTest
class BidderServiceTest {
    @InjectMocks
    BidderService bidderService;

    @Mock
    OrderProcessRepo orderProcessRepo;

    @Mock
    ThirdPaymentClient thirdPaymentClient;

    @Mock
    MQProducerService mqProducerService;

    @Mock
    MessageRepo messageRepo;


    @Test
    public void should_return_payment_succeeded_when_order_status_is_not_payment() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());

        CommonResponse commonResponse = bidderService.handlePayment("11223344", "ACQUISITION", 34.67);

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.PAYMENT_SUCCEEDED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.PAYMENT_SUCCEEDED.getName());
        Mockito.verify(thirdPaymentClient).pay(Mockito.any(PaymentRequestInfo.class));
        Mockito.verify(orderProcessRepo, Mockito.times(2)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_order_not_exist() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());

        CommonResponse commonResponse = bidderService.handlePayment("1", "ACQUISITION", 34.67);

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(0)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_not_sufficient_funds_when_order_status_is_not_payment() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        Mockito.when(thirdPaymentClient.pay(Mockito.any(PaymentRequestInfo.class))).thenThrow(new PaymentException(MessageInfoType.NOT_SUFFICIENT_FUNDS));

        CommonResponse commonResponse = bidderService.handlePayment("11223344", "ACQUISITION", 34.67);

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.NOT_SUFFICIENT_FUNDS.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.NOT_SUFFICIENT_FUNDS.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(2)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_payment_system_exception_when_order_status_is_not_payment() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());

        Mockito.when(thirdPaymentClient.pay(Mockito.any(PaymentRequestInfo.class))).thenThrow(new PaymentException(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION));
        CommonResponse commonResponse = bidderService.handlePayment("11223344", "ACQUISITION", 34.67);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getName());

        Mockito.verify(orderProcessRepo, Mockito.times(2)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_sign_succeeded_response() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        Mockito.when(mqProducerService.sendAsyncMsg(Mockito.any(Message.class))).thenReturn(MqResponse.builder().code(200).message("消息发送成功").build());

        CommonResponse commonResponse = bidderService.handleSign("11223344", "小明","2023-3-19 12:33:00");

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_ING.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_ING.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(1)).save(Mockito.any(OrderEntity.class));
        Mockito.verify(messageRepo).save(Mockito.any(MessageEntity.class));
    }

    @Test
    public void should_return_sign_order_not_exist() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        Mockito.when(thirdPaymentClient.pay(Mockito.any(PaymentRequestInfo.class))).thenThrow(new PaymentException(MessageInfoType.ORDER_NOT_EXIST));

        CommonResponse commonResponse = bidderService.handleSign("1", "小明","2023-3-19 12:33:00");

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(0)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_sign_order_not_exist_when_contractId_not_found() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        CommonResponse commonResponse = bidderService.getSignStatus("1");
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
    }

    @Test
    public void should_return_sign_success_when_user_not_sign() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        CommonResponse commonResponse = bidderService.getSignStatus("11223344");
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
    }

    @Test
    public void should_return_sign_success_when_user_signing() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(2).build());
        CommonResponse commonResponse = bidderService.getSignStatus("11223344");
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_ING.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_ING.getName());
    }

    @Test
    public void should_return_sign_success_when_user_sign_success() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(3).build());
        CommonResponse commonResponse = bidderService.getSignStatus("11223344");
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_SUCCEEDED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_SUCCEEDED.getName());
    }

    @Test
    public void should_return_sign_success_when_user_sign_failed() {
        Mockito.when(orderProcessRepo.findByContractId("11223344")).thenReturn(OrderEntity.builder().contractId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(4).build());
        CommonResponse commonResponse = bidderService.getSignStatus("11223344");
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_FAILED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_FAILED.getName());
    }

}