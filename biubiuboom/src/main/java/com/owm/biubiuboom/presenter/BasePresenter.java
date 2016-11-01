package com.owm.biubiuboom.presenter;

/**
 * 上帝类--控制器
 * Created by ouweiming on 2016/10/31.
 */

public class BasePresenter<V> {

    public V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    public void onDestroy() {
        mView = null;
    }

}
