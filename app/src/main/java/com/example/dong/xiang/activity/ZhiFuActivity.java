package com.example.dong.xiang.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.DeleteBean;
import com.example.dong.xiang.bean.JieSuanBean;
import com.example.dong.xiang.bean.QueBean;
import com.example.dong.xiang.bean.XinxiBean;
import com.example.dong.xiang.bean.ZhifuBean;
import com.example.dong.xiang.contract.JieSuanContract;
import com.example.dong.xiang.presenter.JieSuanPresenter;

import java.util.HashMap;

public class ZhiFuActivity extends AppCompatActivity implements JieSuanContract.SuanView {
    private TextView zhifu;
    private CheckBox zhifuck;
    private JieSuanPresenter jieSuanPresenter;
    private String id;
    private String price;
    private String pppp="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
    }

    private void initData() {
        jieSuanPresenter = new JieSuanPresenter(this);

    }

    private void initView() {
        zhifuck=findViewById(R.id.moneyck);
        zhifu=findViewById(R.id.zhufu);
        Intent intent = getIntent();
        id= intent.getStringExtra("id");
        price = intent.getStringExtra("price");
        zhifuck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    zhifu.setText("余额支付"+ price +"元");
                }else {
                    zhifu.setText("余额支付"+ pppp+"元");
                }
            }
        });

        zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> params =new HashMap<>();
                params.put("orderId", id);
                params.put("payType","1");
                jieSuanPresenter.zhihfu(params);
                finish();
            }
        });




    }
    @Override
    public void ZhifuSuccess(ZhifuBean zhifuBean) {
        Toast.makeText(this,zhifuBean.message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void quefuSuccess(QueBean queBean) {

    }

    @Override
    public void suanSuccess(JieSuanBean jieSuanBean) {

    }

    @Override
    public void xinSuccess(XinxiBean xinxiBean) {

    }

    @Override
    public void DeleteSuccess(DeleteBean deleteBean) {

    }



    @Override
    public void Failure(String msg) {

    }
}
