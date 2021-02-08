package com.nowavesnokings.firstwin.pojo.bo;

import com.nowavesnokings.firstwin.pojo.dto.SkuPureDTO;
import com.nowavesnokings.firstwin.pojo.model.Sku;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author ssx
 * @version V1.0
 * @className SkuOrderBO
 * @description 订单商品传输对象
 * @date 2021-01-21 17:14
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class SkuOrderBO {
    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 实际价格
     */
    private BigDecimal actualPrice;

    public SkuOrderBO(Sku sku, SkuPureDTO skuPureDTO) {
        this.actualPrice = sku.getActualPrice();
        this.categoryId = sku.getCategoryId();
        this.count = skuPureDTO.getCount();
    }

    public BigDecimal getTotalPrice() {
        return this.actualPrice.multiply(new BigDecimal(this.count));
    }
}
