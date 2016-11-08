package com.owm.translation.view.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.owm.biubiuboom.view.base.BaseActivity;
import com.owm.translation.R;
import com.owm.translation.view.translation.TranslationActivity;

/**
 * Created by ouweiming on 2016/11/8.
 */

public class WelcomeActivity extends BaseActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, TranslationActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        Observable.timer(5, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
//            @Override
//            public void call(Long aLong) {
//                startActivity(new Intent(WelcomeActivity.this, TranslationActivity.class));
//            }
//        });
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }
}
