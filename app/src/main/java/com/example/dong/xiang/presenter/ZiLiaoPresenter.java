package com.example.dong.xiang.presenter;

import com.example.dong.xiang.bean.ShangBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.bean.MingBean;
import com.example.dong.xiang.bean.ZiliaoBean;
import com.example.dong.xiang.contract.ZixiaoContract;
import com.example.dong.xiang.modle.ZiLiaoModle;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ZiLiaoPresenter extends ZixiaoContract.Contract.ZiPresenter {
    private WeakReference<ZixiaoContract.Contract.ZiView> reference;
    private ZiLiaoModle model;
    private ZixiaoContract.Contract.ZiView view;
    public ZiLiaoPresenter(ZixiaoContract.Contract.ZiView view){
        model= new ZiLiaoModle();
        reference=new WeakReference<>(view);
        this.view=reference.get();
    }
    @Override
    public void ZiLiao(HashMap<String, String> params) {
        model.LiaoModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                ZiliaoBean ziliaoBean = new Gson().fromJson(result, ZiliaoBean.class);
                view.ZiSuccess(ziliaoBean);

            }

            @Override
            public void ReFailure(String msg) {
              view.Failure(msg);
            }
        });

    }

    @Override
    public void Ming(HashMap<String, String> params) {
         model.MingModle(params, new RequestCallback() {
             @Override
             public void ReSuccess(String result) {
                 MingBean mingBean = new Gson().fromJson(result, MingBean.class);
                 view.MingSuccess(mingBean);

             }

             @Override
             public void ReFailure(String msg) {
                 view.Failure(msg);
             }
         });
    }

    @Override
    public void ZuJi(HashMap<String, String> params) {
        model.ZuJiModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                ZuJiBean zuJiBean =new Gson().fromJson(result,ZuJiBean.class);
                view.ZujiSuccess(zuJiBean);
            }

            @Override
            public void ReFailure(String msg) {
              view.Failure(msg);
            }
        });
    }

    @Override
    public void shang(HashMap<String, String> params) {
        model.shangModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                ShangBean shangBean = new Gson().fromJson(result, ShangBean.class);
                view.shangSuccess(shangBean);
            }

            @Override
            public void ReFailure(String msg) {
               view.Failure(msg);
            }
        });
    }
}
