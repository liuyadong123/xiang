package com.example.dong.xiang.presenter;

import com.example.dong.xiang.bean.QuanBean;
import com.example.dong.xiang.bean.WoQuanBean;
import com.example.dong.xiang.contract.QuanContract;
import com.example.dong.xiang.modle.QuanModel;
import com.example.dong.xiang.utils.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class QuanPresenter extends QuanContract.QuanPresenter {
    private QuanModel model;
    private QuanContract.QuanView view;
    public QuanPresenter(QuanContract.QuanView view){
        model=new QuanModel();
        this.view=view;
    }
    @Override
    public void quan(HashMap<String, String> params) {
        model.quanModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                QuanBean quanBean = new Gson().fromJson(result, QuanBean.class);
                view.quanSuccess(quanBean);

            }

            @Override
            public void ReFailure(String msg) {
               view.Failure(msg);
            }
        });
    }

    @Override
    public void woquan(HashMap<String, String> params) {
        model.woquanModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                WoQuanBean woQuanBean = new Gson().fromJson(result, WoQuanBean.class);
                view.woSuccess(woQuanBean);

            }

            @Override
            public void ReFailure(String msg) {
               view.Failure(msg);
            }
        });
    }
}
