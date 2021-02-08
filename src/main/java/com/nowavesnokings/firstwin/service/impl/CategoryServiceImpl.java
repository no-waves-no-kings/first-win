package com.nowavesnokings.firstwin.service.impl;

import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.dao.CategoryRepository;
import com.nowavesnokings.firstwin.pojo.model.Category;
import com.nowavesnokings.firstwin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ssx
 * @version V1.0
 * @className CategoryServiceImpl
 * @description 分类Service实现类
 * @date 2020-12-25 11:28
 * @since 1.8
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 根据id获取分类具体信息.
     *
     * @param cid the cid
     * @return the category by id
     */
    @Override
    public Category getCategoryById(Long cid) {
        return categoryRepository.getByIdAndOnlineTrue(cid)
                .orElseThrow(() -> new NotFoundException(UnifyResponseCode.CATEGORY_NOT_FOUND.getCode()));
    }
}
