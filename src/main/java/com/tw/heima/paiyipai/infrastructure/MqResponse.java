package com.tw.heima.paiyipai.infrastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MqResponse {
    private Integer code;
    private String message;
}
