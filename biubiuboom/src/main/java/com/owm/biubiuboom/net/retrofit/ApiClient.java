package com.owm.biubiuboom.net.retrofit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * api客户端
 * Created by ouweiming on 2016/11/1.
 */

public class ApiClient {
    private static Map<String, Retrofit> mRetrofit = new ConcurrentHashMap<>();

    public static String baidu_fanyi_URL = "http://api.fanyi.baidu.com/";
    public static String youdao_fanyi_URL = "http://fanyi.youdao.com/";

    public static Retrofit getBaiduFanYiRetrofit() {
        return getRetrofit(baidu_fanyi_URL);
    }

    public static Retrofit getYouDaoFanYiRetrofit() {
        return getRetrofit(youdao_fanyi_URL);
    }


    public static Retrofit getRetrofit(String baseUrl) {
        if (mRetrofit.get(baseUrl) == null) {
            synchronized (ApiClient.class){
                if (mRetrofit.get(baseUrl) == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.addInterceptor(new OkHttpLoggingInterceptor());

                    OkHttpClient okHttpClient = builder
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient)
                            .build();
                    mRetrofit.put(baseUrl, retrofit);
                }
            }
        }
        return mRetrofit.get(baseUrl);
    }


}
