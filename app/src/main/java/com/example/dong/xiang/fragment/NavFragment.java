package com.example.dong.xiang.fragment;

import android.content.Intent;
import android.media.DrmInitData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.activity.JieSuanActivity;
import com.example.dong.xiang.adapter.ChaAdapter;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.bean.XiuBean;
import com.example.dong.xiang.bean.zhengBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.net.HejiCallback;
import com.example.dong.xiang.presenter.Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NavFragment extends Fragment implements Contract.IView,HejiCallback {


    private XRecyclerView gourv;
    private Presenter presenter;
    private ChaAdapter chaAdapter;
    private CheckBox gouquan;
    private TextView gouheji,goujiesuan;
    private List<ChaBean.Result> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.activity_dav,container,false);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        gourv=view.findViewById(R.id.gourv);
        chaAdapter =new ChaAdapter(getActivity());
        gourv.setAdapter(chaAdapter);
        gourv.setLayoutManager(new LinearLayoutManager(getActivity()));
        gouquan=view.findViewById(R.id.gouquan);
        gouheji=view.findViewById(R.id.gouheji);
        goujiesuan=view.findViewById(R.id.gouqujiesuan);
        goujiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),JieSuanActivity.class));
            }
        });


        gouquan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (ChaBean.Result result : list) {
                        result.checkedbox=true;

                    }
                }else {
                    for (ChaBean.Result result : list) {
                        result.checkedbox=false;
                    }
                }
                chaAdapter.notifyDataSetChanged();
                getPrice();

            }

        });


    }

    private void getPrice() {
        double num=0;
        for (ChaBean.Result result : list) {
            if (result.checkedbox){
                num+=result.price*result.Chanum;
            }
        }
        chaAdapter.notifyDataSetChanged();
        gouheji.setText("合计:￥"+num);
    }

    private void initData() {
        presenter = new Presenter(this);
        presenter.ChaGOU(new HashMap<String, String>());
    }

    @Override
    public void ChaGouSUccess(ChaBean chaBean) {
        list=chaBean.result;
        for (ChaBean.Result result : list) {
            result.Chanum=1;
        }
        chaAdapter.setHejiCallback(this);
        Toast.makeText(getActivity(),chaBean.message,Toast.LENGTH_SHORT).show();
        chaAdapter.setList(chaBean.result);
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
    public void ZhengSuccess(zhengBean bean) {

    }

    @Override
    public void XiuSuccess(XiuBean bean) {

    }


    @Override
    public void Failure(String msg) {

    }

    @Override
    public void JIaGouSUccess(JiaGouBean jiaGouBean) {

    }

    @Override
    public void PriceCallback() {
        getPrice();
    }
}
