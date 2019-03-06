package com.example.dong.xiang.presenter;

import com.example.dong.xiang.bean.QianBean;
import com.example.dong.xiang.contract.RegisterContract;
import com.example.dong.xiang.contract.RxContract;
import com.example.dong.xiang.modle.RegisterModle;
import com.example.dong.xiang.modle.RxModle;
import com.example.dong.xiang.utils.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class RxPresent extends RxContract.RxPresenter {
    private RxModle modle;
    private RxContract.RxView view;
    public RxPresent(RxContract.RxView view){
        modle=new RxModle();
        this.view=view;

    }
    @Override
    public void qianshang(HashMap<String, String> params) {
        modle.qianModle(params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                QianBean qianBean = new Gson().fromJson(result, QianBean.class);
                view.qianSuccess(qianBean);

            }

            @Override
            public void ReFailure(String msg) {
                view.Failure(msg);
            }
        });

    }
}
