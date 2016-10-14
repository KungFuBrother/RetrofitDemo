package com.smartown.demo.retrofitdemo;

import com.smartown.demo.retrofitdemo.entity.ResponseEntity;
import com.smartown.demo.retrofitdemo.entity.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-10-14 15:38
 * <p/>
 * 描述：
 */
public interface MovieService {

    @GET("top250")
    Call<ResponseEntity<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<ResponseEntity<List<Subject>>> getTopMovieRx(@Query("start") int start, @Query("count") int count);

}
