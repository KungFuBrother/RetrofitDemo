package com.smartown.demo.retrofitdemo.util;

import com.smartown.demo.retrofitdemo.MovieService;
import com.smartown.demo.retrofitdemo.entity.ResponseEntity;
import com.smartown.demo.retrofitdemo.entity.Subject;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-10-14 17:01
 * <p/>
 * 描述：
 */
public class MovieRequestUtil {

    private MovieService service;

    public MovieRequestUtil() {
        service = RequestUtil.getInstance().getRetrofit().create(MovieService.class);
    }

    public void getTopMovie(int start, int count, Subscriber<ResponseEntity<List<Subject>>> subscriber) {
        service.getTopMovieRx(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
