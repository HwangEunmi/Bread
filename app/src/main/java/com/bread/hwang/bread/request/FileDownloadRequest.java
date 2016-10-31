package com.bread.hwang.bread.request;

import android.content.Context;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.File;
import com.bread.hwang.bread.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Hwang on 2016-10-31.
 */

public class FileDownloadRequest extends AbstractRequest<NetworkResult<File>> {
    Request mRequest;

    public FileDownloadRequest(Context context, int boardNum, int fileNum) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("board")
                .addPathSegment(boardNum + "")
                .addPathSegment("file")
                .addPathSegment(fileNum + "")
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
