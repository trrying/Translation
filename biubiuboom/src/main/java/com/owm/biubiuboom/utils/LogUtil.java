package com.owm.biubiuboom.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

/**
 * 日志打印工具类
 * Created by ouweiming on 2016/11/4.
 */

public class LogUtil {
    private static String customTagPrefix = "owm"; // 自定义Tag的前缀，可以是作者名
    private static boolean isShowlog = true;

    public static void i (String info){
        if (!isShowlog) {
            return;
        }
        Log.i(generateTag(getCallerStackTraceElement()), info);
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(Line:%d)"; // 占位符
        String callerClazzName = caller.getClassName(); // 获取到类名
        callerClazzName = callerClazzName.substring(callerClazzName
                .lastIndexOf(".") + 1);
        tag = String.format(Locale.getDefault(), tag, callerClazzName, caller.getMethodName(),
                caller.getLineNumber()); // 替换
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":"
                + tag;
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

}
