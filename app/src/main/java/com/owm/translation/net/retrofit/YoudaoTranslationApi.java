package com.owm.translation.net.retrofit;

import com.owm.translation.model.BaiduTranslationBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 有道翻译Api
 * Created by ouweiming on 2016/11/9.
 */

public interface YoudaoTranslationApi {

    @GET("openapi.do")
    Observable<BaiduTranslationBean> translation4Youdao(@Query("q") String q
            , @QueryMap Map<String, String> defaultParams);


}
