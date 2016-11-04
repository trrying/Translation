package com.owm.translation.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * 剪切板工具类
 * Created by ouweiming on 2016/11/4.
 */

public class ClipBroardUtil {

    /**
     * 类型一:text
     * 往Clip中放入数据
     */
    public static void put(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData textCd = ClipData.newPlainText(System.currentTimeMillis() + "", text);
        clipboard.setPrimaryClip(textCd);
    }

    /**
     * 类型二:Uri
     * 往Clip中放入数据
     */
    public static void put(Context context, Uri copyUri) {
        ClipData clipUri = ClipData.newUri(context.getContentResolver(), System.currentTimeMillis() + "", copyUri);
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(clipUri);
    }

    /**
     * 类型三:Intent
     * 往Clip中放入数据
     */
    public static void put(Context context, Intent intent) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipIntent = ClipData.newIntent("Intent", intent);
        clipboard.setPrimaryClip(clipIntent);
    }



    /**
     * 获取剪切板的文本复制
     * @param context 上下文
     */
    public static String getClipBroardText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            ClipData clipData = clipboard.getPrimaryClip();
            ClipData.Item item = clipData.getItemAt(0);
            return item.getText().toString();
        }
        return null;
    }


}
