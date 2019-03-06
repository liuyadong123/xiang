package com.example.dong.xiang.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.adapter.ShouhuoAdapter;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.bean.XiugaiBean;
import com.example.dong.xiang.bean.zhengBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.contract.ShouhuoContract;
import com.example.dong.xiang.presenter.Presenter;
import com.example.dong.xiang.presenter.ShouhuoPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

//地址
public class SiteActivity extends AppCompatActivity implements ShouhuoContract.Contract.IView,ShouhuoAdapter.ShouhuoCallback {
    private TextView xinzhen,wancheng;
    private ShouhuoAdapter shouhuoAdapter;
    private RecyclerView shourv;
    private List<ShouhuoBean.Result> list;
    private ShouhuoPresenter presenter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();

    }

    private void initData() {
        presenter1 = new ShouhuoPresenter(this);
        presenter1.Shouhuo(new HashMap<String, String>());
    }

    private void initView() {
        xinzhen=findViewById(R.id.xinzhen);
        xinzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SiteActivity.this,NewSite2Activity.class));
                finish();
            }
        });
        shourv=findViewById(R.id.shourv);
        shourv.setLayoutManager(new LinearLayoutManager(this));
        wancheng=findViewById(R.id.wancheng);
        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void ShouhuoSuccess(ShouhuoBean shouhuoBean) {

         list=shouhuoBean.result;
        shouhuoAdapter=new ShouhuoAdapter(shouhuoBean.result,this);
        shourv.setAdapter(shouhuoAdapter);
        shouhuoAdapter.setCallback(this);

    }

    @Override
    public void MoSuccess(XiugaiBean xiugaiBean) {
     Toast.makeText(SiteActivity.this,xiugaiBean.message,Toast.LENGTH_SHORT).show();
    }


    //修改
    @Override
    public void XiuGaiCallback(String id) {
        Intent intent = new Intent(SiteActivity.this, XiuGaiActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        finish();

    }
    //默认
    @Override
    public void MoRenCallback(String id) {
        HashMap<String,String> params =new HashMap<>();
        params.put("id",id);
      presenter1.mo(params);
    }



    @Override
    public void Failure(String msg) {

    }




}
