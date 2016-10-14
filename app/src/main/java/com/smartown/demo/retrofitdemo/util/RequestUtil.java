package com.smartown.demo.retrofitdemo.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-10-14 17:13
 * <p/>
 * 描述：
 */
public class RequestUtil<S, E> {

    private static RequestUtil requestUtil;
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private RequestUtil() {
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

    public static RequestUtil getInstance() {
        if (requestUtil == null) {
            synchronized (RequestUtil.class) {
                if (requestUtil == null) {
                    requestUtil = new RequestUtil();
                }
            }
        }
        return requestUtil;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void request(Class<S> service, Subscriber<E> subscriber, String methodName, Object... params) {
        S s = retrofit.create(service);
        try {
            Method method = service.getMethod(methodName);
            ((Observable<E>) method.invoke(s, params))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
