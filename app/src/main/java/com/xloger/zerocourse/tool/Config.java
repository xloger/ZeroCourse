package com.xloger.zerocourse.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created on 16/5/25 下午5:22.
 * Author: xloger
 */
public class Config {
    private static Config config;
    private SharedPreferences sp;


    private Config(Context context){
        sp=context.getSharedPreferences("config",0);
    }

    public static void createInstance(Context context){
        config = new Config(context);
    }

    public static Config newInstance(){
        if (config == null) {
            throw new IllegalStateException("ConfigUtil尚未初始化");
        }
        return config;
    }

    public void setConfig(String key, String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getConfig(String key){
        return sp.getString(key, null);
    }
}
