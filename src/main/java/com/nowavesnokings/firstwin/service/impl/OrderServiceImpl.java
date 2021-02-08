package com.nowavesnokings.firstwin.service.impl;

import com.nowavesnokings.firstwin.core.concurrent.local.LocalUser;
import com.nowavesnokings.firstwin.core.enumeration.OrderStatusType;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.enumeration.UserCouponStatusType;
import com.nowavesnokings.firstwin.core.exception.http.ForbiddenException;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.dao.OrderRepository;
import com.nowavesnokings.firstwin.dao.UserCouponRepository;
import com.nowavesnokings.firstwin.logic.IOrderChecker;
import com.nowavesnokings.firstwin.pojo.bo.PageCounter;
import com.nowavesnokings.firstwin.pojo.dto.OrderDTO;
import com.nowavesnokings.firstwin.pojo.dto.SkuPureDTO;
import com.nowavesnokings.firstwin.pojo.model.Order;
import com.nowavesnokings.firstwin.pojo.model.OrderSku;
import com.nowavesnokings.firstwin.pojo.model.QSku;
import com.nowavesnokings.firstwin.pojo.model.QUserCoupon;
import com.nowavesnokings.firstwin.pojo.model.Sku;
import com.nowavesnokings.firstwin.pojo.vo.OrderPureVO;
import com.nowavesnokings.firstwin.pojo.vo.OrderSimplifyVO;
import com.nowavesnokings.firstwin.pojo.vo.PagingDozerVO;
import com.nowavesnokings.firstwin.properties.FirstwinProperties;
import com.nowavesnokings.firstwin.service.OrderService;
import com.nowavesnokings.firstwin.service.SkuService;
import com.nowavesnokings.firstwin.util.DozerUtils;
import com.nowavesnokings.firstwin.util.OrderUtils;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.nowavesnokings.firstwin.pojo.model.QOrder.order;
/**
 * @author ssx
 * @version V1.0
 * @className OrderServiceImpl
 * @description 订单service实现类
 * @date 2021-01-17 17:05
 * @since 1.8
 */
@Service
public class OrderServiceImpl extends AbstractBaseService<Order, Long, OrderRepository> implements OrderService {
    /**
     * The Order checker.
     */
    @Autowired
    private ObjectProvider<IOrderChecker> orderChecker;

    /**
     * The Sku service.
     */
    @Autowired
    private SkuService skuService;

    /**
     * The User coupon repository.
     */
    @Autowired
    private UserCouponRepository userCouponRepository;

    /**
     * The Firstwin properties.
     */
    @Autowired
    private FirstwinProperties firstwinProperties;


    /**
     * 订单校验.
     *
     * @param uid      the uid
     * @param orderDTO the order dto
     */
    @Override
    public IOrderChecker isOk(Long uid, OrderDTO orderDTO) {
        List<SkuPureDTO> skuPureDTOs = orderDTO.getSkuPureDTOs();
        List<Long> skuIds = skuPureDTOs.stream().map(SkuPureDTO::getId).collect(Collectors.toList());
        List<Sku> skus = skuService.findAllById(skuIds);
        //调用校验类校验是否可以下订单
        IOrderChecker orderCheckerObject = orderChecker.getObject(uid, orderDTO, skus);
        orderCheckerObject.isOk();
        return orderCheckerObject;
    }

    /**
     * 生成订单.
     *
     * @param orderChecker the order checker
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long placeOrder(Long uid, OrderDTO orderDTO, IOrderChecker orderChecker) {
        Date now = new Date();
        Order order = Order.builder()
                .orderNo(OrderUtils.getOrderNo())
                .userId(uid)
                .couponId(orderDTO.getCouponId())
                .postage(BigDecimal.ZERO)
                .snapImg(orderChecker.getLeaderImg())
                .snapTitle(orderChecker.getLeaderTitle())
                .totalCount(orderChecker.getOrderCount())
                .finalTotalPrice(orderDTO.getFinalTotalPrice())
                .totalPrice(orderDTO.getTotalPrice())
                .status(OrderStatusType.UN_PAID.getCode())
                .placedTime(now)
                .expiredTime(DateUtils.addSeconds(now, firstwinProperties.getOrderOutDateLimit()))
                .build();
        List<OrderSku> orderSkus = orderChecker.getOrderSkus();
        order.setSnapItems(orderSkus);
        order.setSnapAddress(orderDTO.getOrderAddressDTO());
        this.save(order);
        //减库存
        this.reduceStock(orderSkus);
        //核销优惠券
        if (orderDTO.getCouponId() != null) {
            this.writeOffCoupon(uid, orderDTO.getCouponId(), order.getId());
        }
        return order.getId();
    }

    /**
     * 获取未支付订单.
     *
     * @param pageCounter the page counter
     * @return the unpaid order
     */
    @Override
    public PagingDozerVO<Order, OrderSimplifyVO> getUnpaidOrder(PageCounter pageCounter) {
        PageRequest pageRequest = PageRequest.of(pageCounter.getPage(), pageCounter.getSize(), Sort.by(order.createTime.getMetadata().getName()).descending());
        Long uid = LocalUser.getUser().getId();
        Page<Order> orders = this.findAll(order.userId.eq(uid)
                .and(order.status.eq(OrderStatusType.UN_PAID.getCode()))
                .and(order.expiredTime.after(new Date())), pageRequest);

        PagingDozerVO<Order, OrderSimplifyVO> pagingDozerVO = new PagingDozerVO<>(orders, OrderSimplifyVO.class);
        pagingDozerVO.getItems().forEach(o -> ((OrderSimplifyVO) o).setPeriod(firstwinProperties.getOrderOutDateLimit()));
        return pagingDozerVO;
    }

