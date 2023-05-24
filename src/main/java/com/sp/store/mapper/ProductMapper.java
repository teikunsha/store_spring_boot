package com.sp.store.mapper;

import com.sp.store.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Product> findHotList();

    /**
     * 根據據商品id查詢商品詳情
     * @param id 商品id
     * @return 匹配的商品詳情，如果沒有匹配的資料則返回null
     */
    Product findById(Integer id);
}
