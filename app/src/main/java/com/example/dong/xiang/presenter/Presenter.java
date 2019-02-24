package com.example.dong.xiang.presenter;


import com.example.dong.xiang.Utils.RequestCallback;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.modle.Model;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class Presenter extends Contract.ShopPresenter {
    private WeakReference<Contract.IView> reference;
    private Model model;
    private Contract.IView view;
    public Presenter(Contract.IView view){
        model=new Model();
        reference=new WeakReference<>(view);
        this.view=reference.get();
    }

    @Override
    public void shop(HashMap<String, String> params) {
         model.ShopModle(params, new RequestCallback() {
             @Override
             public void ReSuccess(String result) {
                 ShopBean shopBean = new Gson().fromJson(result, ShopBean.class);
                 view.ShopSuccess(shopBean);

             }

             @Override
             public void ReFailure(String msg) {
                view.Failure(msg);
             }
         });
    }

    @Override
    public void xiang(String id) {
        model.XiangModle(id, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                XiangBean xiangBean = new Gson().fromJson(result, XiangBean.class);
                view.XiangSuccess(xiangBean);

            }

            @Override
            public void ReFailure(String msg) {
                view.Failure(msg);
            }
        });
    }

    @Override
    public void lable(HashMap<String, String> params) {
        model.LableModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                LableBean lableBean =new Gson().fromJson(result,LableBean.class);
                view.LavleSuccess(lableBean);
            }

            @Override
            public void ReFailure(String msg) {
               view.Failure(msg);
            }
        });
    }

    @Override
    public void huo(String userId, String sessionId) {
        model.huoqu(userId,sessionId);
    }

    @Override
    public void jiaGOU(HashMap<String, String> params) {
        model.jiagou(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                JiaGouBean jiaGouBean = new Gson().fromJson(result, JiaGouBean.class);
                view.JIaGouSUccess(jiaGouBean);
            }

            @Override
            public void ReFailure(String msg) {
              view.Failure(msg);
            }
        });
    }

    @Override
    public void ChaGOU(HashMap<String, String> params) {
        model.chagou(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                ChaBean chaBean = new Gson().fromJson(result, ChaBean.class);
                view.ChaGouSUccess(chaBean);

            }

            @Override
            public void ReFailure(String msg) {
               view.Failure(msg);
            }
        });
    }
}
