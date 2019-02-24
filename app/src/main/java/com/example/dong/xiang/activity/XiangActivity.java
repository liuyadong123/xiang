package com.example.dong.xiang.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;

import com.example.dong.xiang.adapter.QingAdapter;
import com.example.dong.xiang.adapter.ViewPagerAdapter;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;


public  class XiangActivity extends AppCompatActivity implements QingAdapter.TianjiaCallback ,Contract.IView {
    private RecyclerView qingrv;
    private XiangBean.ResultBean result;
    private QingAdapter qingAdapter;
    private String count="3";
    private String commodityId1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        qingrv=findViewById(R.id.qing);
        qingAdapter = new QingAdapter(result,this);
        qingrv.setAdapter(qingAdapter);
        qingrv.setLayoutManager(new LinearLayoutManager(this));
        qingAdapter.setTianjiaCallback(this);
    }
    //EventBus穿过来的值
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void shopwewe(XiangBean xiangBean){
        result = xiangBean.result;
        commodityId1 = result.commodityId;



    }
  //加入购物车
    @Override
    public void goucallback() {
        Presenter presenter =new Presenter(this);
        HashMap<String,String> params=new HashMap<>();
        //[{"commodityId":26,"count":3}]

      String  reQuesetBody="[{" +
                "commodityId:"+ commodityId1 +
                ",count:"+ count+"}]";
      params.put("data",reQuesetBody);

      presenter.jiaGOU(params);

    }
  //购买订单
    @Override
    public void maicallback() {
        Toast.makeText(this,"123",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShopSuccess(ShopBean shopBean) {

    }

    @Override
    public void XiangSuccess(XiangBean xiangBean) {

    }

    @Override
    public void LavleSuccess(LableBean lableBean) {

    }

    @Override
    public void ChaGouSUccess(ChaBean chaBean) {

    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void JIaGouSUccess(JiaGouBean jiaGouBean) {
        Toast.makeText(this,jiaGouBean.message,Toast.LENGTH_SHORT).show();


    }
}
