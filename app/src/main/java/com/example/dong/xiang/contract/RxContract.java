package com.example.dong.xiang.contract;

import com.example.dong.xiang.bean.QianBean;
import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.UploadBean;
import com.example.dong.xiang.utils.RequestCallback;

import java.util.HashMap;

public interface RxContract {

        abstract class RxPresenter{

            public abstract void qianshang(HashMap<String, String> params);
        }
        interface  RxModle{
            void  qianModle(HashMap<String, String> params, RequestCallback callback);
        }
        interface  RxView{
            void qianSuccess(QianBean qianBean);

            void Failure(String msg);

        }
    }

