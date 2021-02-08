package com.nowavesnokings.firstwin.api.v1;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssx
 * @version V1.0
 * @className SkuController
 * @description 商品控制器
 * @date 2021-01-21 11:29
 * @since 1.8
 */
@RestController
@RequestMapping(value = "sku")
@Api(value = "商品相关接口", tags = {"用于处理商品相关接口"})
public class SkuController {
}
