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
     * @param orderId
     * @param paymentDTO
     * @return
     */
    @PostMapping("{id}/deposit-payment-request")
    public CommonResponse depositPayment(@PathVariable("id") String orderId, @RequestBody PaymentDTO paymentDTO) {

        return bidderService.handlePayment(orderId, paymentDTO.getBankAccount(), paymentDTO.getPaymentAmount());
    }


    /**
     * 请求签署成交协议
     * @param orderId
     * @param orderSignDTO
     * @return
     */
    @PostMapping("{id}/sign-deal-protocol-request")
    public CommonResponse dealSign(@PathVariable("id") String orderId, @RequestBody OrderSignDTO orderSignDTO) {
        System.out.println("接收到的orderId："+orderId);
        return bidderService.handleSign(orderId, orderSignDTO.getSignName(),orderSignDTO.getSignTime());
    }


    /**
     * 查看成交协议签署结果
     * @param orderId
     * @return
     */
    @GetMapping("{id}/sign-deal-protocol-request")
    public CommonResponse getDealSign(@PathVariable("id") String orderId) {
        System.out.println("接收到的orderId："+orderId);
        return bidderService.getSignStatus(orderId);
    }


}
