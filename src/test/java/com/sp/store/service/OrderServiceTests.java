package com.sp.store.service;

import com.sp.store.entity.Order;
import com.sp.store.vo.OrderItemVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @SpringBootTest：表示標註目前的類是測試類，不會隨同專案一起打包
@SpringBootTest

// @RunWith：啟動單元測試類，需要傳遞一個參數，必須是SpringRunner的實體類型
// 新：@Test，使用junit5
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    // idea有檢測功能，接口是不能直接建立Bean物件的(動態代理技術來解決)
    @Autowired
    private OrderService orderService;

//    @Test
//    public void create() {
//        // public Order create(Integer uid, String username, Integer[] cids)
//        Integer[] cids = {4, 5};
//        Order order = orderService.create(13, "bbb", cids);
//        System.out.println(order);
//    }

    @Test
    public void detail() {
        Integer oid = 78;
        List<OrderItemVO> detail = orderService.detail(oid);
        System.out.println(detail);
    }

}
