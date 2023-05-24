package com.sp.store.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 定義一個攔截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    // 在調用所有處理請求的方法之前

    /**
     * 檢測全域session物件中是否有uid資料，有則放行，沒有則重定向到login.html
     * @param request 請求物件
     * @param response 響應物件
     * @param handler 處理器(url+Controller:映射)
     * @return 如果返回值為true，表示放行目前請求，false表示攔截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // HttpServletRequest物件來取得session物件
        Object obj = request.getSession().getAttribute("uid");
        if(obj == null) {
            // 說明沒有帳號登入，則重定向到login.html
            response.sendRedirect("/web/login.html");

            // 結束後續調用
            return false;
        }
        // 請求放行
        return true;
    }
}
