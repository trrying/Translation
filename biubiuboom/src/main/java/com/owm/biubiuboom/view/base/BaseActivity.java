package com.owm.biubiuboom.view.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.owm.biubiuboom.event.IEvent;

import butterknife.ButterKnife;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ouweiming on 2016/10/31.
 */

public class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private PublishSubject<IEvent> mEventPublishSubject;

    public final PublishSubject<IEvent> getEventBus() {
        if (mEventPublishSubject == null) {
            synchronized (this) {
                if (mEventPublishSubject == null) {
                    mEventPublishSubject = PublishSubject.create();
                }
            }
        }
        return mEventPublishSubject;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }


    private Toast mToast;
    public void showToast(@StringRes int resId) {
        showToast(getText(resId));
    }
    public void showToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

}
