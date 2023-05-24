package com.sp.store.vo;

import java.util.Objects;

public class OrderItemVO {
    // 商品詳細id(每個在訂單中的商品)
    private Integer id;
    // 商品名稱
    private String title;
    // 商品圖片
    private String image;
    // 單價
    private Long price;
    // 商品數量
    private Integer num;

    public OrderItemVO() {}

    public OrderItemVO(Integer id, Integer oid, String title, String image, Long price, Integer num) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemVO that = (OrderItemVO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(image, that.image) && Objects.equals(price, that.price) && Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, image, price, num);
    }

    @Override
    public String toString() {
        return "OrderItemVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
