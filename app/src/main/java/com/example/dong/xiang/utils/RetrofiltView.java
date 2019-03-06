package com.example.dong.xiang.utils;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofiltView {
    @POST
    @FormUrlEncoded
    Call<ResponseBody> DoPost( @Url String url, @FieldMap HashMap<String, String> params);

   @HTTP(method = "DELETE", hasBody = true)
    Call<ResponseBody> Dodelete( @Url String url,@QueryMap HashMap<String, String> params);

    @GET
    Call<ResponseBody> DoGet(@Url String url);

    @PUT
    Call<ResponseBody> NickName(@Url String url, @QueryMap HashMap<String,String> params);
}
