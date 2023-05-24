package com.sp.store.mapper;

import com.sp.store.entity.Order;
import com.sp.store.entity.OrderItem;
import com.sp.store.vo.OrderItemVO;
import com.sp.store.vo.OrderVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// 訂單持久層接口
public interface OrderMapper {
    /**
     * 插入訂單資料
     * @param order
     * @return 受影響的行數
     */
    Integer insertOrder(Order order);

    /**
     * 插入訂單詳情的資料
     * @param orderItem 訂單詳情
     * @return 受影響的行數
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 訂單建立後清空購物車
     */
    void cleanCart(Integer uid);

    List<OrderVO> findOrderByOid(Integer uid);

    List<OrderItemVO> findOrderDetailByOid(Integer oid);
}
