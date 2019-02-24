package com.example.dong.xiang.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request  Request= originalRequest.newBuilder()
                .addHeader("userId",RetrofitUtils.getIntenter().getSp("userId"))
                .addHeader("sessionId",RetrofitUtils.getIntenter().getSp("sessionId"))
                .build();
        Response proceed = chain.proceed(Request);
        return proceed;
    }
}
