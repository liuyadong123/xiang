package com.example.dong.xiang.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dong.xiang.R;
import com.example.dong.xiang.adapter.QuanAdapter;
import com.example.dong.xiang.adapter.WoQuanAdapter;
import com.example.dong.xiang.bean.QuanBean;
import com.example.dong.xiang.bean.WoQuanBean;
import com.example.dong.xiang.contract.QuanContract;
import com.example.dong.xiang.presenter.QuanPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//圈子
public class CircleActivity extends AppCompatActivity implements QuanContract.QuanView,
        XRecyclerView.LoadingListener,WoQuanAdapter.QuanShanchuCallback {
    private QuanPresenter quanPresenter;
    private XRecyclerView  quanrv;
    private int page=1;
    private String count="5";
    private WoQuanAdapter quanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
    }

    private void initView() {
        quanrv=findViewById(R.id.quanrv);
        quanrv.setLoadingMoreEnabled(true);
        quanrv.setLoadingListener(this);
        quanrv.setLayoutManager(new LinearLayoutManager(this));
        quanAdapter = new WoQuanAdapter(this);
        quanrv.setAdapter(quanAdapter);
        quanAdapter.setQuanShanchuCallback(this);
        initData();
    }

    private void initData() {
        quanPresenter = new QuanPresenter(this);
        HashMap<String,String> params =new HashMap<>();
        params.put("page",page+"");
        params.put("count",count);
        quanPresenter.woquan(params);
    }

    @Override
    public void quanSuccess(QuanBean quanBean) {

    }

    @Override
    public void woSuccess(WoQuanBean woQuanBean) {
        if (page==1){
            quanrv.refreshComplete();
            quanAdapter.setList(woQuanBean.getResult());
        }else {
            if (quanAdapter==null){
                quanAdapter.setList(woQuanBean.getResult());
                quanAdapter.setQuanShanchuCallback(this);
            }else {
                quanAdapter.addall(woQuanBean.getResult());
                quanAdapter.setQuanShanchuCallback(this);
            }
            quanrv.loadMoreComplete();
        }
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

    @Override
    public void shancallback(String id) {

    }
}
