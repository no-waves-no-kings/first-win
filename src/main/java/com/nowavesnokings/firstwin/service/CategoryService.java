package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.pojo.model.Category;

/**
 * @author ssx
 * @version V1.0
 * @className CategoryService
 * @description 分类Service
 * @date 2020-12-25 11:28
 * @since 1.8
 */
public interface CategoryService {
    /**
     * 根据id获取分类具体信息.
     *
     * @param cid the cid
     * @return the category by id
     */
    Category getCategoryById(Long cid);
}
