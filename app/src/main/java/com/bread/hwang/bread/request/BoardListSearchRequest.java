package com.bread.hwang.bread.request;

import android.content.Context;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Hwang on 2016-10-31.
 */

public class BoardListSearchRequest extends AbstractRequest<NetworkResult<List<Board>>> {
    Request mRequest;

    public BoardListSearchRequest(Context context, int pageNum) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("boards")
                .addPathSegment(pageNum + "")
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }


    public BoardListSearchRequest(Context context, String lastBoardNum) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("boards")
                .addPathSegment(lastBoardNum)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }


    public BoardListSearchRequest(Context context, int pageNum, String searchType, String keyWord) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("boards")
                .addPathSegment(pageNum + "")
                .addQueryParameter("searchType", searchType)
                .addQueryParameter("keyWord", keyWord)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }


    public BoardListSearchRequest(Context context, String lastBoardNum, String searchType, String keyWord) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("boards")
                .addPathSegment(lastBoardNum)
                .addQueryParameter("searchType", searchType)
                .addQueryParameter("keyWord", keyWord)
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
