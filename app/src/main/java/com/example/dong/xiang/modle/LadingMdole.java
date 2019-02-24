package com.example.dong.xiang.modle;

import android.os.Handler;
import android.text.TextUtils;

import com.example.dong.xiang.api.LadingAPI;
import com.example.dong.xiang.bean.LadingBean;
import com.example.dong.xiang.contract.LadingContract;
import com.example.dong.xiang.net.RequestCallback;
import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LadingMdole implements LadingContract.ILadingModle {
    Handler handler =new Handler();
    @Override
    public void Lading(HashMap<String, String> params, final RequestCallback callback) {
        OkhttpUtils.getInstance().doPost(LadingAPI.LADING, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
               if(!TextUtils.isEmpty(result)){
                   paraenster(result,callback);
               }
            }

            @Override
            public void failure(String msg) {
               callback.failure(msg);
            }
        });
    }

    private void paraenster(String result, final RequestCallback callback) {
        final LadingBean ladingBean = new Gson().fromJson(result, LadingBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.succeess(ladingBean);
            }
        });

    }
}
