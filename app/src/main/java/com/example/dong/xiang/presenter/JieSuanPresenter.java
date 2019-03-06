package com.example.dong.xiang.presenter;

import com.example.dong.xiang.bean.DeleteBean;
import com.example.dong.xiang.bean.JieSuanBean;
import com.example.dong.xiang.bean.QueBean;
import com.example.dong.xiang.bean.XinxiBean;
import com.example.dong.xiang.bean.ZhifuBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.contract.JieSuanContract;
import com.example.dong.xiang.modle.JieSuanModle;
import com.example.dong.xiang.modle.Model;
import com.example.dong.xiang.utils.RequestCallback;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class JieSuanPresenter extends JieSuanContract.SuanPresenter {
    private WeakReference<JieSuanContract.SuanView> reference;
    private JieSuanModle model;
    private JieSuanContract.SuanView view;
    public JieSuanPresenter(JieSuanContract.SuanView view){
        model=new JieSuanModle();
        reference=new WeakReference<>(view);
        this.view=reference.get();
    }
    @Override
    public void suan(HashMap<String, String> params) {
        model.suanModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                JieSuanBean jieSuanBean = new Gson().fromJson(result, JieSuanBean.class);
                view.suanSuccess(jieSuanBean);
            }

            @Override
            public void ReFailure(String msg) {
                  view.Failure(msg);
            }
        });

    }

    @Override
    public void xin(HashMap<String, String> params) {
        model.xinModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                XinxiBean xinxiBean = new Gson().fromJson(result, XinxiBean.class);
                view.xinSuccess(xinxiBean);

            }

            @Override
            public void ReFailure(String msg) {
            view.Failure(msg);
            }
        });
    }

    @Override
    public void delete(HashMap<String, String> params) {
        model.deleteModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                DeleteBean deleteBean = new Gson().fromJson(result, DeleteBean.class);
                view.DeleteSuccess(deleteBean);

            }

            @Override
            public void ReFailure(String msg) {
                view.Failure(msg);

            }
        });

    }

    @Override
    public void zhihfu(HashMap<String, String> params) {
        model.zhifuModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                ZhifuBean zhifuBean = new Gson().fromJson(result, ZhifuBean.class);
                view.ZhifuSuccess(zhifuBean );

            }

            @Override
            public void ReFailure(String msg) {
              view.Failure(msg);
            }
        });
    }

    @Override
    public void quehfu(HashMap<String, String> params) {
         model.queModle(params, new RequestCallback() {
             @Override
             public void ReSuccess(String result) {
                 QueBean queBean = new Gson().fromJson(result, QueBean.class);
                 view.quefuSuccess(queBean);

             }

             @Override
             public void ReFailure(String msg) {
                   view.Failure(msg);
             }
         });
    }
}
