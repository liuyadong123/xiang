package com.example.dong.xiang.contract;

import com.example.dong.xiang.bean.DeleteBean;
import com.example.dong.xiang.bean.JieSuanBean;
import com.example.dong.xiang.bean.QueBean;
import com.example.dong.xiang.bean.UploadBean;
import com.example.dong.xiang.bean.XinxiBean;
import com.example.dong.xiang.bean.ZhifuBean;
import com.example.dong.xiang.utils.RequestCallback;

import java.util.HashMap;

public interface JieSuanContract {
    abstract class SuanPresenter{

        public abstract void suan(HashMap<String, String> params);
        public abstract void xin(HashMap<String, String> params);
        public abstract void delete(HashMap<String, String> params);
        public abstract void zhihfu(HashMap<String, String> params);
        public abstract void quehfu(HashMap<String, String> params);
    }
    interface  SuanModle{
        void  suanModle(HashMap<String, String> params, RequestCallback callback);
        void  xinModle(HashMap<String, String> params, RequestCallback callback);
        void  deleteModle(HashMap<String, String> params, RequestCallback callback);
        void  zhifuModle(HashMap<String, String> params, RequestCallback callback);
        void  queModle(HashMap<String, String> params, RequestCallback callback);
    }
    interface  SuanView{
        void suanSuccess(JieSuanBean jieSuanBean);
        void  xinSuccess(XinxiBean xinxiBean);
        void DeleteSuccess(DeleteBean deleteBean);
        void ZhifuSuccess(ZhifuBean zhifuBean);
        void quefuSuccess(QueBean queBean);
        void Failure(String msg);

    }
}
