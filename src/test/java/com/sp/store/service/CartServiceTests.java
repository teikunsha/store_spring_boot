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
public class CartServiceTests {
    // idea有檢測功能，接口是不能直接建立Bean物件的(動態代理技術來解決)
    @Autowired
    private CartService cartService;

//    @Test
//    public void addToCart() {
//        cartService.addToCart(22, 10000004, 20, "aa");
//
//    }
    @Test
    public void deleteCartItem() {
        cartService.deleteCartItem(26);
    }

}
