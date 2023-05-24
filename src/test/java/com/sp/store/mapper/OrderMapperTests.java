package com.sp.store.mapper;

import com.sp.store.entity.Cart;
import com.sp.store.entity.Order;
import com.sp.store.entity.OrderItem;
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
public class OrderMapperTests {
    // idea有檢測功能，接口是不能直接建立Bean物件的(動態代理技術來解決)
    @Autowired
    private OrderMapper orderMapper;

//    @Test
//    public void insertOrder() {
//        Order order = new Order();
//        order.setUid(14);
//        order.setTotalPrice(10L);
//        orderMapper.insertOrder(order);
//    }

//    @Test
//    public void insertOrderItem() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOid(1);
//        orderItem.setPid(10000001);
//        orderItem.setTitle("aaa");
//        orderMapper.insertOrderItem(orderItem);
//
//    }
//
//    @Test
//    public void cleanCart() {
//        orderMapper.cleanCart(35);
//    }

    @Test
    public void findOrderDetailByOid() {
        Integer oid = 78;
        System.out.println(orderMapper.findOrderDetailByOid(oid));
    }
    @Test
    public void findOrderByOid() {
        Integer uid = 34;
        System.out.println(orderMapper.findOrderByOid(uid));
    }



}
