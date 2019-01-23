package com.izk.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Malong
 * on 19/1/22.
 */
public class RxActivity extends AppCompatActivity {

    private Button tv_request;
    private Button tv_request2;
    private Api api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/idcard/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = build.create(Api.class);

        tv_request = findViewById(R.id.tv_request);
        tv_request2 = findViewById(R.id.tv_request2);

        tv_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //只用 Retrofit 请求
                onlyRetrofit();
            }
        });

        tv_request2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrofit2 与RxJava 配合使用
                retrofitAndRxjava();
            }
        });
    }

    //Retrofit2 与RxJava 配合使用
    private void retrofitAndRxjava() {

        //这个跟只用 Retrofit 请求 模式一样，都是嵌套
        api.getInfo3(new Info("manao")).subscribe(new Consumer<PostInfo>() {
            @Override
            public void accept(PostInfo postInfo) throws Exception {
                //如果登录接口请求成功，再去拿出请求返回值，当做参数请求信息接口
                String reason = postInfo.getResult().getStat();
                api.getInfo4(reason).subscribe(new Consumer<Info>() {
                    @Override
                    public void accept(Info info) throws Exception {
                        //此处显示用户信息
                    }
                });
            }
        });


        /*********************  Retrofit2 与RxJava 配合使用 示例 *********************/
        //这个就很简洁，Retrofit2 与RxJava 配合使用 请求，使用flatMap操作符转换操作
        api.getInfo3(new Info("manao")).flatMap(new Function<PostInfo, ObservableSource<Info>>() {
            @Override
            public ObservableSource<Info> apply(PostInfo postInfo) throws Exception {

                String reason = postInfo.getReason();

                return api.getInfo4(reason);
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<Info>() {
              @Override
              public void accept(Info info) throws Exception {
                  //此处显示用户信息
              }
          }, new Consumer<Throwable>() {
              @Override
              public void accept(Throwable throwable) throws Exception {
                  //异常处理
              }
          });



    }

    //只用 Retrofit 请求
    private void onlyRetrofit() {

        //先去请求登录接口
        api.getInfo(new Info("malong")).enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {


                //如果登录接口请求成功，再去拿出请求返回值，当做参数请求信息接口
                String reason = response.body().getReason();
                api.getInfo2(reason).enqueue(new Callback<Info>() {
                    @Override
                    public void onResponse(Call<Info> call, Response<Info> response) {
                        //此处显示用户信息
                    }

                    @Override
                    public void onFailure(Call<Info> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }

            @Override
            public void onFailure(Call<PostInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
