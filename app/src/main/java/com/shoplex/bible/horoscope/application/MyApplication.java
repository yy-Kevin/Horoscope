package com.shoplex.bible.horoscope.application;

import android.app.Application;

/**
 * Created by qsk on 2017/4/25.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
//        AutoLayoutConifg.getInstance().useDeviceSize();
    }



    public static MyApplication getInstance() {
        return instance;
    }
}
