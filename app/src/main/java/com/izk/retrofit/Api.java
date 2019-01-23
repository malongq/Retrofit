package com.izk.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Malong
 * on 19/1/22.
 */
public interface Api {

    /*********************  Retrofit2 常用请求方法 示例 *********************/

    //GET请求，baseUrl后面必须带 "/" ，然后在来一个后续的，比如 ("index")，如果无参数，可以在方法后不加参数，一个参数可以直接Query，多参数用QueryMap
    @GET("index")
    Call<Info> getData(@QueryMap Map<String, String> map);

    @GET("index")
    Call<Info> getData2(@Query("id") String u_id);//一个参数。后面是 ？拼接

    @GET("index/{id}")
    Call<Info> getData3(@Path("id") String u_id);//一个参数。后面是 / 拼接

    @POST("index")
    Call<PostInfo> getPostData(@QueryMap Map<String, String> map);

    //这是将后面的参数装成一个Bean对象传过去，一般用于提交参数
    @POST("index")
    Call<PostInfo> getgetPostData2(@Body Info info);

    //这是将后面的参数装成一个FormUrlEncoded表单传过去，一般用于提交参数
    @FormUrlEncoded
    @POST("index")
    Call<PostInfo> getgetPostData3(@Field("id") String u_id,@Field("name") String u_name);

    //可以修改Header信息,get/post请求都可以
    @Headers({"User-Agent","malong"})
    @GET("index")//@POST("index")
    Call<Info> getData3(@QueryMap Map<String, String> map);


    /*********************  Retrofit2 与RxJava 配合使用 示例 *********************/

    //这两个是为了演示只用Retrofit2登录系统成功，拿到返回值，字啊用返回值中的数据当做参数去请求下一个接口，从而拿到用户信息

    @POST("index")
    Call<PostInfo> getInfo(@Body Info info);

    @GET("index")
    Call<Info> getInfo2(@Query("id") String u_id);


    //使用Retrofit2 与RxJava 配合使用
    @GET("index")
    Observable<PostInfo> getInfo3(@Body Info info);

    @GET("index")
    Observable<Info> getInfo4(@Query("id") String u_id);



}
