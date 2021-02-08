package com.nowavesnokings.firstwin.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ssx
 * @version V1.0
 * @className CategoryPureVO
 * @description 分类简化视图对象
 * @date 2021-01-16 17:51
 * @since 1.8
 */
@Getter
@Setter
@ToString
public class CategoryPureVO {
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
