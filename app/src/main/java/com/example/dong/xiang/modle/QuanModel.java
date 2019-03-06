package com.example.dong.xiang.modle;

import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.contract.QuanContract;
import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.utils.RetrofitCallback;
import com.example.dong.xiang.utils.RetrofitUtils;

import java.util.HashMap;

public class QuanModel implements QuanContract.QuanModle {
    @Override
    public void quanModle(HashMap<String, String> params, final RequestCallback callback) {
        String page = params.get("page");
        String count = params.get("count");

        RetrofitUtils.getIntenter().Gets(Api.Quan_user + "?page=" + page + "&count=" + count, new RetrofitCallback() {
            @Override
            public void OnSuccess(String result) {
                callback.ReSuccess(result);
            }

            @Override
            public void OnFailure(String msg) {
                callback.ReFailure(msg);
            }
        });
    }

    @Override
    public void woquanModle(HashMap<String, String> params, final RequestCallback callback) {
        String page = params.get("page");
        String count = params.get("count");

        RetrofitUtils.getIntenter().Gets(Api.woquan_user + "?page=" + page + "&count=" + count, new RetrofitCallback() {
            @Override
            public void OnSuccess(String result) {
                callback.ReSuccess(result);
            }

            @Override
            public void OnFailure(String msg) {
                callback.ReFailure(msg);
            }
        });
    }
}
