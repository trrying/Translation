package com.owm.biubiuboom.net.retrofit;

/**
 * Retrofit中Rx回调代理，把回调传递给IcallBack
 * Created by ouweiming on 2016/11/4.
 */

public class ApiCallBackPro<M> extends ApiCallBack<M> {
    private ICallBack<M> mCallBack;

    public ApiCallBackPro(ICallBack<M> callBack){
        mCallBack = callBack;
    }

    @Override
    public void onSuccess(M model) {
        if (mCallBack != null) {
            mCallBack.onSuccess(model);
        }
    }

    @Override
    public void onFailure(String msg) {
        if (mCallBack != null) {
            mCallBack.onFailure(msg);
        }
    }

    @Override
    public void onFinish() {
        if (mCallBack != null) {
            mCallBack.onFinish();
        }
    }
}
