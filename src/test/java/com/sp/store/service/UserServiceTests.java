package com.sp.store.service;

import com.sp.store.entity.User;
import com.sp.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @SpringBootTest：表示標註目前的類是測試類，不會隨同專案一起打包
@SpringBootTest

// @RunWith：啟動單元測試類，需要傳遞一個參數，必須是SpringRunner的實體類型
// 新：@Test，使用junit5
@RunWith(SpringRunner.class)
public class UserServiceTests {
    // idea有檢測功能，接口是不能直接建立Bean物件的(動態代理技術來解決)
    @Autowired
    private UserService userService;
    /**
     * 滿足以下條件，就可以獨立執行，不需要啟動整個專案，提升的測試效率
     * 1. 必須被Test註解修飾
     * 2. 返回值類型必須是vodi
     * 3. 參數列表不指定任何類型
     * 4. 訪問修飾符必須是public
     */
    @Test
    public void reg() {
        try {
            User user= new User();
            user.setUsername("amg-2");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            // 取得類的物件，在取得類的名稱
            System.out.println(e.getClass().getSimpleName());
            // 取得異常的具體描述訊息
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void login() {
        User user = userService.login("bbb", "111");
        System.out.println(user);
    }
}
