package com.owm.biubiuboom.net.retrofit;

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
    public static Retrofit mRetrofit;

    public static String baseUrl = "http://api.fanyi.baidu.com/";
//    public static String baseUrl = "https://api.douban.com/";

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (ApiClient.class){
                if (mRetrofit == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                    if (BuildConfig.DEBUG) {
//                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                        builder.addInterceptor(loggingInterceptor);
//                    }
                    builder.addInterceptor(new OkHttpLoggingInterceptor());

                    OkHttpClient okHttpClient = builder
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build();

                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient)
                            .build();
                }
            }
        }
        return mRetrofit;
    }


}
