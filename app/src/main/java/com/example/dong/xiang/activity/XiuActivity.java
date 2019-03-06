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
import com.example.dong.xiang.bean.MingBean;
import com.example.dong.xiang.bean.ShangBean;
import com.example.dong.xiang.bean.ZiliaoBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.example.dong.xiang.contract.ZixiaoContract;
import com.example.dong.xiang.presenter.ZiLiaoPresenter;

import java.util.HashMap;

public class XiuActivity extends AppCompatActivity implements ZixiaoContract.Contract.ZiView {
    private EditText ed_name;
    private TextView baocun;
    private ZiLiaoPresenter ziLiaoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ed_name=findViewById(R.id.ed_name);
        baocun=findViewById(R.id.baocun);
        ziLiaoPresenter = new ZiLiaoPresenter(this);
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ed_name.getText().toString();
                HashMap<String,String> params =new HashMap<>();
                params.put("nickName",s);
                ziLiaoPresenter.Ming(params);
                startActivity(new Intent(XiuActivity.this,DatumActivity.class));
                finish();
            }
        });




    }

    @Override
    public void ZiSuccess(ZiliaoBean ziliaoBean) {

    }

    @Override
    public void MingSuccess(MingBean mingBean) {
        Toast.makeText(this,mingBean.message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ZujiSuccess(ZuJiBean zuJiBean) {

    }

    @Override
    public void shangSuccess(ShangBean shangBean) {

    }

    @Override
    public void Failure(String msg) {

    }
}
