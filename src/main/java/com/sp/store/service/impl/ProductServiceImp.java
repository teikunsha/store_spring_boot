package com.sp.store.service.impl;

import com.sp.store.entity.Product;
import com.sp.store.mapper.ProductMapper;
import com.sp.store.service.ProductService;
import com.sp.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            // 查詢到後把不要顯示的資料設定為null
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        // 根據參數id調用私有方法執行查詢，取得商品詳情
        Product product = productMapper.findById(id);
        // 判斷查詢結果是否為null
        if (product == null) {
        // 是：拋出ProductNotFoundException
            throw new ProductNotFoundException("商品資料不存在");
        }
        // 將查詢結果中的部分屬性設置為null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        // 返回查詢結果
        return product;
    }
}
