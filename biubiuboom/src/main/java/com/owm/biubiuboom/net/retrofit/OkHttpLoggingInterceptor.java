package com.owm.biubiuboom.net.retrofit;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Retrofit+Okhttp+Rxjava 中的okHttp日志拦截器
 * Created by ouweiming on 2016/11/3.
 */

public class OkHttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //打印请求url
        System.out.println("-->URL " + request.method() + " " + request.url());

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        //打印返回url
        System.out.println("<--URL " + request.method() + " " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " ( " + tookMs + "ms )");

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                System.out.println("Couldn't decode the response body; charset is likely malformed.");
                return response;
            }
        }

        if (contentLength != 0) {
            //打印返回内容
            System.out.println("content(" + buffer.size() + "-byte)  " + buffer.clone().readString(charset));
        }

        return response;
    }
}
