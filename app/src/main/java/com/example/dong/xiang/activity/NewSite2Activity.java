package com.example.dong.xiang.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.bean.XiuBean;
import com.example.dong.xiang.bean.XiugaiBean;
import com.example.dong.xiang.bean.zhengBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.contract.ShouhuoContract;
import com.example.dong.xiang.presenter.Presenter;
import com.example.dong.xiang.presenter.ShouhuoPresenter;

import java.util.HashMap;

public class NewSite2Activity extends AppCompatActivity implements Contract.IView{
   private EditText shoujianren,phone,suozaidiqu,youzhengbianba;
   private TextView xinzhen;
    private Presenter presenter;
    private ShouhuoPresenter shouhuoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_site2);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
    }

    private void initData() {
        presenter = new Presenter(this);

    }

    private void initView() {
        shoujianren=findViewById(R.id.shoujianren);
        phone=findViewById(R.id.phone);
        suozaidiqu=findViewById(R.id.suozaidiqu);
        youzhengbianba=findViewById(R.id.youzhengbianma);
        xinzhen=findViewById(R.id.xinzhen);

        xinzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = shoujianren.getText().toString();
                String s1 = phone.getText().toString();
                String s2 = suozaidiqu.getText().toString();
                String s3 = youzhengbianba.getText().toString();
                HashMap<String,String> params =new HashMap<>();
                params.put("realName",s);
                params.put("phone",s1);
                params.put("address",s2);
                params.put("zipCode",s3);
                presenter.Zheng(params);
                startActivity(new Intent(NewSite2Activity.this,SiteActivity.class));
                finish();


            }
        });
    }
    @Override
    public void ZhengSuccess(zhengBean bean) {
        Toast.makeText(NewSite2Activity.this,bean.message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void XiuSuccess(XiuBean bean) {

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

    }
}
