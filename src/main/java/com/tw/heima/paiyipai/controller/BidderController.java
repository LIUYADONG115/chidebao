package com.tw.heima.paiyipai.controller;

import com.tw.heima.paiyipai.controller.model.CommonResponse;
import com.tw.heima.paiyipai.controller.model.OrderSignDTO;
import com.tw.heima.paiyipai.controller.model.PaymentDTO;
import com.tw.heima.paiyipai.domain.BidderService;
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
     * @param id
     * @param paymentDTO
     * @return
     */
    @PostMapping("{id}/deposit-payment-request")
    public CommonResponse depositPayment(@PathVariable("id") String id, @RequestBody PaymentDTO paymentDTO) {

        return bidderService.handlePayment(id, paymentDTO.getBankAccount(), paymentDTO.getPaymentAmount());
    }


    /**
     * 请求签署成交协议
     * @param contractId
     * @param orderSignDTO
     * @return
     */
    @PostMapping("{id}/sign-deal-protocol-request")
    public CommonResponse dealSign(@PathVariable("id") String contractId, @RequestBody OrderSignDTO orderSignDTO) {
        System.out.println("接收到的orderId："+contractId);
        return bidderService.handleSign(contractId, orderSignDTO.getSignName(),orderSignDTO.getSignTime());
    }


    /**
     * 查看成交协议签署结果
     * @param contractId
     * @return
     */
    @GetMapping("{id}/sign-deal-protocol-request")
    public CommonResponse getDealSign(@PathVariable("id") String contractId) {
        System.out.println("接收到的orderId："+contractId);
        return bidderService.getSignStatus(contractId);
    }
}
