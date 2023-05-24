package com.sp.store.controller;

import com.sp.store.service.ex.*;
import com.sp.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 控制層類的基類
public class BaseController {
    // 操作成功的狀態碼
    public static final int OK = 200;

    // 請求處理方法，這個方法的返回值就是需要傳遞給前端的資料
    // 自動將異常物件傳遞給此方法的參數列表上
    // 當產生了異常，被統一攔截到此方法，這個方法此時就充當是請求處理方法，方法的返回值直接傳給前端
    @ExceptionHandler(ServiceException.class) // 用於統一處理拋出的異常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("使用者名稱已經存在");
        }else if(e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("帳號不存在的異常");
        }else if(e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("密碼錯誤的異常");
        }else if(e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("註冊時產生未知的異常");
        }else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品資料不存在的異常");
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("購物車資料不存在的異常");
        }else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新資料時產生了未知的異常");
        }
        return result;
    }

    /**
     * 取得session物件中的uid
     * @param session session物件
     * @return 使用者uid的值
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 取得session物件的username
     * @param session session物件
     * @return 使用者username的值
     *
     */
    protected final String getUsernameSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
