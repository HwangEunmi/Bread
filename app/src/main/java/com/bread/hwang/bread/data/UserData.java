package com.bread.hwang.bread.data;

/**
 * Created by Hwang on 2016-11-01.
 */

public class UserData {
    private User data;
    private String message;
    private int code;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
