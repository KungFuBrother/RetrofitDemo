package com.smartown.demo.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.smartown.demo.retrofitdemo.entity.HttpResponse;
import com.smartown.demo.retrofitdemo.entity.Subject;
import com.smartown.demo.retrofitdemo.util.MovieRequestUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.result);
        button = (TextView) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getMovie();
//                getMovieRx();
//                getTopMovie();
                getTopMovieMap();
            }
        });
    }

    //进行网络请求
    private void getMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService service = retrofit.create(MovieService.class);
        Call<HttpResponse<List<Subject>>> call = service.getTopMovie(0, 1);
        call.enqueue(new Callback<HttpResponse<List<Subject>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<Subject>>> call, Response<HttpResponse<List<Subject>>> response) {
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<HttpResponse<List<Subject>>> call, Throwable t) {
                textView.setText(t.toString());
            }
        });
    }

    //使用RxJava
    private void getMovieRx() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MovieService service = retrofit.create(MovieService.class);
        Observable<HttpResponse<List<Subject>>> observable = service.getTopMovieRx(0, 1);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse<List<Subject>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.setText(e.toString());
                    }

                    @Override
                    public void onNext(HttpResponse<List<Subject>> listHttpResponse) {
                        textView.setText(listHttpResponse.toString());
                    }
                });
    }

    private void getTopMovie() {
        final MovieRequestUtils utils = new MovieRequestUtils();
        utils.getTopMovie(0, 1, new Subscriber<HttpResponse<List<Subject>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                textView.setText(e.toString());
            }

            @Override
            public void onNext(HttpResponse<List<Subject>> listHttpResponse) {
                textView.setText(listHttpResponse.toString());
            }

        });
    }

    private void getTopMovieMap() {
        final MovieRequestUtils utils = new MovieRequestUtils();
        utils.getTopMovieMap(0, 1, new Subscriber<List<Subject>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                textView.setText(e.toString());
            }

            @Override
            public void onNext(List<Subject> subjects) {
                textView.setText(subjects.toString());
            }

        });
    }

}
