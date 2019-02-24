package com.example.dong.xiang.contract;

import com.example.dong.xiang.bean.ProductBean;
import com.example.dong.xiang.bean.ShouyeBean;
import com.example.dong.xiang.modle.ProductMdole;
import com.example.dong.xiang.net.RequestCallback;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModle;
import com.example.lib_core.base.mvp.IBaseView;

import java.util.HashMap;
import java.util.List;

public interface ProductContract {
       abstract class  ProductPreseter extends BasePresenter<IProductModle,IProductView>{
           @Override
           public IProductModle getModle() {
               return new ProductMdole();
           }
           public  abstract void product(HashMap<String,String> params);
           public abstract  void shouye(HashMap<String,String> params);
           public abstract  void tiao(HashMap<String,String> params);
       }
       interface  IProductModle extends IBaseModle {
           void product(HashMap<String,String> params, RequestCallback callback);
           void shouye(HashMap<String,String> params, RequestCallback callback);
           void tiao(HashMap<String,String> params, RequestCallback callback);
       }
       interface  IProductView extends IBaseView {
           void success(List<ProductBean.ResultBean> list);
           void failure(String msg);
           void succes(String result);
           void successs(ShouyeBean shouyeBean);

       }

}
