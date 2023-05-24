package com.sp.store.service.impl;

import com.sp.store.entity.Order;
import com.sp.store.entity.OrderItem;
import com.sp.store.mapper.OrderMapper;
import com.sp.store.service.CartService;
import com.sp.store.service.OrderService;
import com.sp.store.service.ex.InsertException;
import com.sp.store.vo.CartVO;

import com.sp.store.vo.OrderItemVO;
import com.sp.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartService cartService;

    @Override
    public Order create(Integer uid, String username, Integer[] cids) {
        // 取得購物車的資料(即將要下單的列表)
        List<CartVO> list = cartService.getVOByCid(uid, cids);

        // 計算商品總價
        Long totalPirce = 0L;
        for(CartVO c : list) {
            totalPirce += c.getPrice() * c.getNum();
        }

        // 建立Order物件，插入資料
        Order order = new Order();
        order.setUid(uid);

        // 建立訂單詳情的資料
        for(CartVO c : list) {
            totalPirce += c.getPrice() * c.getNum();

            // 建立訂單詳情物件
            OrderItem orderItem = new OrderItem();
        // 支付
        order.setStatus(0);
        order.setTotalPrice(totalPirce);

        // 訂單建立時間
        order.setOrderTime(new Date());

        // 日誌
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());

        Integer rows = orderMapper.insertOrder(order);
        if(rows != 1) {
            throw new InsertException("新增訂單異常");
        }

            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getPrice());
            orderItem.setNum(c.getNum());

            // 日誌
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());

            // 插入資料操作
            rows = orderMapper.insertOrderItem(orderItem);

            if(rows != 1) {
                throw new InsertException("新增訂單詳細異常");
            }

            orderMapper.cleanCart(uid);
        }
        return order;
    }

    @Override
    public void cleanCart(Integer uid) {}

    @Override
    public List<OrderVO> order(Integer uid) {
        List<OrderVO> order = orderMapper.findOrderByOid(uid);
        return order;
    }

    @Override
    public List<OrderItemVO> detail(Integer oid) {
        List<OrderItemVO> detail = orderMapper.findOrderDetailByOid(oid);
        return detail;
    }
}
