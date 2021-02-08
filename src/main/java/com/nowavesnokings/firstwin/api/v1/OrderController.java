package com.nowavesnokings.firstwin.api.v1;

import com.nowavesnokings.firstwin.core.annotation.ScopeLevel;
import com.nowavesnokings.firstwin.core.concurrent.local.LocalUser;
import com.nowavesnokings.firstwin.core.enumeration.OrderStatusType;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.ForbiddenException;
import com.nowavesnokings.firstwin.core.response.CreatedResponse;
import com.nowavesnokings.firstwin.logic.IOrderChecker;
import com.nowavesnokings.firstwin.pojo.bo.PageCounter;
import com.nowavesnokings.firstwin.pojo.dto.OrderDTO;
import com.nowavesnokings.firstwin.pojo.model.Order;
import com.nowavesnokings.firstwin.pojo.vo.OrderPureVO;
import com.nowavesnokings.firstwin.pojo.vo.OrderSimplifyVO;
import com.nowavesnokings.firstwin.pojo.vo.OrderVO;
import com.nowavesnokings.firstwin.pojo.vo.PagingDozerVO;
import com.nowavesnokings.firstwin.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssx
 * @version V1.0
 * @className OrderController
 * @description 订单控制器层
 * @date 2021-01-17 14:57
 * @since 1.8
 */
@RestController
@RequestMapping(value = "order")
@Api(value = "订单接口", tags = {"订单相关接口"})
public class OrderController {
    /**
     * The Order service.
     */
    @Autowired
    private OrderService orderService;

    /**
     * 生产订单.
     *
     * @param orderDTO the order dto
     * @return the created response
     */
    @PostMapping("/place")
    @ScopeLevel
    @ApiOperation(httpMethod = "POST", value = "生产订单", notes = "用于生产订单接口")
    public CreatedResponse<OrderVO> placeOrder(@RequestBody OrderDTO orderDTO) {
        Long uid = LocalUser.getUser().getId();
        IOrderChecker orderChecker = orderService.isOk(uid, orderDTO);
        Long orderId = orderService.placeOrder(uid, orderDTO, orderChecker);
        OrderVO orderVO = new OrderVO(orderId);
        return new CreatedResponse<>(orderVO);
    }

    /**
     * 查询未支付订单.
     *
     * @param start the start
     * @param count the count
     * @return the unpaid order
     */
    @ScopeLevel
    @GetMapping("/status/unpaid")
    @ApiOperation(httpMethod = "GET", value = "查询未支付订单", notes = "用于查未支付订单接口")
    public PagingDozerVO<Order, OrderSimplifyVO> getUnpaidOrder(@RequestParam(value = "start", required = true, defaultValue = "0") Integer start,
                                                                @RequestParam(value = "count", required = true, defaultValue = "10") Integer count) {
        PageCounter pageCounter = new PageCounter(start, count);
        return this.orderService.getUnpaidOrder(pageCounter);
    }

    /**
     * Gets order by status.
     *
     * @return the order by status
     */
    @ScopeLevel
    @ApiOperation(httpMethod = "GET", value = "获取除未支付外得订单列表", notes = "根据状态获取除未支付订单外其他订单列表")
    @GetMapping("/by/status/{status}")
    public PagingDozerVO<Order, OrderSimplifyVO> getOrderByStatus(@PathVariable(value = "status") Integer status,
                                                                  @RequestParam(value = "start", defaultValue = "0")Integer start,
                                                                  @RequestParam(value = "count", defaultValue = "10")Integer count) {
        OrderStatusType orderStatusType = OrderStatusType.getOrderStatusTypeByCode(status);
        if (orderStatusType == null || orderStatusType.equals(OrderStatusType.UN_PAID)) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_UN_SUPPORT_ORDER_STATUS.getCode());
        }
        PageCounter pageCounter = new PageCounter(start, count);
        return this.orderService.getOrdersByStatus(pageCounter, orderStatusType);
    }

    @ScopeLevel
    @ApiOperation(httpMethod = "GET", value = "获取订单详情", notes = "通过订单id获取订单详情")
    @GetMapping("/detail/by/id/{id}")
    public OrderPureVO getOrderDetailById(@PathVariable(value = "id") Long id) {
        return this.orderService.getOrderDetailById(id);
    }
}
