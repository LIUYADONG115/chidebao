package com.tw.heima.chidebao.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.heima.chidebao.common.enums.OrderStatus;
import com.tw.heima.chidebao.common.enums.SignStatus;
import com.tw.heima.chidebao.controller.model.CommonResponse;
import com.tw.heima.chidebao.common.enums.MessageInfoType;
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
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:48 下午
 */

@Service
public class FoodOrderService {

    private final OrderProcessRepo orderProcessRepo;
    private final MessageRepo messageRepo;
    private final FoodBookClient foodBookClient;
    private final MessageProvider messageProvider;

    public FoodOrderService(OrderProcessRepo orderProcessRepo, MessageRepo messageRepo, FoodBookClient foodBookClient, MessageProvider messageProvider) {
        this.orderProcessRepo = orderProcessRepo;
        this.messageRepo = messageRepo;
        this.foodBookClient = foodBookClient;
        this.messageProvider = messageProvider;
    }


    public CommonResponse handlePayment(String orderId, String bankAccount, Double paymentAmount) {
        OrderEntity orderEntity = orderProcessRepo.findByOrderId(orderId);
        return Optional.ofNullable(orderEntity)
                .filter(entity -> OrderStatus.ORDER_NOT_PAYMENT.getCode() == entity.getPaymentStatus())
                .map(entity -> payOrder(entity, bankAccount, paymentAmount))
                .orElseGet(() -> CommonResponse.builder().code(MessageInfoType.ORDER_NOT_EXIST.getCode()).message(MessageInfoType.ORDER_NOT_EXIST.getName()).build());
    }

    private CommonResponse payOrder(OrderEntity order, String bankAccount, Double paymentAmount) {
        try {
            order.setPaymentStatus(OrderStatus.ORDER_PAYING.getCode());
            orderProcessRepo.save(order);
            PaymentRequestInfo paymentRequestInfo = PaymentRequestInfo.builder().bankAccount(bankAccount).paymentAmount(paymentAmount).build();
            foodBookClient.pay(paymentRequestInfo);
        } catch (PaymentException e) {
            order.setPaymentStatus(OrderStatus.ORDER_PAYMENT_FAILED.getCode());
            orderProcessRepo.save(order);
            if (e.getType().equals(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION)) {
                return CommonResponse.error(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION);
            }
            if (e.getType().equals(MessageInfoType.NOT_SUFFICIENT_FUNDS)) {
                return CommonResponse.error(MessageInfoType.NOT_SUFFICIENT_FUNDS);
            }
        }
        order.setPaymentStatus(OrderStatus.ORDER_PAYMENT_SUCCESS.getCode());
        orderProcessRepo.save(order);
        return CommonResponse.paymentSuccess();
    }

    public CommonResponse handleSign(String orderId, String signName, String signTime) {
        OrderEntity orderEntity = orderProcessRepo.findByOrderId(orderId);
        return Optional.ofNullable(orderEntity)
                .filter(entity -> entity.getPaymentStatus() == SignStatus.ORDER_NOT_SIGN.getCode())
                .map(entity -> signOrder(entity, signName, signTime))
                .orElseGet(() -> CommonResponse.builder().code(MessageInfoType.ORDER_NOT_EXIST.getCode()).message(MessageInfoType.ORDER_NOT_EXIST.getName()).build());
    }

    @SneakyThrows
    private CommonResponse signOrder(OrderEntity order,String signName, String signTime) {
        order.setSignStatus(SignStatus.ORDER_SIGNING.getCode());
        orderProcessRepo.save(order);
        Message message = Message.builder().messageStartTime(LocalDateTime.now()).orderId(order.getOrderId()).signTime(signTime).signName(signName).storeName(order.getStoreName()).topic(Message.Topic.SIGN_ORDER_TOPIC).tag(Message.Topic.SIGN_ORDER_TAG).build();
        MqResponse response = messageProvider.send(message);
        if (HttpStatus.OK.value() == response.getCode()) {
            messageRepo.save(MessageEntity.builder().businessId(order.getOrderId()).content(new ObjectMapper().writeValueAsString(message)).build());
            order.setSignStatus(SignStatus.ORDER_SIGN_SUCCESS.getCode());
            orderProcessRepo.save(order);
            return CommonResponse.signSuccess();
        }
        return CommonResponse.error(MessageInfoType.SIGN_FAILED);
    }

}

