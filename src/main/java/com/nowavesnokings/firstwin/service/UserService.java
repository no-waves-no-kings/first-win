package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.pojo.model.User;

/**
 * @author ssx
 * @version V1.0
 * @className UserService
 * @description 用户service
 * @date 2020-12-19 18:50
 * @since 1.8
 */
public interface UserService {
    /**
     * 更具openid获取用户.
     *
     * @param openid the openid
     * @return User by openid
     */
    User getByOpenid(String openid);

    /**
     * 根据id查询用户.
     *
     * @param uid the uid
     * @return the user by id
     */
    User getUserById(Long uid);
}
