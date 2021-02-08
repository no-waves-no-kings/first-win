package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.Sku;
import org.springframework.stereotype.Repository;

/**
 * @author ssx
 * @version V1.0
 * @className SkuRepository
 * @description 商品repository
 * @date 2021-01-21 11:22
 * @since 1.8
 */
@Repository
public interface SkuRepository extends BaseRepository<Sku, Long> {
}
