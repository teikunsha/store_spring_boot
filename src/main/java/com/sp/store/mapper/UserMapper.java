package com.sp.store.mapper;

import com.sp.store.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

// 使用者模組的接口
@Repository
public interface UserMapper {
    /**
     * 插入使用者資料
     * @param user 使用者資料
     * @return 受影響的行數(增刪改，都有受影響的行數作為返回值，根據返回值判斷是否執行成功)
     */
    Integer insert(User user);

    /**
     * 根據使用者名稱查詢使用者資料
     * @param username
     * @return 如果只到對應的使用者則返回該名使用者的資料，沒有則返回null
     */
    User findByUsername(String username);

}
