package com.sp.store.service.impl;

import com.sp.store.entity.User;
import com.sp.store.mapper.UserMapper;
import com.sp.store.service.UserService;
import com.sp.store.service.ex.InsertException;
import com.sp.store.service.ex.PasswordNotMatchException;
import com.sp.store.service.ex.UserNotFoundException;
import com.sp.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 過通user參數來取得前端傳遞過來的username
        String username = user.getUsername();
        System.out.println(username);

        // 調用findByUsername(username)方法，判斷使用者名稱是否被註冊過
        User result = userMapper.findByUsername(username);

        // 判斷結果是否為null，不是null則拋出使用者名稱被占用的異常
        if (result != null) {
            throw new UsernameDuplicatedException("使用者名稱已經存在");
        }

        // 密碼加密實現：md5
        // 字串 + password + 字串 -> 交給md5算法進行加密，連續加載三次
        // 鹽值 + password + 鹽值 -> 鹽值是一個隨機的字串
        String oldPassword = user.getPassword();
        // 取得鹽值(隨機產生)
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 將鹽值保存至資料庫
        user.setSalt(salt);
        // 將密碼和鹽值值做為一個整體進行加密處理，
        String md5Password = getMD5Password(oldPassword, salt);
        // 將加密後的密碼放入user物件中
        user.setPassword(md5Password);

        // 補全資料
        // is_delete設定為0
        user.setIsDelete(0);
        // 4個日誌字段
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());

        // 執行註冊功能的實現(執行插入成功：rows == 1)
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在註冊過程中產生了未知的異常");
        }
    }

    @Override
    public User login(String username, String password) {
        // 根據帳號名稱查詢使用者的資料是否存在，不存在則拋出異常
        User result = userMapper.findByUsername(username);
        if(result == null) {
            throw new UserNotFoundException("帳號不存在");
        }
        // 檢測密碼是否匹配
        // 1. 先取得資料庫中加密後密碼
        String oldPassword = result.getPassword();

        // 2. 和前端傳遞過來的密碼進行比較
        // 2.1 取得鹽值：註冊時產生的鹽值
        String salt = result.getSalt();
        // 2.2 將密碼按照相同的md5算法規則進行加密
        String newMd5Password = getMD5Password(password, salt);

        // 3. 將密碼進行比較
        if(!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("密碼錯誤");
        }

        // 判斷is_delete字段的值是否為1
        if(result.getIsDelete()==1) {
            throw new UserNotFoundException("帳號不存在");
        }

        // 調用mapper層的findByUsername來查詢使用者的資料，提升系統性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());

        // 將目前的使用者資料返回，返回的資料是為了輔助其他頁面作資料展示(uid, username)
        return user;
    }

    // 定義md5算法的加密處裡
    private String getMD5Password(String password, String salt) {
        // 三次加密
        for (int i = 0; i < 3; i++) {
            // md5加密算法
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();

        }
        return password;
    }
}
