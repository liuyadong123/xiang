package com.example.dong.xiang.contract;

import com.example.dong.xiang.utils.RequestCallback;

import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.XiugaiBean;

import java.util.HashMap;

public interface ShouhuoContract {

    public interface Contract {
        abstract class ShopPresenter{

            public abstract void Shouhuo(HashMap<String, String> params);
            public abstract void mo(HashMap<String, String> params);
        }
        interface  IModle{
            void  huoModle(HashMap<String, String> params, RequestCallback callback);
            void  moModle(HashMap<String, String> params, RequestCallback callback);
        }
        interface  IView{
            void ShouhuoSuccess(ShouhuoBean shouhuoBean);
            void  MoSuccess(XiugaiBean xiugaiBean);
            void Failure(String msg);

        }
    }

}
