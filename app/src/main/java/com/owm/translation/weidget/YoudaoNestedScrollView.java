package com.owm.translation.weidget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.owm.translation.R;
import com.owm.translation.adapter.YoudaoResultExplainsAdapter;
import com.owm.translation.adapter.YoudaoResultWebAdapter;
import com.owm.translation.model.YoudaoTranslationBean;
import com.owm.translation.utils.ViewUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 有道翻译结果展示
 * Created by ouweiming on 2016/11/10.
 */

public class YoudaoNestedScrollView extends NestedScrollView {

    @Bind(R.id.tv_src)
    CustomTextView mTvSrc;
    @Bind(R.id.ll_basic)
    LinearLayout mLlBasic;
    @Bind(R.id.tv_phonetic)
    TextView mTvPhonetic;
    @Bind(R.id.Ll_phonetic)
    LinearLayout mLlPhonetic;
    @Bind(R.id.tv_us_phonetic)
    TextView mTvUsPhonetic;
    @Bind(R.id.Ll_us_phonetic)
    LinearLayout mLlUsPhonetic;
    @Bind(R.id.tv_uk_phonetic)
    TextView mTvUkPhonetic;
    @Bind(R.id.Ll_uk_phonetic)
    LinearLayout mLlUkPhonetic;
    @Bind(R.id.rv_explains)
    RecyclerView mRvExplains;
    @Bind(R.id.rv_web)
    RecyclerView mRvWeb;
    @Bind(R.id.ll_web)
    LinearLayout mLlWeb;
    @Bind(R.id.ll_youdao_result)
    LinearLayout mLlYoudaoResult;

    private YoudaoResultExplainsAdapter mYoudaoResultExplainsAdapter;
    private YoudaoResultWebAdapter mYoudaoResultWebAdapter;

    private YoudaoTranslationBean mYoudaoTranslationBean;


    public YoudaoNestedScrollView(Context context) {
        this(context, null);
    }

    public YoudaoNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YoudaoNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int layoutId = R.layout.view_youdao_result;
    }

    public YoudaoTranslationBean getYoudaoTranslationBean() {
        return mYoudaoTranslationBean;
    }

    public void setYoudaoTranslationBean(YoudaoTranslationBean youdaoTranslationBean) {
        ButterKnife.bind(this, this);
        mYoudaoTranslationBean = youdaoTranslationBean;
        if (mYoudaoTranslationBean != null) {
            ViewUtil.visble(mLlYoudaoResult);
            refreshView();
        } else {
            ViewUtil.gone(mLlYoudaoResult);
        }
    }

    private void refreshView() {
        //源词
        ViewUtil.setText(mTvSrc, mYoudaoTranslationBean.getQuery(), true);

        if (mYoudaoTranslationBean.getBasic() != null) {
            //发音
            ViewUtil.setText(mTvPhonetic, mYoudaoTranslationBean.getBasic().getPhonetic(), mLlPhonetic);
            ViewUtil.setText(mTvUsPhonetic, mYoudaoTranslationBean.getBasic().getUs_phonetic(), mLlUsPhonetic);
            ViewUtil.setText(mTvUkPhonetic, mYoudaoTranslationBean.getBasic().getUk_phonetic(), mLlUkPhonetic);
            //释义
            if (mYoudaoTranslationBean.getBasic().getExplains() != null) {
                if (mYoudaoResultExplainsAdapter == null) {
                    mYoudaoResultExplainsAdapter = new YoudaoResultExplainsAdapter(mYoudaoTranslationBean.getBasic().getExplains());
                    mRvExplains.setAdapter(mYoudaoResultExplainsAdapter);
                    mRvExplains.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    mYoudaoResultExplainsAdapter.getData().clear();
                    mYoudaoResultExplainsAdapter.addData(mYoudaoTranslationBean.getBasic().getExplains());
                }
            }
            ViewUtil.visble(mLlBasic);
        } else {
            ViewUtil.gone(mLlBasic);
        }

        //网络释义
        if (mYoudaoTranslationBean.getWeb() != null) {
            if (mYoudaoResultWebAdapter == null) {
                mYoudaoResultWebAdapter = new YoudaoResultWebAdapter(mYoudaoTranslationBean.getWeb());
                mRvWeb.setAdapter(mYoudaoResultWebAdapter);
                mRvWeb.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                mYoudaoResultWebAdapter.getData().clear();
                mYoudaoResultWebAdapter.addData(mYoudaoTranslationBean.getWeb());
            }
            ViewUtil.visble(mLlWeb);
        } else {
            ViewUtil.gone(mLlWeb);
        }
    }

}
