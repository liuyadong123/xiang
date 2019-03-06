package com.example.dong.xiang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dong.xiang.R;
import com.example.dong.xiang.adapter.QuanAdapter;
import com.example.dong.xiang.bean.QuanBean;
import com.example.dong.xiang.bean.WoQuanBean;
import com.example.dong.xiang.contract.QuanContract;
import com.example.dong.xiang.presenter.QuanPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

public class DisFragment extends Fragment implements QuanContract.QuanView,XRecyclerView.LoadingListener {

    private QuanPresenter quanPresenter;
    private int page=1;
    private String count="5";
    private XRecyclerView quanrv;
    private QuanAdapter quanAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.activity_dis,container,false);
         initView(view);
         return view;
    }

    private void initData() {
        quanPresenter = new QuanPresenter(this);
        HashMap<String,String> params =new HashMap<>();
        params.put("page",page+"");
        params.put("count",count);
        quanPresenter.quan(params);

    }

    private void initView(View view) {
        quanrv=view.findViewById(R.id.quanrrv);
        quanrv.setLoadingMoreEnabled(true);
        quanrv.setLoadingListener(this);
        quanrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        quanAdapter = new QuanAdapter(getActivity());
        quanrv.setAdapter(quanAdapter);
        page=1;
        initData();

    }

    @Override
    public void quanSuccess(QuanBean quanBean) {
       if (page==1){
           quanrv.refreshComplete();
          quanAdapter.setList(quanBean.result);
       }else {
           if (quanAdapter==null){
               quanAdapter.setList(quanBean.result);
           }else {
               quanAdapter.addall(quanBean.result);
           }
           quanrv.loadMoreComplete();
       }
    }

    @Override
    public void woSuccess(WoQuanBean woQuanBean) {

    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void onRefresh() {
        page=1;
        initData();
    }

    @Override
    public void onLoadMore() {
       page++;
       initData();
    }
}
