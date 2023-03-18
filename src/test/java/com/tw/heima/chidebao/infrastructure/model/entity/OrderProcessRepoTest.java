package com.tw.heima.chidebao.infrastructure.model.entity;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-19 2:05 上午
 */
@SpringBootTest
public class OrderProcessRepoTest {
    @Autowired
    OrderProcessRepo orderProcessRepo;

    @Test
    public void should_find_all_order_data_when_prepare_insert () {
        OrderEntity entity = OrderEntity.builder().id(1).orderId("326118").storeName("KFC").paymentAmount(35.42).paymentStatus(1).signStatus(1).build();
        orderProcessRepo.save(entity);
        OrderEntity originOrder = orderProcessRepo.findByOrderId("326118");

        assertThat(originOrder.getOrderId()).isEqualTo("326118");
        assertThat(originOrder.getStoreName()).isEqualTo("KFC");
        assertThat(originOrder.getPaymentAmount()).isEqualTo(35.42);
        assertThat(originOrder.getPaymentStatus()).isEqualTo(1);
        assertThat(originOrder.getSignStatus()).isEqualTo(1);
    }

    @AfterEach
    public void clear()  {
        orderProcessRepo.deleteAll();
    }
}