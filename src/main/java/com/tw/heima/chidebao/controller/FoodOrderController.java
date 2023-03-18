package com.tw.heima.chidebao.controller;

import com.tw.heima.chidebao.controller.model.CommonResponse;
import com.tw.heima.chidebao.controller.model.PaymentDTO;
import com.tw.heima.chidebao.controller.model.OrderSignDTO;
import com.tw.heima.chidebao.domain.FoodOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−03-18 6:47 下午
 */
@RestController
@RequestMapping("/purchase-orders")
public class FoodOrderController {
    @Resource
    private final FoodOrderService foodOrderService;

    public FoodOrderController(FoodOrderService foodOrderService) {
        this.foodOrderService = foodOrderService;
    }

    @PostMapping("{pid}/payment")
    public CommonResponse orderPayment(@PathVariable("pid") String orderId, @RequestBody PaymentDTO paymentDTO) {

        return foodOrderService.handlePayment(orderId, paymentDTO.getBankAccount(), paymentDTO.getPaymentAmount());
    }

    @PostMapping("{pid}/sign/confirmation")
    public CommonResponse orderSign(@PathVariable("pid") String orderId, @RequestBody OrderSignDTO orderSignDTO) {
        System.out.println("接收到的orderId："+orderId);
        return foodOrderService.handleSign(orderId, orderSignDTO.getSignName(),orderSignDTO.getSignTime());
    }
}
