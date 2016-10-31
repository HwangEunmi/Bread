package com.bread.hwang.bread.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Hwang on 2016-10-28.
 */

public abstract class NetworkRequest<T> implements Callback {
    /* NetworkManager에서 OkHTTP안쓰고 ThreadPool이랑 Executor쓰면 Runnable을 implements해야 함 */

    T result;
    int errorCode;
    String errorMessage;
    Throwable exception;
    NetworkManager.OnResultListener<T> listener;

    public void setOnResultListener(NetworkManager.OnResultListener<T> listener) {
        this.listener = listener;
    }

    public abstract Request getRequest();

    protected abstract T parse(ResponseBody body) throws IOException;

    Call call;

    void process(OkHttpClient client) {
        Request request = getRequest();
        call = client.newCall(request);
        call.enqueue(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        sendError(-1, e.getMessage(), e);
    }

    protected void sendError(int errorCode, String errorMessage, Throwable exception) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exception = exception;
        NetworkManager.getInstance().sendFail(this);
    }

    void sendFail() {
        if (listener != null) {
            listener.onFail(this, errorCode, errorMessage, exception);
        }
    }

    private void sendSuccess(T result) {
        this.result = result;
        NetworkManager.getInstance().sendSuccess(this);
    }

    void sendSuccess() {
        if (listener != null) {
            listener.onSuccess(this, result);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            try {
                sendSuccess(parse(response.body()));
            } catch (IOException e) {
                sendError(-1, e.getMessage(), e);
            }
        } else {
            sendError(response.code(), response.message(), null);
        }
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }

    public boolean isCancel() {
        if (call == null) return false;
        return call.isCanceled();
    }


}
