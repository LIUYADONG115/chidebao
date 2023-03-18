package com.tw.heima.chidebao.domain;


import com.tw.heima.chidebao.common.enums.MessageInfoType;
import com.tw.heima.chidebao.controller.model.CommonResponse;
import com.tw.heima.chidebao.infrastructure.FoodBookClient;
import com.tw.heima.chidebao.infrastructure.MessageProvider;
import com.tw.heima.chidebao.infrastructure.MqResponse;
import com.tw.heima.chidebao.infrastructure.model.Message;
import com.tw.heima.chidebao.infrastructure.model.PaymentException;
import com.tw.heima.chidebao.infrastructure.model.PaymentRequestInfo;
import com.tw.heima.chidebao.infrastructure.model.entity.MessageEntity;
import com.tw.heima.chidebao.infrastructure.model.entity.MessageRepo;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderEntity;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderProcessRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-19 1:11 上午
 */
@SpringBootTest
public class FoodOrderServiceTest {
    @InjectMocks
    FoodOrderService foodOrderService;

    @Mock
    OrderProcessRepo orderProcessRepo;

    @Mock
    MessageRepo messageRepo;

    @Mock
    FoodBookClient foodBookClient;

    @Mock
    MessageProvider messageProvider;


    @Test
    public void should_return_payment_succeeded_when_order_status_is_not_payment() {
        Mockito.when(orderProcessRepo.findByOrderId("11223344")).thenReturn(OrderEntity.builder().orderId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());

        CommonResponse commonResponse = foodOrderService.handlePayment("11223344", "ACQUISITION", 34.67);

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.PAYMENT_SUCCEEDED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.PAYMENT_SUCCEEDED.getName());
        Mockito.verify(foodBookClient).pay(Mockito.any(PaymentRequestInfo.class));
        Mockito.verify(orderProcessRepo, Mockito.times(2)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_order_not_exist() {
        Mockito.when(orderProcessRepo.findByOrderId("11223344")).thenReturn(OrderEntity.builder().orderId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());

        CommonResponse commonResponse = foodOrderService.handlePayment("1", "ACQUISITION", 34.67);

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(0)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_not_sufficient_funds_when_order_status_is_not_payment() {
        Mockito.when(orderProcessRepo.findByOrderId("11223344")).thenReturn(OrderEntity.builder().orderId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        Mockito.when(foodBookClient.pay(Mockito.any(PaymentRequestInfo.class))).thenThrow(new PaymentException(MessageInfoType.NOT_SUFFICIENT_FUNDS));

        CommonResponse commonResponse = foodOrderService.handlePayment("11223344", "ACQUISITION", 34.67);

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.NOT_SUFFICIENT_FUNDS.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.NOT_SUFFICIENT_FUNDS.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(2)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_payment_system_exception_when_order_status_is_not_payment() {
        Mockito.when(orderProcessRepo.findByOrderId("11223344")).thenReturn(OrderEntity.builder().orderId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());

        Mockito.when(foodBookClient.pay(Mockito.any(PaymentRequestInfo.class))).thenThrow(new PaymentException(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION));
        CommonResponse commonResponse = foodOrderService.handlePayment("11223344", "ACQUISITION", 34.67);
        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION.getName());

        Mockito.verify(orderProcessRepo, Mockito.times(2)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_sign_succeeded_response() {
        Mockito.when(orderProcessRepo.findByOrderId("11223344")).thenReturn(OrderEntity.builder().orderId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        Mockito.when(messageProvider.send(Mockito.any(Message.class))).thenReturn(MqResponse.builder().code(200).message("消息发送成功").build());

        CommonResponse commonResponse = foodOrderService.handleSign("11223344", "小明","2023-3-19 12:33:00");

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_SUCCEEDED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_SUCCEEDED.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(2)).save(Mockito.any(OrderEntity.class));
        Mockito.verify(messageRepo).save(Mockito.any(MessageEntity.class));
    }

    @Test
    public void should_return_sign_order_not_exist() {
        Mockito.when(orderProcessRepo.findByOrderId("11223344")).thenReturn(OrderEntity.builder().orderId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        Mockito.when(foodBookClient.pay(Mockito.any(PaymentRequestInfo.class))).thenThrow(new PaymentException(MessageInfoType.ORDER_NOT_EXIST));

        CommonResponse commonResponse = foodOrderService.handleSign("1", "小明","2023-3-19 12:33:00");

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.ORDER_NOT_EXIST.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(0)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    public void should_return_sign_failed() {
        Mockito.when(orderProcessRepo.findByOrderId("11223344")).thenReturn(OrderEntity.builder().orderId("11223344").storeName("KFC").paymentAmount(18.56).paymentStatus(1).signStatus(1).build());
        Mockito.when(messageProvider.send(Mockito.any(Message.class))).thenReturn(MqResponse.builder().code(300).message("订单签收人与购买人不匹配").build());

        CommonResponse commonResponse = foodOrderService.handleSign("11223344", "小明","2023-3-19 12:33:00");

        Assertions.assertThat(commonResponse.getCode()).isEqualTo(MessageInfoType.SIGN_FAILED.getCode());
        Assertions.assertThat(commonResponse.getMessage()).isEqualTo(MessageInfoType.SIGN_FAILED.getName());
        Mockito.verify(orderProcessRepo, Mockito.times(1)).save(Mockito.any(OrderEntity.class));
    }

}