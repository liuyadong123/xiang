package com.example.dong.xiang.modle;

import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.utils.RetrofitCallback;
import com.example.dong.xiang.utils.RetrofitUtils;
import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.contract.ShouhuoContract;

import java.util.HashMap;

public class ShouhuoModle implements ShouhuoContract.Contract.IModle {
    @Override
    public void huoModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().Gets(Api.Shouhuo_user, new RetrofitCallback() {
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
    public void moModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().Posts(Api.Mo_user, params, new RetrofitCallback() {
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
