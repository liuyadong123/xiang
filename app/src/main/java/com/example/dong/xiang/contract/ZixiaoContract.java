package com.example.dong.xiang.contract;

import com.example.dong.xiang.bean.ShangBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.example.dong.xiang.utils.RequestCallback;
import com.example.dong.xiang.bean.MingBean;
import com.example.dong.xiang.bean.ZiliaoBean;

import java.util.HashMap;

public interface ZixiaoContract {
    interface Contract {
        abstract class ZiPresenter{

            public abstract void ZiLiao(HashMap<String, String> params);
            public abstract void Ming(HashMap<String, String> params);
            public abstract void ZuJi(HashMap<String, String> params);
            public abstract void shang(HashMap<String, String> params);
        }
        interface  ZiModle{
            void LiaoModle(HashMap<String, String> params, RequestCallback callback);
            void MingModle(HashMap<String, String> params, RequestCallback callback);
            void ZuJiModle(HashMap<String, String> params, RequestCallback callback);
            void shangModle(HashMap<String, String> params, RequestCallback callback);
        }
        interface  ZiView{
            void ZiSuccess(ZiliaoBean ziliaoBean);
            void MingSuccess(MingBean mingBean);
            void  ZujiSuccess(ZuJiBean zuJiBean);
            void shangSuccess(ShangBean shangBean);
            void Failure(String msg);

        }
    }
}
