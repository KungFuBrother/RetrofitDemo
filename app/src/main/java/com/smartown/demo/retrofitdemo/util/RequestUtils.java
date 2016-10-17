package com.smartown.demo.retrofitdemo.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-10-14 17:13
 * <p/>
 * 描述：
 */
public class RequestUtils {

    private static RequestUtils requestUtils;
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private RequestUtils() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.douban.com/v2/movie/")
                .build();
    }

    public static RequestUtils getInstance() {
        if (requestUtils == null) {
            synchronized (RequestUtils.class) {
                if (requestUtils == null) {
                    requestUtils = new RequestUtils();
                }
            }
        }
        return requestUtils;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
