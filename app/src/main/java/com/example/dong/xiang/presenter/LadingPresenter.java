package com.example.dong.xiang.presenter;

import com.example.dong.xiang.bean.LadingBean;
import com.example.dong.xiang.contract.LadingContract;
import com.example.dong.xiang.modle.LadingMdole;
import com.example.dong.xiang.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class LadingPresenter extends LadingContract.LadingPresenter {

    @Override
    public void LadingPresenter(HashMap<String, String> params) {
       modle.Lading(params, new RequestCallback() {
           @Override
           public void success(String result) {


           }

           @Override
           public void failure(String msg) {
              View.failure(msg);
           }

           @Override
           public void succeess(LadingBean ladingBean) {
                View.success(ladingBean);
           }


       });

    }
}
