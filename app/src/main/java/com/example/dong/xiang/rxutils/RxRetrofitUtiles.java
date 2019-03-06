package com.example.dong.xiang.rxutils;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.api.UserApi;
import com.example.dong.xiang.bean.UploadBean;
import com.example.dong.xiang.utils.HeaderInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class RxRetrofitUtiles {
    private  static  RxRetrofitUtiles instance;
    private final Retrofit retrofit;

    private  RxRetrofitUtiles(){
        HttpLoggingInterceptor httpLoggingInterceptor =new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(UserApi.USER_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }
    public static RxRetrofitUtiles getInstance(){
        if(instance==null){
            synchronized (RxRetrofitUtiles.class){
                if(instance==null){
                    instance = new RxRetrofitUtiles();
                }
            }
        }
        return instance;
    }




}
