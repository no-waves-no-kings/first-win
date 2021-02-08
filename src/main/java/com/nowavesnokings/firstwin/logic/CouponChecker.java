package com.nowavesnokings.firstwin.logic;

import com.nowavesnokings.firstwin.core.enumeration.CouponType;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.enumeration.UserCouponStatusType;
import com.nowavesnokings.firstwin.core.exception.http.ForbiddenException;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.core.exception.http.ParameterException;
import com.nowavesnokings.firstwin.core.money.IMoneyDiscount;
import com.nowavesnokings.firstwin.dao.UserCouponRepository;
import com.nowavesnokings.firstwin.pojo.bo.SkuOrderBO;
import com.nowavesnokings.firstwin.pojo.model.CategoryCoupon;
import com.nowavesnokings.firstwin.pojo.model.Coupon;
import com.nowavesnokings.firstwin.pojo.model.QCoupon;
import com.nowavesnokings.firstwin.pojo.model.UserCoupon;
import com.nowavesnokings.firstwin.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author ssx
 * @version V1.0
 * @className CouponChecker
 * @description 优惠券检查类
 * @date 2021-01-19 14:24
 * @since 1.8
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CouponChecker implements ICouponChecker {
    /**
     * The Uid.
     */
    private Long uid;

    /**
     * The Cid.
     */
    private Long cid;
    /**
     * The Check coupon.
     */
    private Coupon checkCoupon;

    /**
     * The User coupon.
     */
    private UserCoupon userCoupon;

    /**
     * The Sku order bo list.
     */
    private List<SkuOrderBO> skuOrderBOList;

    /**
     * The Coupon service.
     */
    @Autowired
    private CouponService couponService;

    /**
     * The User coupon repository.
     */
    @Autowired
    private UserCouponRepository userCouponRepository;

    /**
     * Instantiates a new Coupon checker.
     *
     * @param uid the uid
     * @param cid the cid
     */
    public CouponChecker(Long uid, Long cid) {
        this.uid = uid;
        this.cid = cid;
    }

    /**
     * 根据用户id和优惠券id初始化检查器.
     *
     */
    @Override
    public void isOk() {
        Date now = new Date();
        this.checkCoupon = couponService.findOne(QCoupon.coupon.id.eq(this.cid)
                .and(QCoupon.coupon.startTime.before(now))
                .and(QCoupon.coupon.endTime.after(now))).orElseThrow(() -> new ParameterException(UnifyResponseCode.COUPON_NOT_FOUND.getCode()));
        this.userCoupon = userCouponRepository.getUserCouponByCouponIdAndUserIdAndStatus(this.cid, this.uid, UserCouponStatusType.NO_USE.getCode())
                .orElseThrow(() -> new ParameterException(UnifyResponseCode.USER_HAVE_NO_COUPON.getCode()));
    }


    /**
     * 校验最终产品价格是否正确.
     *
     * @param orderFinalTotalPrice the order final total price
     * @param serverTotalPrice     the server total price
     * @return the boolean
     */
    @Override
    public void checkFinalTotalPrice(BigDecimal orderFinalTotalPrice, BigDecimal serverTotalPrice) {
        BigDecimal serverFinalTotalPrice = null;
        //根据优惠券 计算商品最终价格
        CouponType couponType = CouponType.getCouponTypeByCode(this.checkCoupon.getType());
        switch (couponType) {
            case FULL_SUBTRACT_COUPON:
            case WHOLE_SUBTRACT_COUPON:
                serverFinalTotalPrice = serverTotalPrice.subtract(this.checkCoupon.getMinus());
                if (serverFinalTotalPrice.compareTo(BigDecimal.ZERO) < 0) {
                    serverFinalTotalPrice = BigDecimal.ZERO;
                }
                break;
            case FULL_DISCOUNT_COUPON:
            case WHOLE_DISCOUNT_COUPON:
                Boolean wholeStore = this.checkCoupon.getWholeStore();
                if (Boolean.TRUE.equals(wholeStore)) {
                    serverFinalTotalPrice = IMoneyDiscount.discount(serverTotalPrice, this.checkCoupon.getRate(),
                            (o, r) -> o.multiply(r).setScale(2, BigDecimal.ROUND_HALF_EVEN));
                }else {
                    BigDecimal sumByCategoryList = this.getUseCouponSumByCategoryList(this.skuOrderBOList);
                    BigDecimal noCouponDiscount = serverTotalPrice.subtract(sumByCategoryList);
                    serverFinalTotalPrice = noCouponDiscount.add(IMoneyDiscount.discount(sumByCategoryList,
                            this.checkCoupon.getRate(), (o, r) -> o.multiply(r).setScale(2, BigDecimal.ROUND_HALF_EVEN)
                    ));
                }
                break;
            default:
                throw new NotFoundException(UnifyResponseCode.NOT_SUPPORT_COUPON_TYPE.getCode());
        }
        if (serverFinalTotalPrice.compareTo(orderFinalTotalPrice) != 0) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_DISCOUNT_NOT_EQUAL_REAL_DISCOUNT.getCode());
        }
    }

    /**
     * 验证该优惠券能否使用.
     *
     * @param skuOrderBOs the sku pure dt os
     * @return the boolean
     */
    @Override
    public Boolean canBeUsed(List<SkuOrderBO> skuOrderBOs, BigDecimal serverTotalPrice) {
        this.skuOrderBOList = skuOrderBOs;
        //如果是全场优惠券，需要根据商品的价格，判断是否达到门槛使用
        BigDecimal orderCouponPrice = null;
        Boolean wholeStore = this.checkCoupon.getWholeStore();
        if (Boolean.TRUE.equals(wholeStore)) {
            orderCouponPrice = serverTotalPrice;
        } else {
            //如果非全场券，需要根据分类，获取该分类下商品价格，是否可以使用
            orderCouponPrice = getUseCouponSumByCategoryList(skuOrderBOs);
        }
        this.couponCanUsed(orderCouponPrice);
        return Boolean.TRUE;
    }

    /**
     * Gets use coupon sum by category list.
     *
     * @param skuOrderBOs the sku order b os
     * @return the use coupon sum by category list
     */
    private BigDecimal getUseCouponSumByCategoryList(List<SkuOrderBO> skuOrderBOs) {
        BigDecimal orderCouponPrice;
        List<CategoryCoupon> categoryCoupons = this.checkCoupon.getCategoryCoupons();
        List<Long> categoryIds = categoryCoupons.stream().map(CategoryCoupon::getCategoryId).collect(Collectors.toList());
        orderCouponPrice = categoryIds.stream()
                .map(cid -> this.getSumByCategory(skuOrderBOs, cid))
                .reduce(new BigDecimal("0"), BigDecimal::add);
        return orderCouponPrice;
    }

    /**
     * 判断优惠券是否可以使用.
     *
     * @param orderCouponPrice the order coupon price
     */
    private void couponCanUsed(BigDecimal orderCouponPrice) {
        switch (CouponType.getCouponTypeByCode(this.checkCoupon.getType())) {
            case FULL_SUBTRACT_COUPON:
            case FULL_DISCOUNT_COUPON:
                if (orderCouponPrice.compareTo(this.checkCoupon.getFullMoney()) < 0) {
                    throw new ForbiddenException(UnifyResponseCode.COUPON_NOT_REACH_SILL.getCode());
                }
                break;
            case WHOLE_DISCOUNT_COUPON:
            case WHOLE_SUBTRACT_COUPON:
                break;
            default:
                throw new NotFoundException(UnifyResponseCode.NOT_SUPPORT_COUPON_TYPE.getCode());
        }

    }

    /**
     * 根据分类计算价格.
     *
     * @param skuOrderBOs the sku order b os
     * @param cid         the cid
     * @return the sum by category
     */
    private BigDecimal getSumByCategory(List<SkuOrderBO> skuOrderBOs, Long cid) {
        return skuOrderBOs.stream()
                .filter(skuOrderBO -> skuOrderBO.getCategoryId().equals(cid))
                .map(SkuOrderBO::getTotalPrice)
                .reduce(new BigDecimal("0"), BigDecimal::add);
    }
}
