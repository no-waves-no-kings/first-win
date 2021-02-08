package com.nowavesnokings.firstwin.api.v1;

import com.nowavesnokings.firstwin.core.annotation.ScopeLevel;
import com.nowavesnokings.firstwin.pojo.vo.UnifyPaymentVO;
import com.nowavesnokings.firstwin.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssx
 * @version V1.0
 * @className PaymentController
 * @description 支付相关控制器
 * @date 2021-02-05 9:44
 * @since 1.8
 */
@RestController
@RequestMapping(value = "/payment")
@Api(value = "支付相关接口", tags = {"处理支付相关接口"})
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @ScopeLevel
    @ApiOperation(value = "支付预处理", notes = "处理支付预处理生成签名")
    @PostMapping("/prepay/{orderId}")
    public UnifyPaymentVO prepayHandle(@PathVariable(value = "orderId") Long orderId) {
        return this.paymentService.prepayHandle(orderId);
    }
}
