package com.sp.store.service;

import com.sp.store.entity.User;

// 使用者模組業務層接口
public interface UserService {
    /**
     * 使用者註冊方法
     * @param user 使用者的資料物件
     */
    void reg(User user);

    /**
     * 使用這登入功能
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
