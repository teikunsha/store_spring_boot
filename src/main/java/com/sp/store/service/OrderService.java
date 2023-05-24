package com.sp.store.service;

import com.sp.store.entity.Order;
import com.sp.store.vo.OrderItemVO;
import com.sp.store.vo.OrderVO;

import java.util.List;

public interface OrderService {

    Order create(Integer uid, String username, Integer[] cids);

    void cleanCart(Integer uid);


    List<OrderVO> order(Integer uid);

    List<OrderItemVO> detail(Integer oid);
}
