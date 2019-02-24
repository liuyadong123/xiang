package com.example.dong.xiang.contract;

import com.example.dong.xiang.bean.LadingBean;
import com.example.dong.xiang.modle.LadingMdole;
import com.example.dong.xiang.net.RequestCallback;
import com.example.lib_core.base.mvp.IUserModle;
import com.example.lib_core.base.mvp.IUserView;
import com.example.lib_core.base.mvp.UserPresenter;

import java.util.HashMap;
import java.util.List;

public interface LadingContract {
    abstract  class  LadingPresenter extends UserPresenter<ILadingModle,ILadingView>{
        @Override
        public ILadingModle getModle() {
            return new LadingMdole();
        }

        public  abstract  void LadingPresenter(HashMap<String,String> params);
    }
    interface  ILadingModle extends IUserModle {
        void Lading(HashMap<String,String> params, RequestCallback callback);
    }
    interface ILadingView extends IUserView {
        void success(LadingBean ladingBean);
        void keywordsEmpty(String error);

    }
}
