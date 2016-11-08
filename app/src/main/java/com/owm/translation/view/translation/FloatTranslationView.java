package com.owm.translation.view.translation;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.owm.biubiuboom.utils.LogUtil;
import com.owm.translation.R;
import com.owm.translation.common.CommonApplication;
import com.owm.translation.model.BaiduTranslationBean;
import com.owm.translation.presenter.TranslationPresenter;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 浮动翻译窗口
 * Created by ouweiming on 2016/11/7.
 */

public class FloatTranslationView implements ITranslationView {

    private Context mContext;
    private TranslationPresenter mPresenter;

    private View mainView;
    private WindowManager mWindowManager;

    @Bind(R.id.tv_dst)
    TextView tv_dst;
    @Bind(R.id.tv_src)
    TextView tv_src;

    private static FloatTranslationView install;

    public static FloatTranslationView getInstall() {
        if (install == null) {
            synchronized (FloatTranslationView.class) {
                if (install == null) {
                    install = new FloatTranslationView(CommonApplication.getmApplication());
                }
            }
        }
        return install;
    }

    private FloatTranslationView(Context context){
        mContext = context;
        mPresenter = new TranslationPresenter(this);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        init();
    }

    private void init() {
        mainView = View.inflate(mContext, R.layout.win_quick_query, null);
        ButterKnife.bind(this, mainView);
    }

    public void translation(String dst) {
        tv_dst.setText(dst);
        mPresenter.translation(dst);
    }

    private int getType(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            return WindowManager.LayoutParams.TYPE_PHONE;
        }
    }

    private void showView(){
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT, getType(), 0, PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.TOP;
        mWindowManager.addView(mainView, layoutParams);
    }

    private void hideView(){
        if (mWindowManager != null && mainView != null) {
            mWindowManager.removeView(mainView);
        }
    }

    @OnClick(R.id.rl_result)
    public void clickResult(View view){
        showMessageDialog("click result");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessageDialog(String message) {

    }

    @Override
    public void showResult(BaiduTranslationBean model) {
        if (model == null || model.getTrans_result() == null || model.getTrans_result().isEmpty()) {
            LogUtil.i("show result model is error");
            return;
        }
        tv_dst.setText(model.getTrans_result().get(0).getDst());
        tv_src.setText(model.getTrans_result().get(0).getSrc());
        showView();

        Observable.timer(5000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                hideView();
            }
        });
    }
}
