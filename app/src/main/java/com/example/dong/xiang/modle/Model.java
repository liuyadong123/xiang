package com.example.dong.xiang.modle;

import com.example.dong.xiang.Utils.RequestCallback;
import com.example.dong.xiang.Utils.RetrofitCallback;
import com.example.dong.xiang.Utils.RetrofitUtils;
import com.example.dong.xiang.api.Api;
import com.example.dong.xiang.contract.Contract;

import java.util.HashMap;

public class Model implements Contract.IModle {

    @Override
    public void ShopModle(HashMap<String, String> params, final RequestCallback callback) {
        String keyword = params.get("keyword");
        String page = params.get("page");
        String count = params.get("count");




        RetrofitUtils.getIntenter().Gets(Api.Shop_User + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RetrofitCallback() {
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
    public void XiangModle(String id, final RequestCallback callback) {
        RetrofitUtils.getIntenter().Gets(Api.Xing_User + "?commodityId=" + id, new RetrofitCallback() {
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
    public void LableModle(HashMap<String, String> params, final RequestCallback callback) {
        String page = params.get("page");
        String count = params.get("count");
        String labelId = params.get("labelId");
        RetrofitUtils.getIntenter().Gets(Api.Label_User + "?labelId=" + labelId + "&page=" + page + "&count=" + count, new RetrofitCallback() {
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
    public void huoqu(String userId, String sessionId) {
        RetrofitUtils.getIntenter().putSp("userId",userId);
        RetrofitUtils.getIntenter().putSp("sessionId",sessionId);
    }

    @Override
    public void jiagou(HashMap<String, String> params, final RequestCallback callback) {
        String data = params.get("data");


        RetrofitUtils.getIntenter().NiceName(Api.JIAGOU_User ,params, new RetrofitCallback() {
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
    public void chagou(HashMap<String, String> params, final RequestCallback callback) {
         RetrofitUtils.getIntenter().Gets(Api.Cha_user, new RetrofitCallback() {
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
