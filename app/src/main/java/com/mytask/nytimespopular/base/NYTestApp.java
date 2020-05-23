package com.mytask.nytimespopular.base;

import android.app.Application;
import android.content.Context;

public class NYTestApp extends Application {

    private static NYTestApp sInstance;

    public static NYTestApp getInstance() {
        if (sInstance == null)
            sInstance = new NYTestApp();
        return sInstance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
