package com.owm.translation.presenter;

import com.owm.biubiuboom.net.retrofit.ApiClient;
import com.owm.biubiuboom.net.retrofit.ApiHelper;
import com.owm.biubiuboom.net.retrofit.ICallBack;
import com.owm.biubiuboom.presenter.BasePresenter;
import com.owm.translation.model.BaiduTranslationBean;
import com.owm.translation.model.MovieResult;
import com.owm.translation.model.YoudaoTranslationBean;
import com.owm.translation.net.retrofit.BaiduTranslationApi;
import com.owm.translation.net.retrofit.DoubanMovieTop;
import com.owm.translation.net.retrofit.YoudaoTranslationApi;
import com.owm.translation.utils.GlobalProperty;
import com.owm.translation.view.translation.ITranslationView;

import java.util.HashMap;
import java.util.Map;

/**
 * 主界面控制器
 * Created by ouweiming on 2016/10/31.
 */

public class TranslationPresenter extends BasePresenter<ITranslationView>{

    private BaiduTranslationApi mBaiduTranslationApi;
    private YoudaoTranslationApi mYoudaoTranslationApi;

    private DoubanMovieTop mDoubanMovieTop;

    public TranslationPresenter(ITranslationView view) {
        super(view);
        mBaiduTranslationApi = ApiClient.getBaiduFanYiRetrofit().create(BaiduTranslationApi.class);
        mYoudaoTranslationApi = ApiClient.getYouDaoFanYiRetrofit().create(YoudaoTranslationApi.class);
//        mDoubanMovieTop = ApiClient.getBaiduFanYiRetrofit().create(DoubanMovieTop.class);
    }

    public void translation(String query) {
        translation(query, GlobalProperty.TRANSLATION_TYPE_BAIDU);
    }

    public void translation(String query, int type) {
        mView.showLoading();
        switch (type) {
            case GlobalProperty.TRANSLATION_TYPE_YOUDAO:
            translation4Youdao(query);
            break;
            case GlobalProperty.TRANSLATION_TYPE_BAIDU:
            default:
                translation4Baidu(query);
                break;
        }
    }

    private void translation4Baidu(String query) {
        String salt = System.currentTimeMillis()+"";
        addSubscription(mBaiduTranslationApi.translation4Baidu(query, "auto", "auto", ApiHelper.APP_ID, salt
                , ApiHelper.getSign(query, salt)), translation);
    }

    private void translation4Youdao(String query) {
        Map<String, String> params = new HashMap<>();
        params.put("keyfrom","AnyHelpers");
        params.put("key","1757159193");
        params.put("type","data");
        params.put("doctype","json");
        params.put("version","1.1");
        addSubscription(mYoudaoTranslationApi.translation4Youdao(query, params), youdaoResult);
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
            if (model.getError_code() != null && !BaiduTranslationBean.SUCCESS_CODE.equals(model.getError_code())) {
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

    private ICallBack<YoudaoTranslationBean> youdaoResult = new ICallBack<YoudaoTranslationBean>() {
        @Override
        public void onSuccess(YoudaoTranslationBean model) {
            if (model == null) {
                mView.showMessageDialog("访问出错");
                return;
            }
            if (model.getErrorCode() != null && !YoudaoTranslationBean.SUCCESS_CODE.equals(model.getErrorCode())) {
                mView.showMessageDialog(YoudaoTranslationBean.getErrorCodes().get(model.getErrorCode()));
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
