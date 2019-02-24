package com.example.dong.xiang.contract;

import com.example.dong.xiang.Utils.RequestCallback;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.XiangBean;

import java.util.HashMap;

public interface Contract {
     abstract class ShopPresenter{
         public  abstract  void shop(HashMap<String,String> params);
         public abstract  void xiang(String id);
         public  abstract  void lable(HashMap<String,String> params);
         public  abstract  void huo(String userId,String sessionId);
         public abstract void jiaGOU(HashMap<String, String> params);
         public abstract void ChaGOU(HashMap<String, String> params);
    }
    interface  IModle{
         void ShopModle(HashMap<String, String> params, RequestCallback callback);
         void XiangModle(String id, RequestCallback callback);
         void  LableModle(HashMap<String, String> params, RequestCallback callback);
          void huoqu(String userId,String sessionId);
          void  jiagou(HashMap<String, String> params, RequestCallback callback);
        void  chagou(HashMap<String, String> params, RequestCallback callback);
    }
    interface  IView{
         void ShopSuccess(ShopBean shopBean);
         void XiangSuccess(XiangBean xiangBean);
         void LavleSuccess(LableBean lableBean);
         void  ChaGouSUccess(ChaBean chaBean);
         void Failure(String msg);
         void JIaGouSUccess(JiaGouBean jiaGouBean);
    }
}
