package com.owm.translation.view.translation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.owm.biubiuboom.view.mvp.MvpActivity;
import com.owm.translation.R;
import com.owm.translation.model.BaiduTranslationBean;
import com.owm.translation.model.YoudaoTranslationBean;
import com.owm.translation.presenter.TranslationPresenter;
import com.owm.translation.utils.ClipBroardUtil;
import com.owm.translation.utils.GlobalProperty;
import com.owm.translation.utils.SPUtil;
import com.owm.translation.weidget.YoudaoNestedScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 翻译界面
 * Created by ouweiming on 2016/11/3.
 */

public class TranslationActivity extends MvpActivity<TranslationPresenter> implements ITranslationView, RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.edit_query)
    EditText edit_query;
    @Bind(R.id.bt_tranlation)
    Button bt_tranlation;
    @Bind(R.id.rb_baidu)
    RadioButton rb_baidu;
    @Bind(R.id.rb_youdao)
    RadioButton rb_youdao;
    @Bind(R.id.view_youdao_result)
    YoudaoNestedScrollView mNsYoudaoResult;
    @Bind(R.id.rg_choose_type)
    RadioGroup mRgChooseType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mRgChooseType.setOnCheckedChangeListener(this);

        switch (SPUtil.getInt(GlobalProperty.TRANSLATION_TYPE, GlobalProperty.TRANSLATION_TYPE_BAIDU)) {
            case GlobalProperty.TRANSLATION_TYPE_BAIDU:
                rb_baidu.setChecked(true);
                break;
            case GlobalProperty.TRANSLATION_TYPE_YOUDAO:
                rb_youdao.setChecked(true);
                break;
        }
    }

    @NonNull
    @Override
    protected TranslationPresenter createPresenter() {
        return new TranslationPresenter(this);
    }

    @OnClick(R.id.bt_tranlation)
    public void tranlation(View view) {
        mPresenter.translation(edit_query.getText().toString(), getTranslationType());
    }

    public int getTranslationType() {
        int type = GlobalProperty.TRANSLATION_TYPE_BAIDU;
        if (rb_baidu.isChecked()) {
            type = GlobalProperty.TRANSLATION_TYPE_BAIDU;
        } else if (rb_youdao.isChecked()) {
            type = GlobalProperty.TRANSLATION_TYPE_YOUDAO;
        }
        return type;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(ClipBroardUtil.getClipBroardText(this))) {
            edit_query.setText(ClipBroardUtil.getClipBroardText(this));
            edit_query.setSelection(edit_query.length());
        }
    }

    @Override
    public void showResult(BaiduTranslationBean model) {
        showMessageDialog(model.getTrans_result().get(0).getDst());
    }

    @Override
    public void showResult(YoudaoTranslationBean model) {
        mNsYoudaoResult.setYoudaoTranslationBean(model);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_baidu:
                SPUtil.putInt(GlobalProperty.TRANSLATION_TYPE, GlobalProperty.TRANSLATION_TYPE_BAIDU);
                break;
            case R.id.rb_youdao:
                SPUtil.putInt(GlobalProperty.TRANSLATION_TYPE, GlobalProperty.TRANSLATION_TYPE_YOUDAO);
                break;
        }
    }
}
