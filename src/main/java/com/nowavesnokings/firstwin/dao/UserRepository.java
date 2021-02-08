package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ssx
 * @version V1.0
 * @className UserRepository
 * @description 用户repository
 * @date 2020-12-15 16:12
 * @since 1.8
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * 更具openid获取用户.
     *
     * @param openid the openid
     * @return User by openid
     */
    User getByOpenid(String openid);

    /**
     * 根据id获取用户.
     *
     * @param uid the uid
     * @return the by id
     */
    Optional<User> getById(Long uid);
}
