package com.bread.hwang.bread.data;

/**
 * Created by Hwang on 2016-10-31.
 */

public class NetworkResult<T> {
    private T result;
    private int code;
    private String error;

    public T getResult() {
        return this.result;
    }

    public int getCode() {
        return this.code;
    }

    public String getError() {
        return error;
    }
}
