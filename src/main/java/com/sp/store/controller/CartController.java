package com.sp.store.controller;

import com.sp.store.service.CartService;
import com.sp.store.util.JsonResult;
import com.sp.store.vo.CartVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{

    @Autowired
    private CartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid,
                                      Integer amount,
                                      HttpSession session) {
        cartService.addToCart(getUidFromSession(session),
                pid,
                amount,
                getUsernameSession(session));
        System.out.println(getUidFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> data =  cartService.getVOByUid(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.addNum(cid, getUidFromSession(session), getUsernameSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/num/minus")
    public JsonResult<Integer> minusNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.minusNum(cid, getUidFromSession(session), getUsernameSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids, HttpSession session) {
        List<CartVO> data =  cartService.getVOByCid(getUidFromSession(session), cids);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("delete/{cid}")
    public JsonResult<Void> deleteCartItem(@PathVariable("cid") Integer cid) {
        cartService.deleteCartItem(cid);
        return new JsonResult<>(OK);
    }

}
