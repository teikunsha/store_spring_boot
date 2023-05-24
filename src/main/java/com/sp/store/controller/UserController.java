package com.sp.store.controller;

import com.sp.store.entity.User;
import com.sp.store.service.UserService;
import com.sp.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController // @Controller + @RequestBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

//    @RequestMapping("reg")
//    // @RequestBody // 表示此方法的響應結果以json格式進行資料的響應給前端
//    public JsonResult<Void> reg(User user) {
//        // 響應結果的物件
//        JsonResult<Void> result = new JsonResult<>();
//        try {
//            userService.reg(user);
//            result.setState(200);
//            result.setMessage("使用者註冊成功");
//        } catch (UsernameDuplicatedException e) { // 可能出現username已經存在的異常
//            result.setState(4000);
//            result.setMessage("使用者名稱已經存在");
//        } catch (InsertException e) {
//            result.setState(5000);
//            result.setMessage("註冊時產生未知的異常");
//        }
//        return result;
//    }

    /**
     * 1. 接收資料方式：請求處理方法的參數列表設置為pojo類型來接收前端的資料
     * SpringBoot會將前端的url地址中的參數名和pojo類的屬性名進行比較
     * 如果兩個名稱相同，則將值注入pojo類中對應的屬性上
     */
    @RequestMapping("reg")
    // @RequestBody // 表示此方法的響應結果以json格式進行資料的響應給前端
    public JsonResult<Void> reg(User user) {
        // 假如有異常產生，會直接跳轉至BaseController，並把結果傳遞回來
            userService.reg(user);
            return new JsonResult<>(OK); // 沒有異常響應OK(public static final int OK = 200;)
    }

    /**
     * 接收資料方式：請求處理方法的參數列表設置為非pojo類型來接收前端的資料
     * SpringBoot直接將請求的參數名(網址的id="username")和方法的參數名進行比較
     * 如果名稱相同則自動完成值的依賴注入
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);

        // 向session物件中完成資料的綁定(session全域)
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        // 取得session中綁定的資料
//        System.out.println(getUidFromSession(session));
//        System.out.println(getUsernameSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("getUserSession")
    public String getUserSession(HttpSession httpSession) {
        if(httpSession.getAttribute("username") != null) {
            return "OK";
        }
        return "NO";
    }

    @RequestMapping("logout")
    public void logout (HttpSession httpSession) {
        httpSession.invalidate();
    }
}
