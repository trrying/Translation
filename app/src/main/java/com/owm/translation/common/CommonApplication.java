package com.owm.translation.common;

import android.app.Application;
import android.content.Intent;

import com.owm.translation.service.ClipboardManagerService;

/**
 * Application
 * Created by ouweiming on 2016/11/4.
 */

public class CommonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, ClipboardManagerService.class));
    }
}
