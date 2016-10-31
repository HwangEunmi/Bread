package com.bread.hwang.bread.request;

import android.content.Context;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.NetworkResult;
import com.bread.hwang.bread.data.Reply;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Hwang on 2016-10-31.
 */

public class UpdateReplyRequest extends AbstractRequest<NetworkResult<Reply>> {
    Request mRequest;

    public UpdateReplyRequest(Context context, int boardNum, int replyNum, String replyContent) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("board")
                .addPathSegment(boardNum + "")
                .addPathSegment("reply")
                .addPathSegment(replyNum + "")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("replyContent", replyContent)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .post(body)
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
