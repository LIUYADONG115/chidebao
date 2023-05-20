package com.tw.heima.chidebao.infrastructure.model.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_ordinary_order")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    private Integer id;

    private String userId;

    private String storeName;

    private Double paymentAmount;

    private Integer paymentStatus;

    private Integer signStatus;
}
