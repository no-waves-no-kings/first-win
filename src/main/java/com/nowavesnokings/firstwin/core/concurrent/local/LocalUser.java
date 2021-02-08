package com.nowavesnokings.firstwin.core.concurrent.local;

import com.nowavesnokings.firstwin.pojo.model.User;

/**
 * @author ssx
 * @version V1.0
 * @className LocalUser
 * @description 线程共享用户
 * @date 2020-12-19 11:22
 * @since 1.8
 */
public class LocalUser {
    /**
     * The User thread local.
     */
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    private LocalUser() {

    }

    /**
     * 获取用户.
     *
     * @return the user
     */
    public static User getUser() {
        return LocalUser.userThreadLocal.get();
    }

    /**
     * 设置用户到threadLocal.
     *
     * @param user the user
     */
    public static void setUser(User user) {
        LocalUser.userThreadLocal.set(user);
    }

    /**
     * 删除用户.
     */
    public static void removeUser() {
        LocalUser.userThreadLocal.remove();
    }
}
