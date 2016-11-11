package com.owm.translation.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * View的一些判断操作工具类
 * Created by ouweiming on 2016/11/10.
 */

public class ViewUtil {

    /**
     * 设置文本（判断是否为空）
     * @param textView 源view
     * @param text 文本
     * @return 设置成功返回true
     */
    public static boolean setText(TextView textView, String text) {
        return setText(textView, text, false);
    }

    /**
     * 设置文本（判断是否为空）
     * @param textView 源view
     * @param text 文本
     * @param isGone
     * @return 设置成功返回true
     */
    public static boolean setText(TextView textView, String text, boolean isGone) {
        if (textView == null) {
            return false;
        }
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
            return false;
        }
        textView.setText(text);
        visble(textView);
        return true;
    }

    /**
     * 设置文本（判断是否为空）
     * @param textView 源view
     * @param text 文本
     * @param operateView 如果设置失败就隐藏,成功就显示
     * @return 设置成功返回true
     */
    public static boolean setText(TextView textView, String text, View operateView) {
        if (!setText(textView, text)) {
            gone(operateView);
            return false;
        }
        visble(operateView);
        return true;
    }

    public static void gone(View view){
        if (view == null) return;
        view.setVisibility(View.GONE);
    }

    public static void visble(View view) {
        if (view == null) return;
        view.setVisibility(View.VISIBLE);
    }

    public static void isVisble(View view, boolean isVisble) {
        if (view == null) return;
        view.setVisibility(isVisble ? View.VISIBLE : View.GONE);
    }

}
