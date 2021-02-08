package com.nowavesnokings.firstwin.logic;

import com.google.common.collect.Lists;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.ForbiddenException;
import com.nowavesnokings.firstwin.pojo.bo.SkuOrderBO;
import com.nowavesnokings.firstwin.pojo.dto.OrderDTO;
import com.nowavesnokings.firstwin.pojo.dto.SkuPureDTO;
import com.nowavesnokings.firstwin.pojo.model.OrderSku;
import com.nowavesnokings.firstwin.pojo.model.Sku;
import com.nowavesnokings.firstwin.properties.FirstwinProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ssx
 * @version V1.0
 * @className OrderChecker
 * @description 订单校验器
 * @date 2021-01-25 9:14
 * @since 1.8
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderChecker implements IOrderChecker {
    private Long uid;

    private OrderDTO orderDTO;

    private List<Sku> skus;

    private List<OrderSku> orderSkus = Lists.newArrayList();

    private Integer orderCount = 0;


    @Autowired
    private ObjectProvider<ICouponChecker> couponChecker;

    @Autowired
    private FirstwinProperties firstwinProperties;

    public OrderChecker(Long uid, OrderDTO orderDTO, List<Sku> skus) {
        this.uid = uid;
        this.orderDTO = orderDTO;
        this.skus = skus;
    }

    /**
     * 是否通过验证.
     */
    @Override
    public void isOk() {
        //封装订单sku对象
        List<SkuPureDTO> skuPureDTOs = orderDTO.getSkuPureDTOs();
        Map<Long, SkuPureDTO> skuPrueMap = skuPureDTOs.stream().collect(Collectors.toMap(SkuPureDTO::getId, skuPureDTO -> skuPureDTO));
        List<SkuOrderBO> skuOrderBOs = this.skus.stream().map(sku -> new SkuOrderBO(sku, skuPrueMap.get(sku.getId()))).collect(Collectors.toList());
        BigDecimal serverTotalPrice = BigDecimal.ZERO;
        //验证商品是否下架
        checkHaveOffShelfGoods(skuPureDTOs);
        for (Sku sku : this.skus) {
            //验证商品是否售罄
            this.checkSoldOutSku(sku);
            //验证购买数量是否超过库存
            SkuPureDTO skuPureDTO = skuPrueMap.get(sku.getId());
            beyondSkuStock(sku, skuPureDTO);
            //计算商品总价
            serverTotalPrice = serverTotalPrice.add(sku.getActualPrice().multiply(new BigDecimal(skuPureDTO.getCount())));
            orderCount += skuPureDTO.getCount();
            orderSkus.add(new OrderSku(sku, skuPureDTO));
        }
        //验证商品是否超卖
        totalPriceIsOk(serverTotalPrice);
        if (orderCount > this.firstwinProperties.getOrderBuyNumber()) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_OVER_MAX_BUY_NUMBER.getCode());
        }
        //如果使用了优惠券，验证优惠券使用
        if (this.orderDTO.getCouponId() != null) {
            ICouponChecker checker = couponChecker.getObject(this.uid, this.orderDTO.getCouponId());
            checker.isOk();
            checker.canBeUsed(skuOrderBOs, serverTotalPrice);
            checker.checkFinalTotalPrice(this.orderDTO.getFinalTotalPrice(), serverTotalPrice);
        }
    }

    /**
     * 获取头商品标题.
     *
     * @return the leader title
     */
    @Override
    public String getLeaderTitle() {
        return this.skus.get(0).getTitle();
    }

    /**
     * 获取头商品图片.
     *
     * @return the leader img
     */
    @Override
    public String getLeaderImg() {
        return this.skus.get(0).getImg();
    }

    /**
     * 获取订单商品列表.
     *
     * @return the order skus
     */
    @Override
    public List<OrderSku> getOrderSkus() {
        return this.orderSkus;
    }

    /**
     * 获取订单数量.
     *
     * @return the order count
     */
    @Override
    public Integer getOrderCount() {
        return this.orderCount;
    }

    private void totalPriceIsOk(BigDecimal serverTotalPrice) {
        if (serverTotalPrice.compareTo(this.orderDTO.getTotalPrice()) != 0) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_AMOUNT_NOT_EQUAL_REAL_AMOUNT.getCode());
        }
    }

    private void beyondSkuStock(Sku sku, SkuPureDTO skuPureDTO) {
        if (sku.getStock() < skuPureDTO.getCount()) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_HAVE_NO_STOCK_GOODS.getCode());
        }
    }

    private void checkSoldOutSku(Sku sku) {
        if (sku.getStock() <= 0) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_HAVE_NO_STOCK_GOODS.getCode());
        }
    }

    private void checkHaveOffShelfGoods(List<SkuPureDTO> skuPureDTOs) {
        int skuPureSize = skuPureDTOs.size();
        int skuSize = this.skus.size();
        if (skuSize < skuPureSize) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_HAVE_OFF_SHELF_GOODS.getCode());
        }
    }
}
