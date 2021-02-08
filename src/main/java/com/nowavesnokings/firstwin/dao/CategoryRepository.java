package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ssx
 * @version V1.0
 * @className CategoryRepository
 * @description 分类Repository
 * @date 2020-12-25 11:26
 * @since 1.8
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 根据id查询已上线分类信息.
     *
     * @param cid the cid
     * @return the by id and online true
     */
    Optional<Category> getByIdAndOnlineTrue(Long cid);

    /**
     * 根据id获取分类集合.
     *
     * @param ids the ids
     * @return the categories by id in
     */
    List<Category> getCategoriesByIdIn(List<Long> ids);
}
