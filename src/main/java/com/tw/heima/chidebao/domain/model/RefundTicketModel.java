package com.tw.heima.chidebao.domain.model;

import com.tw.heima.chidebao.common.enums.OrderStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefundTicketModel {
    private String orderId;
    private Double amount;
    private OrderStatus status;
}
