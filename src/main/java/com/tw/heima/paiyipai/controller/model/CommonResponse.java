package com.tw.heima.paiyipai.controller.model;

import com.tw.heima.paiyipai.common.enums.MessageInfoType;
import lombok.Builder;
import lombok.Data;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 7:00 下午
 */
@Builder
@Data
public class CommonResponse {
    private String code;
    private String message;

    public static CommonResponse paymentSuccess() {
        return CommonResponse.builder().code(MessageInfoType.PAYMENT_SUCCEEDED.getCode()).message(MessageInfoType.PAYMENT_SUCCEEDED.getName()).build();
    }

    public static CommonResponse signIng() {
        return CommonResponse.builder().code(MessageInfoType.SIGN_ING.getCode()).message(MessageInfoType.SIGN_ING.getName()).build();
    }

    public static CommonResponse signSuccess() {
        return CommonResponse.builder().code(MessageInfoType.SIGN_SUCCEEDED.getCode()).message(MessageInfoType.SIGN_SUCCEEDED.getName()).build();
    }


    public static CommonResponse error(MessageInfoType type) {
        return CommonResponse.builder().code(type.getCode()).message(type.getName()).build();
    }
}
