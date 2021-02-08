package com.nowavesnokings.firstwin.service.impl;

import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.dao.UserRepository;
import com.nowavesnokings.firstwin.pojo.model.User;
import com.nowavesnokings.firstwin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ssx
 * @version V1.0
 * @className UserServiceImpl
 * @description 用户Service实现类
 * @date 2020-12-19 18:55
 * @since 1.8
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 更具openid获取用户.
     *
     * @param openid the openid
     * @return User by openid
     */
    @Override
    public User getByOpenid(String openid) {
        return userRepository.getByOpenid(openid);
    }

    /**
     * 根据id查询用户.
     *
     * @return the user by id
     */
    @Override
    public User getUserById(Long uid) {
        return userRepository.getById(uid).orElseThrow(() -> new NotFoundException(UnifyResponseCode.USER_NOT_FOUND.getCode()));
    }
}
