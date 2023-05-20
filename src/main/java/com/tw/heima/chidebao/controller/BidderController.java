package com.tw.heima.chidebao.controller;

import com.tw.heima.chidebao.controller.model.CommonResponse;
import com.tw.heima.chidebao.controller.model.OrderSignDTO;
import com.tw.heima.chidebao.controller.model.PaymentDTO;
import com.tw.heima.chidebao.domain.BidderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-17 7:37 下午
 */
@RestController
@RequestMapping("/bidder-contracts")
public class BidderController {

    @Resource
    private BidderService bidderService;

    /**
     * 请求支付保证金
     * @param userId
     * @param paymentDTO
     * @return
     */
    @PostMapping("{id}/deposit-payment-request")
    public CommonResponse depositPayment(@PathVariable("id") String userId, @RequestBody PaymentDTO paymentDTO) {

        return bidderService.handlePayment(userId, paymentDTO.getBankAccount(), paymentDTO.getPaymentAmount());
    }


    /**
     * 请求签署成交协议
     * @param userId
     * @param orderSignDTO
     * @return
     */
    @PostMapping("{id}/sign-deal-protocol-request")
    public CommonResponse dealSign(@PathVariable("id") String userId, @RequestBody OrderSignDTO orderSignDTO) {
        System.out.println("接收到的orderId："+userId);
        return bidderService.handleSign(userId, orderSignDTO.getSignName(),orderSignDTO.getSignTime());
    }


    /**
     * 查看成交协议签署结果
     * @param userId
     * @return
     */
    @GetMapping("{id}/sign-deal-protocol-request")
    public CommonResponse getDealSign(@PathVariable("id") String userId) {
        System.out.println("接收到的orderId："+userId);
        return bidderService.getSignStatus(userId);
    }
}
