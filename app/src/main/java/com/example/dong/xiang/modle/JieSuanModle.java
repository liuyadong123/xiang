package com.example.dong.xiang.modle;

import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.contract.JieSuanContract;
import com.example.dong.xiang.contract.RxContract;
import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.utils.RetrofitCallback;
import com.example.dong.xiang.utils.RetrofitUtils;

import java.util.HashMap;

public class JieSuanModle implements JieSuanContract.SuanModle {
    @Override
    public void suanModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().Posts(Api.JieSuan_user, params, new RetrofitCallback() {
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
    public void xinModle(HashMap<String, String> params, final RequestCallback callback) {
        String status= params.get("status");
        String page = params.get("page");
        String count = params.get("count");

        RetrofitUtils.getIntenter().Gets(Api.Xinxi_user+ "?status=" + status + "&page=" + page + "&count=" + count, new RetrofitCallback() {
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
    public void deleteModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().Delete(Api.Delete_user, params, new RetrofitCallback() {
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
    public void zhifuModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().Posts(Api.Zhifu_user, params, new RetrofitCallback() {
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
    public void queModle(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().NiceName(Api.que_user, params, new RetrofitCallback() {
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
