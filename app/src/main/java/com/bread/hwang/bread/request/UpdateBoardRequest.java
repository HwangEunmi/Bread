package com.bread.hwang.bread.request;

import android.content.Context;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.Data;
import com.bread.hwang.bread.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Hwang on 2016-10-31.
 */

public class UpdateBoardRequest extends AbstractRequest<NetworkResult<Data>> {
    MediaType image = MediaType.parse("image/*");
    Request mRequest;

    public UpdateBoardRequest(Context context, int boardNum, String boardContent) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("board")
                .addPathSegment(boardNum + "")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("boardContent", boardContent)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    public UpdateBoardRequest(Context context, int boardNum, String boardContent, List<File> files, List<Integer> delFiles) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("board")
                .addPathSegment(boardNum + "")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("boardContent", boardContent)
                .addFormDataPart("delFiles", delFiles + "");

        if (files != null) {
            for (int i = 0; i < files.size(); i++) {
                builder.addFormDataPart("files", files.get(i).getName(),
                        RequestBody.create(image, files.get(i)));
            }
        }
        RequestBody body = builder.build();
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
