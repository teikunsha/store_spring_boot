package com.sp.store.controller;

import com.sp.store.entity.Order;
import com.sp.store.service.OrderService;
import com.sp.store.util.JsonResult;
import com.sp.store.vo.OrderItemVO;
import com.sp.store.vo.OrderVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameSession(session);
        Order data = orderService.create(uid, username, cids);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("order")
    public JsonResult<List<OrderVO>> order(HttpSession session) {
        List<OrderVO> data = orderService.order(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("order/detail/{oid}")
    public JsonResult<List<OrderItemVO>> detail(@PathVariable("oid") Integer oid) {
        List<OrderItemVO> data = orderService.detail(oid);
        return new JsonResult<>(OK, data);
    }
}