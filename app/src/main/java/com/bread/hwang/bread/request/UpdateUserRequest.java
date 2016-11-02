package com.bread.hwang.bread.request;

import android.content.Context;
import android.content.IntentFilter;

import com.bread.hwang.bread.R;
import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.Data;
import com.bread.hwang.bread.data.NetworkResult;
import com.bread.hwang.bread.data.User;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Hwang on 2016-10-31.
 */

class UpdateUserRequest extends AbstractRequest<NetworkResult<Data>> {
    MediaType image = MediaType.parse("image/*");
    Request mRequest;

    public UpdateUserRequest(Context context, String userPass, String userNick, File userFile) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("profile")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userPass", userPass)
                .addFormDataPart("userNick", userNick);

        if (userFile != null) {
            builder.addFormDataPart("userFile", userFile.getName(),
                    RequestBody.create(image, userFile));
        }

        RequestBody body = builder.build();
        mRequest = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    public UpdateUserRequest(Context context, String userPass, String userNick) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("profile")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("userPass", userPass)
                .add("userNick", userNick)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    public UpdateUserRequest(Context context, String userPass) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("profile")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("userPass", userPass)
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
