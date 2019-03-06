package com.example.dong.xiang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.activity.FaBU2Activity;
import com.example.dong.xiang.activity.ZhiFuActivity;
import com.example.dong.xiang.adapter.DingAdapter;
import com.example.dong.xiang.adapter.HuoAdapter;
import com.example.dong.xiang.adapter.PingAdapter;
import com.example.dong.xiang.bean.DeleteBean;
import com.example.dong.xiang.bean.JieSuanBean;
import com.example.dong.xiang.bean.QueBean;
import com.example.dong.xiang.bean.XinxiBean;
import com.example.dong.xiang.bean.ZhifuBean;
import com.example.dong.xiang.contract.JieSuanContract;
import com.example.dong.xiang.presenter.JieSuanPresenter;

import java.util.HashMap;

public class WenFragment extends Fragment implements JieSuanContract.SuanView,DingAdapter.DeleteCallback,HuoAdapter.DeleteCallback,PingAdapter.DeleteCallback {

    private JieSuanPresenter jieSuanPresenter;
    private ImageView dingdan,shoukuang,shouhuo,pingjia,wancheng;
    private RecyclerView dingrv,furv,shourv,pingjiarv;
    private DingAdapter dingAdapter;
    private HuoAdapter huoAdapter;
    private PingAdapter pingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.activity_wen,container,false);
         initView(view);
         initData();
        return view;
    }

    private void initData() {
        jieSuanPresenter = new JieSuanPresenter(this);
        dingrv.setVisibility(View.VISIBLE);
        furv.setVisibility(View.GONE);
        shourv.setVisibility(View.GONE);
        pingjiarv.setVisibility(View.GONE);
        HashMap<String,String> params =new HashMap<>();
        params.put("status","0");
        params.put("page","1");
        params.put("count","5");
        jieSuanPresenter.xin(params);


    }

    private void initView(View view) {
        dingdan=view.findViewById(R.id.dingdan);
        shoukuang=view.findViewById(R.id.fukuang);
        shouhuo=view.findViewById(R.id.shouhuo);
        pingjia=view.findViewById(R.id.pingjia);
        wancheng=view.findViewById(R.id.wancheng);
        dingrv=view.findViewById(R.id.dingrv);
        furv=view.findViewById(R.id.furv);
        shourv=view.findViewById(R.id.shourv);
        pingjiarv=view.findViewById(R.id.pingjiarv);
        furv.setLayoutManager(new LinearLayoutManager(getActivity()));
        dingrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shourv.setLayoutManager(new LinearLayoutManager(getActivity()));
        pingjiarv.setLayoutManager(new LinearLayoutManager(getActivity()));
     //全部订单
        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dingrv.setVisibility(View.VISIBLE);
                furv.setVisibility(View.GONE);
                shourv.setVisibility(View.GONE);
                pingjiarv.setVisibility(View.GONE);
                HashMap<String,String> params =new HashMap<>();
                params.put("status","0");
                params.put("page","1");
                params.put("count","5");
               jieSuanPresenter.xin(params);

            }
        });
        //代付款
        shoukuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                furv.setVisibility(View.VISIBLE);
                dingrv.setVisibility(View.GONE);
                shourv.setVisibility(View.GONE);
                pingjiarv.setVisibility(View.GONE);
                HashMap<String,String> params =new HashMap<>();
                params.put("status","1");
                params.put("page","1");
                params.put("count","5");
                jieSuanPresenter.xin(params);

            }
        });
        //待收货
        shouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shourv.setVisibility(View.VISIBLE);
                dingrv.setVisibility(View.GONE);
                furv.setVisibility(View.GONE);
                pingjiarv.setVisibility(View.GONE);
                HashMap<String,String> params =new HashMap<>();
                params.put("status","2");
                params.put("page","1");
                params.put("count","5");
                jieSuanPresenter.xin(params);

            }
        });
        //评价
        pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pingjiarv.setVisibility(View.VISIBLE);
                shourv.setVisibility(View.GONE);
                dingrv.setVisibility(View.GONE);
                furv.setVisibility(View.GONE);
                HashMap<String,String> params =new HashMap<>();
                params.put("status","3");
                params.put("page","1");
                params.put("count","5");
                jieSuanPresenter.xin(params);
            }
        });
    }



    @Override
    public void xinSuccess(XinxiBean xinxiBean) {
        dingAdapter = new DingAdapter(xinxiBean.getOrderList(),getActivity());
        huoAdapter=new HuoAdapter(xinxiBean.getOrderList(),getActivity());
        pingAdapter = new PingAdapter(xinxiBean.getOrderList(),getActivity());
        shourv.setAdapter(huoAdapter);
        dingrv.setAdapter(dingAdapter);
        furv.setAdapter(dingAdapter);
        pingjiarv.setAdapter(pingAdapter);
        dingAdapter.setCallback(this);
        huoAdapter.setCallback(this);
        pingAdapter.setCallback(this);
        dingAdapter.notifyDataSetChanged();
        pingAdapter.notifyDataSetChanged();
        huoAdapter.notifyDataSetChanged();
    }

    @Override
    public void DeleteSuccess(DeleteBean deleteBean) {
        Toast.makeText(getActivity(),deleteBean.message,Toast.LENGTH_SHORT).show();
        dingAdapter.notifyDataSetChanged();
}

    @Override
    public void ZhifuSuccess(ZhifuBean zhifuBean) {

    }

    @Override
    public void quefuSuccess(QueBean queBean) {
        Toast.makeText(getActivity(),queBean.message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failure(String msg) {

    }
    @Override
    public void suanSuccess(JieSuanBean jieSuanBean) {

    }
      //取消订单
    @Override
    public void delete(String id) {
          HashMap<String,String> param=new HashMap<>();
          param.put("orderId",id);
          jieSuanPresenter.delete(param);

    }
  //评价删除
    @Override
    public void deletes(String id) {

    }
 //去评价
    @Override
    public void picallback() {
      startActivity(new Intent(getActivity(),FaBU2Activity.class));
    }
    //支付页面
    @Override
    public void zhifu(String id,String price) {
        Intent intent = new Intent(getActivity(), ZhiFuActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("price",price);
        startActivity(intent);

    }

    @Override
    public void zhifu(String id) {
        HashMap<String,String> param=new HashMap<>();
        param.put("orderId",id);
        jieSuanPresenter.quehfu(param);
    }
}
