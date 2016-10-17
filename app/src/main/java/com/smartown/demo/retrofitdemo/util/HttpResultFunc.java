package com.smartown.demo.retrofitdemo.util;

import com.smartown.demo.retrofitdemo.entity.HttpResponse;

import rx.functions.Func1;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-10-17 14:53
 * <p>
 * 描述：
 */
public class HttpResultFunc<T> implements Func1<HttpResponse<T>, T> {

    @Override
    public T call(HttpResponse<T> httpResponse) {
        if (!httpResponse.isSuccess()) {
            throw new HttpResultException("");
        }
        return httpResponse.getSubjects();
    }

}
