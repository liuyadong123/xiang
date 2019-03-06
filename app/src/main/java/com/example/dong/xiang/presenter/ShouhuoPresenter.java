package com.example.dong.xiang.presenter;

import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.XiugaiBean;
import com.example.dong.xiang.contract.ShouhuoContract;
import com.example.dong.xiang.modle.ShouhuoModle;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ShouhuoPresenter extends ShouhuoContract.Contract.ShopPresenter {
    private WeakReference<ShouhuoContract.Contract.IView> reference;
    private ShouhuoModle model;
    private ShouhuoContract.Contract.IView view;
    public ShouhuoPresenter(ShouhuoContract.Contract.IView view){
        model= new ShouhuoModle();
        reference=new WeakReference<>(view);
        this.view=reference.get();
    }
    @Override
    public void Shouhuo(HashMap<String, String> params) {
        model.huoModle(params, new RequestCallback() {

            @Override
            public void ReSuccess(String result) {
                ShouhuoBean shouhuoBean = new Gson().fromJson(result, ShouhuoBean.class);
                view.ShouhuoSuccess(shouhuoBean);
            }

            @Override
            public void ReFailure(String msg) {
                view.Failure(msg);
            }
        });
    }

    @Override
    public void mo(HashMap<String, String> params) {
          model.moModle(params, new RequestCallback() {
              @Override
              public void ReSuccess(String result) {
                  XiugaiBean xiugaiBean = new Gson().fromJson(result, XiugaiBean.class);
                  view.MoSuccess(xiugaiBean);

              }

              @Override
              public void ReFailure(String msg) {
                     view.Failure(msg);
              }
          });
    }
}
