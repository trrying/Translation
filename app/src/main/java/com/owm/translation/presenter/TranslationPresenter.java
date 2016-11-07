package com.owm.translation.presenter;

import com.owm.biubiuboom.net.retrofit.ApiClient;
import com.owm.biubiuboom.net.retrofit.ApiHelper;
import com.owm.biubiuboom.net.retrofit.ICallBack;
import com.owm.biubiuboom.presenter.BasePresenter;
import com.owm.translation.model.BaiduTranslationBean;
import com.owm.translation.model.MovieResult;
import com.owm.translation.net.retrofit.BaiduTranslationApi;
import com.owm.translation.net.retrofit.DoubanMovieTop;
import com.owm.translation.view.translation.ITranslationView;

/**
 * 主界面控制器
 * Created by ouweiming on 2016/10/31.
 */

public class TranslationPresenter extends BasePresenter<ITranslationView>{

    private BaiduTranslationApi mBaiduTranslationApi;

    private DoubanMovieTop mDoubanMovieTop;

    public TranslationPresenter(ITranslationView view) {
        super(view);
        mBaiduTranslationApi = ApiClient.getRetrofit().create(BaiduTranslationApi.class);
        mDoubanMovieTop = ApiClient.getRetrofit().create(DoubanMovieTop.class);
    }

    public void translation(String query) {
        mView.showLoading();
        String salt = System.currentTimeMillis()+"";
        addSubscription(mBaiduTranslationApi.translation4Baidu(query, "auto", "auto", ApiHelper.APP_ID, salt
                , ApiHelper.getSign(query, salt)), translation);
    }

    public void getTopMovie() {
        mView.showLoading();
        addSubscription(mDoubanMovieTop.getTopMovie(1, 10), mMovieResultApiCallBack);
    }

    private ICallBack<BaiduTranslationBean> translation = new ICallBack<BaiduTranslationBean>() {
        @Override
        public void onSuccess(BaiduTranslationBean model) {
            if (model == null) {
                mView.showMessageDialog("访问出错");
                return;
            }
            if (model.getError_code() != null && !BaiduTranslationBean.successCode.equals(model.getError_code())) {
                mView.showMessageDialog(BaiduTranslationBean.getErrorCodes().get(model.getError_code()));
                return;
            }
            mView.showResult(model);
        }

        @Override
        public void onFailure(String msg) {
            mView.showMessageDialog(msg);
        }

        @Override
        public void onFinish() {
            mView.hideLoading();
        }
    };

    private ICallBack<MovieResult> mMovieResultApiCallBack = new ICallBack<MovieResult>() {
        @Override
        public void onSuccess(MovieResult model) {

        }

        @Override
        public void onFailure(String msg) {

        }

        @Override
        public void onFinish() {
            mView.hideLoading();
        }
    };

}
