package com.sp.store.mapper;

import com.sp.store.entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @SpringBootTest：表示標註目前的類是測試類，不會隨同專案一起打包
@SpringBootTest

// @RunWith：啟動單元測試類，需要傳遞一個參數，必須是SpringRunner的實體類型
// 新：@Test，使用junit5
@RunWith(SpringRunner.class)
public class CartMapperTests {
    // idea有檢測功能，接口是不能直接建立Bean物件的(動態代理技術來解決)
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(22);
        cart.setPid(10000011);
        cart.setNum(2);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
//        cartMapper.updateNumByCid(1, 8,"aaa", new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart result = cartMapper.findByUidAndPid(22, 10000011);
        System.out.println(result);
    }

    @Test
    public void findByUid() {
        System.out.println(cartMapper.findByUid(22));
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOByCid() {
        Integer[] cids = {41,42,43};
        System.out.println(cartMapper.findVOByCid(cids));
    }

//    @Test
//    public void deleteItem() {
//        Integer cid = 22;
//        cartMapper.delete(cid);
//    }

}
