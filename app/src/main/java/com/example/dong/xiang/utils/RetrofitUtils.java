package com.example.dong.xiang.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.app.App;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static  RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    private  RetrofitUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor =new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                 .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)

                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public  static  RetrofitUtils getIntenter(){
          if (retrofitUtils==null){
              synchronized (RetrofitUtils.class){
                  if (retrofitUtils==null){
                      retrofitUtils=new RetrofitUtils();
                  }
              }
          }
          return retrofitUtils;
    }
    public SharedPreferences getSp(){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("uerer",Context.MODE_PRIVATE);
        if (sharedPreferences!=null){
            return sharedPreferences;
        }

        return null;
    }

    public void putSp(String key,String value){
        getSp().edit().putString(key,value).commit();
}
    /**
     * 存储数据
     * @param key
     */
    public String getSp(String key){
        return getSp().getString(key,"");
    }


    public  void  Posts(String url, HashMap<String,String> params, final RetrofitCallback callback){
           RetrofiltView retrofiltView =retrofit.create(RetrofiltView.class);
           retrofiltView.DoPost(url, params).enqueue(new Callback<ResponseBody>() {
               @Override
               public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                   try {
                       if (response.code()==200){
                           String string = response.body().string();
                           if (callback!=null){
                               callback.OnSuccess(string);
                           }

                       }



                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }

               @Override
               public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (callback!=null){
                            callback.OnFailure("请求失败");
                        }
               }
           });

    }
    public  void  Delete(String url, HashMap<String,String> params, final RetrofitCallback callback){
        RetrofiltView retrofiltView =retrofit.create(RetrofiltView.class);
        retrofiltView.Dodelete(url,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200){
                        String string = response.body().string();
                        if (callback!=null){
                            callback.OnSuccess(string);
                        }

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback!=null){
                    callback.OnFailure("请求失败");
                }
            }
        });

    }
    public  void Gets(String url, final RetrofitCallback callback){
                  RetrofiltView retrofiltView =retrofit.create(RetrofiltView.class);
                  retrofiltView.DoGet(url).enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                          try {
                               if (response.code()==200){
                                    String string = response.body().string();
                                    if (callback!=null){ callback.OnSuccess(string);
                                    }
                               }

                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }

                      @Override
                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                             if (callback!=null){
                                 callback.OnFailure("请求失败");
                             }
                      }
                  });
    }
    public void NiceName(String url,HashMap<String,String> params,final RetrofitCallback callback){

        RetrofiltView retrofiltView =retrofit.create(RetrofiltView.class);

        retrofiltView.NickName(url,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                     if (response.code()==200){
                            String string = response.body().string();
                            if (callback!=null){
                                callback.OnSuccess(string);
                            }
                     }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (callback!=null){
                        callback.OnFailure("请求失败");
                    }
            }
        });
    }

}
