package com.sp.store.vo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderVO{
    // 訂單編號
    private Integer oid;
    // 使用者id
    private Integer uid;
    // 總金額
    private String totalPrice;
    // 建立時間
    private Date orderTime;
    private List<OrderItemVO> orderItemVOList;

    public OrderVO() {}

    public OrderVO(Integer oid, Integer uid, String totalPrice, Date orderTime, List<OrderItemVO> orderItemVOList) {
        this.oid = oid;
        this.uid = uid;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.orderItemVOList = orderItemVOList;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderItemVO> getOrderItemVOList() {
        return orderItemVOList;
    }

    public void setOrderItemVOList(List<OrderItemVO> orderItemVOList) {
        this.orderItemVOList = orderItemVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderVO orderVO = (OrderVO) o;
        return Objects.equals(oid, orderVO.oid) && Objects.equals(uid, orderVO.uid) && Objects.equals(totalPrice, orderVO.totalPrice) && Objects.equals(orderTime, orderVO.orderTime) && Objects.equals(orderItemVOList, orderVO.orderItemVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, uid, totalPrice, orderTime, orderItemVOList);
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", totalPrice='" + totalPrice + '\'' +
                ", orderTime=" + orderTime +
                ", orderItemVOList=" + orderItemVOList +
                '}';
    }
}
