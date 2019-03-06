package com.example.dong.xiang.modle;

import android.view.View;

import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.utils.RetrofitCallback;
import com.example.dong.xiang.utils.RetrofitUtils;
import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.contract.ZixiaoContract;

import java.util.HashMap;

public class ZiLiaoModle implements ZixiaoContract.Contract.ZiModle {
    @Override
    public void LiaoModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().Gets(Api.ZiLiao_user, new RetrofitCallback() {
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
    public void MingModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().NiceName(Api.ming_user, params, new RetrofitCallback() {
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
    public void ZuJiModle(HashMap<String, String> params, final RequestCallback callback) {
        String page = params.get("page");
        String count = params.get("count");
        RetrofitUtils.getIntenter().Gets(Api.ZuJi_user+"?page="+page+"&count="+count, new RetrofitCallback() {
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
    public void shangModle(HashMap<String, String> params, final RequestCallback callback) {
         RetrofitUtils.getIntenter().Posts(Api.shang_user, params, new RetrofitCallback() {
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
