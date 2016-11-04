package com.owm.translation.service;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.owm.biubiuboom.utils.LogUtil;
import com.owm.translation.utils.ClipBroardUtil;

/**
 * 监听剪切板变化服务
 * Created by ouweiming on 2016/11/4.
 */

public class ClipboardManagerService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {

    private ClipboardManager mClipboardManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mClipboardManager == null) {
            mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            mClipboardManager.addPrimaryClipChangedListener(this);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onPrimaryClipChanged() {
        LogUtil.i(ClipBroardUtil.getClipBroardText(getApplicationContext()));
    }
}
