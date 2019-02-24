package com.example.dong.xiang.contract;

import com.example.dong.xiang.modle.LadingMdole;
import com.example.dong.xiang.modle.RegisterModle;
import com.example.dong.xiang.net.RequestCallback;
import com.example.lib_core.base.mvp.IUserModle;
import com.example.lib_core.base.mvp.IUserView;
import com.example.lib_core.base.mvp.UserPresenter;

import java.util.HashMap;

public interface RegisterContract {
    abstract  class  RegisterPresenter extends UserPresenter<IRegisterModle,IRegisterView>{
        @Override
        public IRegisterModle getModle() {
            return new RegisterModle();
        }

        public  abstract  void LadingPresenters(HashMap<String,String> params);
    }
    interface  IRegisterModle extends IUserModle {
        void Register(HashMap<String, String> params, RequestCallback callback);
    }
    interface IRegisterView extends IUserView {
        void success(String result);
        void keywordsEmpty(String error);

    }
}
