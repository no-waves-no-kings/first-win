package com.nowavesnokings.firstwin.pojo.model;

import com.nowavesnokings.firstwin.pojo.dto.SkuPureDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className OrderSku
 * @description 订单商品实体
 * @date 2021-01-29 13:09
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class OrderSku {
    private Long id;
    private Long spuId;
    private String img;
    private String title;
    private BigDecimal finalPrice;
    private BigDecimal singlePrice;
    private Integer count;
    private List<String> specValues;

    public OrderSku(Sku sku, SkuPureDTO skuPureDTO) {
        this.id = sku.getId();
        this.spuId = sku.getSpuId();
        this.img = sku.getImg();
        this.title = sku.getTitle();
        this.count = skuPureDTO.getCount();
        this.finalPrice = sku.getActualPrice().multiply(new BigDecimal(skuPureDTO.getCount()));
        this.singlePrice = sku.getActualPrice();

    }
}
