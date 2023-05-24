package com.sp.store.mapper;

import com.sp.store.entity.Cart;
import com.sp.store.vo.CartVO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CartMapper {
    /**
     * 插入購物車資料
     * @param cart 購物車資料
     * @return 受影響的行數
     */
    Integer insert(Cart cart);

    /**
     * 更新購物車某件商品的數量
     * @param cid 購物車資料id
     * @param num 更新數量
     * @param modifiedUser 修改者
     * @param modifiedTime 修改時間
     * @return 受影響的行數
     */
    Integer updateNumByCid(Integer cid,
                           Integer num,
                           String modifiedUser,
                           Date modifiedTime);

    /**
     * 根據使用者id和商品id來查詢購物車中的資料
     * @param uid 使用者id
     * @param pid 商品id
     * @return
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    List<CartVO> findByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCid(Integer[] cids);

    /**
     * 刪除購物車資料
     * @param cid 購物車id
     */
    Integer delete(Integer cid);
}
