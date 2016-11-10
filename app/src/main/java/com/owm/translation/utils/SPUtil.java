package com.owm.translation.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.owm.translation.common.CommonApplication;

/**
 * SharedPreferences 工具类
 * Created by ouweiming on 2016/11/10.
 */

public class SPUtil {
    private static final String name = "com.owm.translation";

    public static void putInt(String key, int value) {
        getEditor().putInt(key, value).commit();
    }

    public static int getInt(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

    public static int getInt(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    public static SharedPreferences getSharedPreferences() {
        return CommonApplication.getmApplication().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

}
