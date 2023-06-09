package com.tw.heima.paiyipai.infrastructure.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<MessageEntity, Integer> {

    MessageEntity findByBusinessId(String businessId);
}
