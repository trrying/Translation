package com.owm.biubiuboom.net.retrofit;

/**
 * Retrofit回调接口
 * Created by ouweiming on 2016/11/4.
 */

public interface ICallBack<M> {

    void onSuccess(M model);

    void onFailure(String msg);

    void onFinish();

}
