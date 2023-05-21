package com.tw.heima.chidebao.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.heima.chidebao.common.enums.MessageInfoType;
import com.tw.heima.chidebao.common.enums.OrderStatus;
import com.tw.heima.chidebao.common.enums.SignStatus;
import com.tw.heima.chidebao.controller.model.CommonResponse;
import com.tw.heima.chidebao.infrastructure.ThirdPaymentClient;
import com.tw.heima.chidebao.infrastructure.model.Message;
import com.tw.heima.chidebao.infrastructure.model.PaymentException;
import com.tw.heima.chidebao.infrastructure.model.PaymentRequestInfo;
import com.tw.heima.chidebao.infrastructure.model.entity.MessageEntity;
import com.tw.heima.chidebao.infrastructure.model.entity.MessageRepo;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderEntity;
import com.tw.heima.chidebao.infrastructure.model.entity.OrderProcessRepo;
import com.tw.heima.chidebao.infrastructure.rocketmq.MQProducerService;
import com.tw.heima.chidebao.infrastructure.rocketmq.MqException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.tw.heima.chidebao.common.enums.SignStatus.*;
import static com.tw.heima.chidebao.infrastructure.model.Message.Topic.SIGN_ORDER_TAG;
import static com.tw.heima.chidebao.infrastructure.model.Message.Topic.SIGN_ORDER_TOPIC;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-17 8:19 下午
 */
@Service
@Slf4j
public class BidderService {
    private final OrderProcessRepo orderProcessRepo;
    private final ThirdPaymentClient thirdPaymentClient;
    private final MQProducerService mqProducerService;
    private final MessageRepo messageRepo;

    public BidderService(OrderProcessRepo orderProcessRepo, ThirdPaymentClient thirdPaymentClient, MQProducerService mqProducerService, MessageRepo messageRepo) {
        this.orderProcessRepo = orderProcessRepo;
        this.thirdPaymentClient = thirdPaymentClient;
        this.mqProducerService = mqProducerService;
        this.messageRepo = messageRepo;
    }

    public CommonResponse handlePayment(String id, String bankAccount, Double paymentAmount) {
        OrderEntity orderEntity = orderProcessRepo.findByContractId(id);
        return Optional.ofNullable(orderEntity)
                .filter(entity -> OrderStatus.ORDER_NOT_PAYMENT.getCode() == entity.getPaymentStatus())
                .map(entity -> payOrder(entity, bankAccount, paymentAmount))
                .orElseGet(() -> CommonResponse.builder().code(MessageInfoType.ORDER_NOT_EXIST.getCode()).message(MessageInfoType.ORDER_NOT_EXIST.getName()).build());
    }

    private CommonResponse payOrder(OrderEntity order, String bankAccount, Double paymentAmount) {
        try {
            log.info("查到了orderEntity：orderEntity={}", order.toString());
            order.setPaymentStatus(OrderStatus.ORDER_PAYING.getCode());
            orderProcessRepo.save(order);
            log.info("存储正在支付orderEntity：orderEntity={}", order.toString());
            PaymentRequestInfo paymentRequestInfo = PaymentRequestInfo.builder().bankAccount(bankAccount).paymentAmount(paymentAmount).build();
            thirdPaymentClient.pay(paymentRequestInfo);
        } catch (PaymentException e) {
            order.setPaymentStatus(OrderStatus.ORDER_PAYMENT_FAILED.getCode());
            orderProcessRepo.save(order);
            log.info("存储支付失败orderEntity：orderEntity={}", order.toString());
            if (e.getType().equals(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION)) {
                log.info("支付失败原因是系统异常");
                System.out.println("支付失败原因是系统异常");
                return CommonResponse.error(MessageInfoType.PAYMENT_SYSTEM_EXCEPTION);
            }
            if (e.getType().equals(MessageInfoType.NOT_SUFFICIENT_FUNDS)) {
                log.info("支付失败原因是账户余额不足");
                return CommonResponse.error(MessageInfoType.NOT_SUFFICIENT_FUNDS);
            }
        }
        order.setPaymentStatus(OrderStatus.ORDER_PAYMENT_SUCCESS.getCode());
        orderProcessRepo.save(order);
        log.info("存储支付成功的orderEntity：orderEntity={}", order.toString());
        return CommonResponse.paymentSuccess();
    }

    public CommonResponse handleSign(String contractId, String signName, String signTime) {
        OrderEntity orderEntity = orderProcessRepo.findByContractId(contractId);
        return Optional.ofNullable(orderEntity)
                .filter(entity -> entity.getSignStatus() == SignStatus.ORDER_NOT_SIGN.getCode())
                .map(entity -> signOrder(entity, signName, signTime))
                .orElseGet(() -> CommonResponse.builder().code(MessageInfoType.ORDER_NOT_EXIST.getCode()).message(MessageInfoType.ORDER_NOT_EXIST.getName()).build());
    }

    @SneakyThrows
    private CommonResponse signOrder(OrderEntity order, String signName, String signTime) {
        log.info("查到了orderEntity：orderEntity={}", order.toString());
        order.setSignStatus(SignStatus.ORDER_SIGNING.getCode());
        orderProcessRepo.save(order);
        log.info("存储正在签署的orderEntity：orderEntity={}", order.toString());
        Message message = new Message(
                SIGN_ORDER_TOPIC,
                SIGN_ORDER_TAG,
                LocalDateTime.now(),
                order.getContractId(),
                signTime,
                signName,
                order.getStoreName());
        try {
            mqProducerService.sendAsyncMsg(message);
            log.info("MQ发送消息成功，将发送成功的message存储起来，返回正在签署");
            messageRepo.save(MessageEntity.builder().businessId(order.getContractId()).content(new ObjectMapper().writeValueAsString(message)).build());
            return CommonResponse.signIng();
        } catch (MqException exception) {
            log.info("MQ发送消息失败，返回签署失败");
            return CommonResponse.error(MessageInfoType.SIGN_FAILED);
        }
    }

    public CommonResponse getSignStatus(String contractId) {
        OrderEntity orderEntity = orderProcessRepo.findByContractId(contractId);
        if (orderEntity == null || orderEntity.getSignStatus() == ORDER_NOT_SIGN.getCode()) {
            return CommonResponse.builder().code(MessageInfoType.ORDER_NOT_EXIST.getCode()).message(MessageInfoType.ORDER_NOT_EXIST.getName()).build();
        } else if (orderEntity.getSignStatus() == ORDER_SIGNING.getCode()) {
            return CommonResponse.builder().code(MessageInfoType.SIGN_ING.getCode()).message(MessageInfoType.SIGN_ING.getName()).build();
        } else if (orderEntity.getSignStatus() == ORDER_SIGN_SUCCESS.getCode()) {
            return CommonResponse.builder().code(MessageInfoType.SIGN_SUCCEEDED.getCode()).message(MessageInfoType.SIGN_SUCCEEDED.getName()).build();
        } else {
            return CommonResponse.builder().code(MessageInfoType.SIGN_FAILED.getCode()).message(MessageInfoType.SIGN_FAILED.getName()).build();
        }
    }
}
