package com.tw.heima.paiyipai.controller.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderSignDTO {
    private String signTime;
    private String signName;
}
