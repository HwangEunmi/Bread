package com.bread.hwang.bread.request;

import android.content.Context;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.NetworkResult;
import com.bread.hwang.bread.manager.NetworkRequest;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Hwang on 2016-10-31.
 */

public class BoardDetailRequest extends AbstractRequest<NetworkResult<Board>> {
    Request mRequest;

    public BoardDetailRequest(Context context, int boardNum) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("board")
                .addPathSegment(boardNum + "")
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
