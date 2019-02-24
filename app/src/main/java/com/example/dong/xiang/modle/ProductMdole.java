package com.example.dong.xiang.modle;

import com.example.dong.xiang.api.ProductApi;
import com.example.dong.xiang.contract.ProductContract;
import com.example.dong.xiang.net.RequestCallback;
import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;

import java.util.HashMap;

public class ProductMdole implements ProductContract.IProductModle {
    @Override
    public void product(HashMap<String, String> params, final RequestCallback callback) {
        OkhttpUtils.getInstance().doGet(ProductApi.Banner_Api, params, new OkhttpCallback() {
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

    @Override
    public void shouye(HashMap<String, String> params, final RequestCallback callback) {
        OkhttpUtils.getInstance().doGet(ProductApi.Product_Api, params, new OkhttpCallback() {
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

    @Override
    public void tiao(HashMap<String, String> params, final RequestCallback callback) {
        OkhttpUtils.getInstance().doGet(ProductApi.Tiao_Api, params, new OkhttpCallback() {
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
