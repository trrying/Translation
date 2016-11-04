package com.owm.biubiuboom.presenter;

import com.owm.biubiuboom.net.retrofit.ApiCallBackPro;
import com.owm.biubiuboom.net.retrofit.ICallBack;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 上帝类--控制器
 * Created by ouweiming on 2016/10/31.
 */

public class BasePresenter<V> {

    protected V mView;

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public BasePresenter(V view) {
        mView = view;
    }

    public void onDestroy() {
        mView = null;
        unSubscribe();
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscription(Observable observable, Subscriber subscriber) {
        mCompositeSubscription.add(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber));
    }

    protected void addSubscription(Observable observable, ICallBack callBack) {
        mCompositeSubscription.add(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ApiCallBackPro(callBack)));
    }

}
