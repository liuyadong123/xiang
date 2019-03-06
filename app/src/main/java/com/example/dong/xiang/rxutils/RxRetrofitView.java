package com.example.dong.xiang.rxutils;

import com.example.dong.xiang.bean.UploadBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface RxRetrofitView {
    @POST("small/user/verify/v1/modifyHeadPic")
    @Multipart
    Observable<ResponseBody> upload(@HeaderMap Map<String,String> headerParams, @Part MultipartBody.Part f);
}
