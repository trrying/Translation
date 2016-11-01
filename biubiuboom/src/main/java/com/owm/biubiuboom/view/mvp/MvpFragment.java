package com.owm.biubiuboom.view.mvp;

import com.owm.biubiuboom.presenter.BasePresenter;
import com.owm.biubiuboom.view.base.BaseFragment;

/**
 * Created by ouweiming on 2016/10/31.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
