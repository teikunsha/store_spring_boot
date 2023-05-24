package com.sp.store.service;

import com.sp.store.entity.Product;
import com.sp.store.vo.CartVO;

import java.util.List;


public interface CartService {

    /**
     * 將商品增加到購物車中
     * @param uid 使用者id
     * @param pid 商品id
     * @param amount 新增數量
     * @param username 使用者名稱(修改者)
     * @return 匹配的商品詳情，如果沒有匹配的資料則返回null
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    List<CartVO> getVOByUid(Integer uid);

    /**
     * 操作購物車商品的數量
     * @param cid
     * @param uid
     * @param username 使用者名稱
     * @return 增加成功後的新的數量
     */
    Integer addNum(Integer cid, Integer uid, String username);
    Integer minusNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCid(Integer uid, Integer[] cids);

    void deleteCartItem(Integer cid);
}
