package com.bread.hwang.bread.data;

import java.util.List;

/**
 * Created by Hwang on 2016-11-01.
 */

public class FileData {
    private List<File> data;
    private String message;
    private int code;

    public List<File> getData() {
        return data;
    }

    public void setData(List<File> data) {
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
