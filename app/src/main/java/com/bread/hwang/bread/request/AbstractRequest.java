package com.bread.hwang.bread.request;

import com.bread.hwang.bread.manager.NetworkRequest;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Hwang on 2016-10-31.
 */

public abstract class AbstractRequest<T> extends NetworkRequest<T> {

    protected HttpUrl.Builder getBaseUrlBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("http");
        // builder.host();
        return builder;
    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    protected T parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        T result = gson.fromJson(text, getType());
        return result;
    }

    protected Type getType(int code) {
        return getType();
    }

    protected abstract Type getType();
}
