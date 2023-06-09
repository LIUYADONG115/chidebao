package com.tw.heima.paiyipai.infrastructure.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProcessRepo extends JpaRepository<OrderEntity, Integer> {

    OrderEntity findByContractId(String contractId);
}
