package com.example.dong.xiang.presenter;

import com.example.dong.xiang.bean.LadingBean;
import com.example.dong.xiang.contract.RegisterContract;
import com.example.dong.xiang.modle.RegisterModle;
import com.example.dong.xiang.net.RequestCallback;

import java.util.HashMap;

public class RegisterPresenter extends RegisterContract.RegisterPresenter {
     private RegisterModle registerModle;
     private RegisterContract.IRegisterView iRegisterView;
     public  RegisterPresenter(RegisterContract.IRegisterView iRegisterView){
         registerModle=new RegisterModle();
         this.iRegisterView=iRegisterView;

     }
    @Override
    public void LadingPresenters(HashMap<String, String> params) {
          registerModle.Register(params, new RequestCallback() {
              @Override
              public void success(String result) {
                  iRegisterView.success(result);
              }

              @Override
              public void failure(String msg) {
              iRegisterView.failure(msg);
              }

              @Override
              public void succeess(LadingBean ladingBean) {

              }
          });

    }
}
