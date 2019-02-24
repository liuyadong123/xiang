package com.example.dong.xiang.modle;

import android.os.Handler;
import android.text.TextUtils;

import com.example.dong.xiang.api.LadingAPI;
import com.example.dong.xiang.api.RegisterApi;
import com.example.dong.xiang.bean.LadingBean;
import com.example.dong.xiang.bean.RefisterBean;
import com.example.dong.xiang.contract.RegisterContract;
import com.example.dong.xiang.net.RequestCallback;
import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class RegisterModle implements RegisterContract.IRegisterModle {
    Handler handler =new Handler();
    @Override
    public void Register(HashMap<String, String> params, final RequestCallback callback) {

            OkhttpUtils.getInstance().doPost(RegisterApi.REGISTER, params, new OkhttpCallback() {
                @Override
                public void success(String result) {
                   callback.success(result);
                }

                @Override
                public void failure(String msg) {
                    callback.failure(msg);
                }
            });
        }





}