    /**
     * 获取除未支付订单列表.
     *
     * @param pageCounter the page counter
     * @param orderStatusType      the status
     * @return the orders by status
     */
    @Override
    public PagingDozerVO<Order, OrderSimplifyVO> getOrdersByStatus(PageCounter pageCounter, OrderStatusType orderStatusType) {
        PageRequest pageRequest = PageRequest.of(pageCounter.getPage(), pageCounter.getSize(), Sort.by(order.createTime.getMetadata().getName()).descending());
        Long uid = LocalUser.getUser().getId();
        Predicate predicate = ExpressionUtils.allOf(order.userId.eq(uid));
        if (!OrderStatusType.ALL.equals(orderStatusType)) {
            predicate = ExpressionUtils.and(predicate, order.status.eq(orderStatusType.getCode()));
        }
        Page<Order> orders = this.findAll(predicate, pageRequest);
        PagingDozerVO<Order, OrderSimplifyVO> pagingDozerVO = new PagingDozerVO<>(orders, OrderSimplifyVO.class);
        pagingDozerVO.getItems().forEach(o -> ((OrderSimplifyVO) o).setPeriod(firstwinProperties.getOrderOutDateLimit()));
        return pagingDozerVO;
    }

    /**
     * 获取订单详情.
     *
     * @param id the id
     * @return the order detail by id
     */
    @Override
    public OrderPureVO getOrderDetailById(Long id) {
        Long uid = LocalUser.getUser().getId();
        Order order = this.getOrderByIdAndUserId(id, uid);
        OrderPureVO orderPureVO = DozerUtils.convertBean(order, OrderPureVO.class);
        assert orderPureVO != null;
        orderPureVO.setPeriod(firstwinProperties.getOrderOutDateLimit());
        return orderPureVO;
    }

    @Override
    public Order getOrderByIdAndUserId(Long id, Long uid) {
        return this.findOne(order.userId.eq(uid)
                .and(order.id.eq(id))).orElseThrow(() -> new NotFoundException(UnifyResponseCode.ORDER_NOT_FOUND.getCode()));
    }

    /**
     * 核销优惠券.
     *
     * @param uid      the uid
     * @param couponId the coupon id
     * @param orderId  the order id
     */
    private void writeOffCoupon(Long uid, Long couponId, Long orderId) {
        userCouponRepository.update(query -> {
            QUserCoupon userCoupon = QUserCoupon.userCoupon;
            long count = query.set(userCoupon.status, UserCouponStatusType.ALREADY_USE.getCode())
                    .set(userCoupon.orderId, orderId)
                    .where(userCoupon.couponId.eq(couponId)
                            .and(userCoupon.userId.eq(uid))
                            .and(userCoupon.status.eq(UserCouponStatusType.NO_USE.getCode())
                                    .and(userCoupon.orderId.isNull()))).execute();
            if (count != 1) {
                throw new ForbiddenException(UnifyResponseCode.COUPON_WRITE_OFF_FAILURE.getCode());
            }
        });


    }

    /**
     * 减库存.
     *
     * @param orderSkus the order skus
     */
    private void reduceStock(List<OrderSku> orderSkus) {
        orderSkus.forEach(orderSku -> this.skuService.update(query -> {
            QSku sku = QSku.sku;
            long count = query.set(sku.stock, sku.stock.subtract(orderSku.getCount()))
                    .where(sku.id.eq(orderSku.getId()).and(sku.stock.goe(orderSku.getCount())))
                    .execute();
            if (count != 1) {
                throw new ForbiddenException(UnifyResponseCode.ORDER_HAVE_NO_STOCK_GOODS.getCode());
            }
        }));
    }
}
