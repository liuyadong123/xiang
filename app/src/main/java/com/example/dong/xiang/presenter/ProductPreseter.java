package com.example.dong.xiang.presenter;

import com.example.dong.xiang.bean.LadingBean;
import com.example.dong.xiang.bean.ProductBean;
import com.example.dong.xiang.bean.ShouyeBean;
import com.example.dong.xiang.contract.ProductContract;
import com.example.dong.xiang.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class ProductPreseter  extends ProductContract.ProductPreseter {
    @Override
    public void product(HashMap<String, String> params) {
         modle.product(params, new RequestCallback() {
             @Override
             public void success(String result) {
                 ProductBean productBean = new Gson().fromJson(result, ProductBean.class);
                 List<ProductBean.ResultBean> result1 = productBean.getResult();
                 view.success(result1);


             }

             @Override
             public void failure(String msg) {
               view.failure(msg);
             }

             @Override
             public void succeess(LadingBean ladingBean) {

             }


         });
    }

    @Override
    public void shouye(HashMap<String, String> params) {
        modle.shouye(params, new RequestCallback() {
            @Override
            public void success(String result) {
                ShouyeBean shouyeBean = new Gson().fromJson(result, ShouyeBean.class);
                view.successs(shouyeBean);


            }


            @Override
            public void failure(String msg) {

            }

            @Override
            public void succeess(LadingBean ladingBean) {

            }
        });
    }

    @Override
    public void tiao(HashMap<String, String> params) {
         modle.tiao(params, new RequestCallback() {
             @Override
             public void success(String result) {
                 view.succes(result);
             }

             @Override
             public void failure(String msg) {
                 view.failure(msg);
             }

             @Override
             public void succeess(LadingBean ladingBean) {

             }
         });
    }
}
