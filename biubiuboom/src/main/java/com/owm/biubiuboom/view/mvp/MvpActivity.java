package com.owm.biubiuboom.view.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.owm.biubiuboom.presenter.BasePresenter;
import com.owm.biubiuboom.view.base.BaseActivity;

/**
 * Mvp activity基类
 * Created by ouweiming on 2016/10/31.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements IView{

    protected P mPresenter;

    protected AlertDialog mAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract @NonNull P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public void showLoading() {
        if (mAlertDialog == null) {
            mAlertDialog = getAlertDialog();
        }
        mAlertDialog.show();
    }

    private AlertDialog getAlertDialog() {
        return new AlertDialog.Builder(this).setMessage("正在加载").create();
    }

    @Override
    public void hideLoading() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.hide();
        }
    }

    @Override
    public void showMessageDialog(String message) {
        showToast(message);
    }
}
