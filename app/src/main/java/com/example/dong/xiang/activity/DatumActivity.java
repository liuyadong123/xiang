package com.example.dong.xiang.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.MingBean;
import com.example.dong.xiang.bean.ShangBean;
import com.example.dong.xiang.bean.ZiliaoBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.example.dong.xiang.contract.ZixiaoContract;
import com.example.dong.xiang.presenter.ZiLiaoPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

//资料
public class DatumActivity extends AppCompatActivity implements ZixiaoContract.Contract.ZiView {
  private SimpleDraweeView geimage;
  private TextView gename,gepwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datum);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
         initView();
         initData();
    }

     private void initData() {
         ZiLiaoPresenter ziLiaoPresenter =new ZiLiaoPresenter(this);
         ziLiaoPresenter.ZiLiao(new HashMap<String, String>());
     }

     private void initView() {
        geimage=findViewById(R.id.geimage);
        gename=findViewById(R.id.gename);
        gepwd=findViewById(R.id.gepwd);
        gename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatumActivity.this,XiuActivity.class));
                finish();

            }
        });
     }

    @Override
    public void ZiSuccess(ZiliaoBean ziliaoBean) {
        Uri uri=Uri.parse(ziliaoBean.result.headPic);
        DraweeController draweeController=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        RoundingParams roundingParams =RoundingParams.fromCornersRadius(30);
        geimage.getHierarchy().setRoundingParams(roundingParams);
        geimage.setController(draweeController);
        gename.setText(ziliaoBean.result.nickName);
        gepwd.setText(ziliaoBean.result.phone);


    }

    @Override
    public void MingSuccess(MingBean mingBean) {

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
