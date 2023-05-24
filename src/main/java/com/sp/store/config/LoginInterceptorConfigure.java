package com.sp.store.config;

import com.sp.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/**
 * 處理器攔截器的註冊
 */
@Configuration
public class LoginInterceptorConfigure implements WebMvcConfigurer {

    // 將自定義的攔截器進行註冊
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 建立自定義攔截器物件
        HandlerInterceptor interceptor = new LoginInterceptor();

        // 配置白名單：存在在一個List集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/products/**");
        patterns.add("/");
        patterns.add("/index.html");
        patterns.add("");

        registry.addInterceptor(interceptor)
                // 攔截全部，除了...
                .addPathPatterns("/**") // 要攔截的內容是什麼，/**表示專案下全部內容
                .excludePathPatterns(patterns); // 除外
    }
}
