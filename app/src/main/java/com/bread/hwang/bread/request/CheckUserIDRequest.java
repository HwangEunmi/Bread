package com.bread.hwang.bread.request;

import android.content.Context;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.Data;
import com.bread.hwang.bread.data.NetworkResult;
import com.bread.hwang.bread.data.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Hwang on 2016-10-31.
 */

public class CheckUserIDRequest extends AbstractRequest<NetworkResult<Data>> {
    Request mRequest;

    public CheckUserIDRequest(Context context, String userId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("user")
                .addPathSegment("checkid")
                .addPathSegment(userId)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Board>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
