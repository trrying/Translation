package com.owm.biubiuboom.net.retrofit;

import com.owm.biubiuboom.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ouweiming on 2016/11/1.
 */

public class ApiClient {
    public static Retrofit mRetrofit;

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (ApiClient.class){
                if (mRetrofit == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(loggingInterceptor);
                    }
                    OkHttpClient okHttpClient = builder.build();
                    mRetrofit = new Retrofit.Builder()
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
