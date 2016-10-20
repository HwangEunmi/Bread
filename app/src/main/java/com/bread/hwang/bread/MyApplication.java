package com.bread.hwang.bread;

import android.app.Application;
import android.content.Context;

/**
 * Created by Hwang on 2016-10-20.
 */

public class MyApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
