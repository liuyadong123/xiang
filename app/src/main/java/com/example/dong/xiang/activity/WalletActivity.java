package com.example.dong.xiang.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dong.xiang.R;
import com.example.dong.xiang.adapter.QianAdapter;
import com.example.dong.xiang.adapter.QingAdapter;
import com.example.dong.xiang.bean.QianBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.contract.RxContract;
import com.example.dong.xiang.presenter.RxPresent;

import java.util.HashMap;

//钱包
public class WalletActivity extends AppCompatActivity implements RxContract.RxView {
    private RecyclerView qianrv;
    private QianAdapter qianAdapter;
    private RxPresent rxPresent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
    }

    private void initData() {
        rxPresent=new RxPresent(this);
        HashMap<String,String> params =new HashMap<>();
        params.put("page","1");
        params.put("count","1");
        rxPresent.qianshang(params);
    }

    private void initView() {
        qianrv=findViewById(R.id.qianbaorv);
        qianrv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void qianSuccess(QianBean qianBean) {
        QianBean.ResultBean result = qianBean.getResult();
        qianAdapter=new QianAdapter(result,result.getDetailList(),this);
        qianrv.setAdapter(qianAdapter);
    }

    @Override
    public void Failure(String msg) {

    }
}
