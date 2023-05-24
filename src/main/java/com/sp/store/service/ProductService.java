package com.sp.store.service;

import com.sp.store.entity.Product;

import java.util.List;


public interface ProductService {
    List<Product> findHotList();

    /**
     * 根據商品id查詢商品詳情
     * @param id 商品id
     * @return 匹配的商品詳情，如果沒有匹配的資料則返回null
     */
    Product findById(Integer id);
}
