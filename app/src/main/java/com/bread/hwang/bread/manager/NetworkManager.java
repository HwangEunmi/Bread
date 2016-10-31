package com.bread.hwang.bread.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.bread.hwang.bread.MyApplication;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/**
 * Created by Hwang on 2016-10-28.
 */

public class NetworkManager {
    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    OkHttpClient client;

    private NetworkManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Context context = MyApplication.getContext();
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        builder.cookieJar(cookieJar);
        builder.followRedirects(true);


        File cacheDir = new File(context.getCacheDir(), "network");
        if (!cacheDir.exists()) cacheDir.mkdir();
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);
        builder.cache(cache);

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        client = builder.build();
    }

    public OkHttpClient getClient() {
        return client;
    }

    private static final int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAIL = 2;

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkRequest<?> request = (NetworkRequest<?>) msg.obj;

            switch (msg.what) {
                case MESSAGE_SUCCESS:
                    request.sendSuccess();
                    break;
                case MESSAGE_FAIL:
                    request.sendFail();
                    break;
            }
        }
    };

    public interface OnResultListener<T> {
        public void onSuccess(NetworkRequest<T> request, T result);
        public void onFail(NetworkRequest<T> request, int errorCode, String errorMessage, Throwable exception);
    }

    void sendSuccess(NetworkRequest<?> request) {
        Message msg = mHandler.obtainMessage(MESSAGE_SUCCESS, request);
        mHandler.sendMessage(msg);
    }

    void sendFail(NetworkRequest<?> request) {
        Message msg = mHandler.obtainMessage(MESSAGE_FAIL, request);
        mHandler.sendMessage(msg);
    }

    public <T> void getNetworkData(NetworkRequest<T> request, OnResultListener<T> listener) {
        request.setOnResultListener(listener);
        request.process(client);
    }

    public void cancelAll() {
        client.dispatcher().cancelAll();
    }

    public void cancelAll(Object tag) {
        Dispatcher dispatcher = client.dispatcher();
        List<Call> list = dispatcher.queuedCalls();

        for (Call call : list) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
        list = dispatcher.runningCalls();

        for (Call call : list) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
    }


}





/* ThreadPool 이용 */
    /*
    Executor mExecutor;
    BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

    private NetworkManager() {
        mExecutor = new ThreadPoolExecutor(3, 64, 3, TimeUnit.SECONDS, taskQueue);
    }

    private static final int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAIL = 2;

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            NetworkRequest<?> request = (NetworkRequest<?>) msg.obj;
            switch (msg.what) {
                case MESSAGE_SUCCESS:
                    request.sendSuccess();
                    break;
                case MESSAGE_FAIL:
                    request.sendFail();
                    break;
            }

        }
    };


    public interface OnResultListener<T> {
        public void onSuccess(NetworkRequest<T> request, T result);

        public void onFail(NetworkRequest<T> request, int errorCode, String errorMessage, Throwable exception);
    }


    public <T> void getNetworkdata(NetworkRequest request, OnResultListener<T> listener) {
        request.setOnResultListener(listener);
           mExecutor.execute(request);
    }

    void sendSuccess(NetworkRequest<?> request) {
        Message msg = mHandler.obtainMessage(MESSAGE_SUCCESS, request);
        mHandler.sendMessage(msg);
    }

    void sendFail(NetworkRequest<?> request) {
        Message msg = mHandler.obtainMessage(MESSAGE_FAIL, request);
        mHandler.sendMessage(msg);
    }
*/


