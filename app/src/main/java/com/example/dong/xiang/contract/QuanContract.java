package com.example.dong.xiang.contract;

import com.example.dong.xiang.bean.QianBean;
import com.example.dong.xiang.bean.QuanBean;
import com.example.dong.xiang.bean.WoQuanBean;
import com.example.dong.xiang.utils.RequestCallback;

import java.util.HashMap;

public interface QuanContract {
    abstract class QuanPresenter{

        public abstract void quan(HashMap<String, String> params);
        public abstract void woquan(HashMap<String, String> params);
    }
    interface  QuanModle{
        void  quanModle(HashMap<String, String> params, RequestCallback callback);
        void  woquanModle(HashMap<String, String> params, RequestCallback callback);
    }
    interface  QuanView{
        void quanSuccess(QuanBean quanBean);
        void woSuccess(WoQuanBean woQuanBean);
        void Failure(String msg);

    }
}
