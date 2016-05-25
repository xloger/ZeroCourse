package com.xloger.zerocourse.view;

import android.app.Application;

import com.xloger.zerocourse.tool.Config;

/**
 * Created on 16/5/25 下午6:33.
 * Author: xloger
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Config.createInstance(this);
    }
}
