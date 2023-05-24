package com.sp.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// MapperScan：指定目前專案中的Mapper接口路徑位置，在專案啟動時，會自動載入所有接口
@MapperScan("com.sp.store.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
