package com.sp.store.util;

import org.springframework.core.serializer.Serializer;

import java.io.Serializable;

/**
 * Json格式的資料進行響應
 */
public class JsonResult<E> implements Serializable {
    // 狀態碼
    private Integer state;
    // 狀態訊息
    private String message;
    // 資料，不確定類型，設置為汎型
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
