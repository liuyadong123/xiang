package com.example.dong.xiang.modle;

import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.api.UserApi;
import com.example.dong.xiang.contract.RxContract;
import com.example.dong.xiang.rxutils.RxRetrofitUtiles;
import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.utils.RetrofitCallback;
import com.example.dong.xiang.utils.RetrofitUtils;

import java.util.HashMap;

public class RxModle implements RxContract.RxModle {
    @Override
    public void qianModle(HashMap<String, String> params, final RequestCallback callback) {
        String page = params.get("page");
        String count = params.get("count");
        RetrofitUtils.getIntenter().Gets(Api.qian_user + "?page=" + page + "&count=" + count, new RetrofitCallback() {
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
