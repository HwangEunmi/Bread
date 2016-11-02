package com.bread.hwang.bread.data;

import java.io.Serializable;

/**
 * Created by Hwang on 2016-11-01.
 */

public class BoardData implements Serializable{
    private Board data;
    private String message;
    private int code;

    public Board getData() {
        return data;
    }

    public void setData(Board data) {
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
