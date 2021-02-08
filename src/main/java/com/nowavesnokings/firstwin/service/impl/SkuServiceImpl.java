package com.nowavesnokings.firstwin.service.impl;

import com.nowavesnokings.firstwin.dao.SkuRepository;
import com.nowavesnokings.firstwin.pojo.model.Sku;
import com.nowavesnokings.firstwin.service.SkuService;
import org.springframework.stereotype.Service;

/**
 * @author ssx
 * @version V1.0
 * @className SkuServiceImpl
 * @description 商品service实现类
 * @date 2021-01-21 11:25
 * @since 1.8
 */
@Service
public class SkuServiceImpl extends AbstractBaseService< Sku, Long, SkuRepository> implements SkuService {
}
