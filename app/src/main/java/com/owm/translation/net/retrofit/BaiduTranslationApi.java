package com.owm.translation.net.retrofit;

import com.owm.translation.model.BaiduTranslationBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 百度翻译接口
 * Created by ouweiming on 2016/11/2.
 */

public interface BaiduTranslationApi {

    @POST("api/trans/vip/translate")
    Observable<BaiduTranslationBean> translation4Baidu(@Query("q") String q
            , @Query("from") String from, @Query("to") String to, @Query("appid") String appid
            , @Query("salt") String salt, @Query("sign") String sign);
//    Observable<BaiduTranslationBean> translation4Baidu(@QueryMap Map<String, String> map);

}
