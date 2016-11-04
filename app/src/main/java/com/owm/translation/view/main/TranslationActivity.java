package com.owm.translation.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.owm.biubiuboom.view.mvp.MvpActivity;
import com.owm.translation.R;
import com.owm.translation.presenter.MainPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 翻译界面
 * Created by ouweiming on 2016/11/3.
 */

public class TranslationActivity extends MvpActivity<MainPresenter> implements IMainView{

    @Bind(R.id.edit_query)
    EditText edit_query;
    @Bind(R.id.bt_tranlation)
    Button bt_tranlation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @OnClick(R.id.bt_tranlation)
    public void tranlation(View view) {
        mPresenter.translation(edit_query.getText().toString());
//        mPresenter.getTopMovie();
    }

}
