package com.sp.store.service.impl;

import com.sp.store.entity.Cart;
import com.sp.store.entity.Product;
import com.sp.store.mapper.CartMapper;
import com.sp.store.mapper.ProductMapper;
import com.sp.store.service.CartService;
import com.sp.store.service.ex.AccessDeniedException;
import com.sp.store.service.ex.CartNotFoundException;
import com.sp.store.service.ex.InsertException;
import com.sp.store.service.ex.UpdateException;
import com.sp.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    /**
     * 購物車的業務層依賴於購物車的持久層
     * 以及商品的持久層(根據id查詢商品)
     */
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        // 查詢目前要增加的這個購物車是否在表中已存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(result == null) { // 表示這個商品沒有被增加到購物車中，則進行新增操作
            // 建立一個空的cart物件
            Cart cart = new Cart();
            // 補全資料
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 補全價格：來自商品中的資料
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            // 補全日誌：
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            // 執行資料插入操作
            Integer rows = cartMapper.insert(cart);
            if(rows != 1) {
                throw new InsertException("插入資料時產生未知的異常");
            }

        }else{ // 目前的商品在購物車中已經存在，更新num值即可
            // 原有資料和現有傳遞的資料進行相加
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, date);

            if(rows != 1) {
                throw new UpdateException("更新資料時產生了未知的異常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("資料不存在");
        }
        if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException("資料非法訪問");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows != 1) {
            throw new UpdateException("資料更新失敗");
        }
        // 返回新的商品數量
        return num;
    }

    @Override
    public Integer minusNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("資料不存在");
        }
        if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException("資料非法訪問");
        }

        Integer num = result.getNum();
        if(result.getNum() <= 1) {
            num = 1;
        }else{
            num = result.getNum() -1;
        }


        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows != 1) {
            throw new UpdateException("資料更新失敗");
        }
        // 返回新的商品數量
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        Iterator<CartVO> it = list.iterator();
        while(it.hasNext()) {
            CartVO cartVO = it.next();
            if(!cartVO.getUid().equals(uid)) { // 表示目前的資料不屬於目前的帳號
                // 從集合中移除元素
                list.remove(cartVO);
            }
        }
        return list;
    }

    @Override
    public void deleteCartItem(Integer cid) {
        cartMapper.delete(cid);
    }
}
