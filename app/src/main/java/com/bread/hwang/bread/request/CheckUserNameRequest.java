package com.bread.hwang.bread.request;

import android.content.Context;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.NetworkResult;
import com.bread.hwang.bread.data.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Hwang on 2016-10-31.
 */

public class CheckUserNameRequest extends AbstractRequest<NetworkResult<User>> {
    Request mRequest;

    public CheckUserNameRequest(Context context, String userNick) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("user")
                .addPathSegment("checkname")
                .addPathSegment(userNick)
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
