package com.example.dong.xiang.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.adapter.ZujiAdapter;
import com.example.dong.xiang.bean.MingBean;
import com.example.dong.xiang.bean.ShangBean;
import com.example.dong.xiang.bean.ZiliaoBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.example.dong.xiang.contract.ZixiaoContract;
import com.example.dong.xiang.presenter.ZiLiaoPresenter;

import java.util.HashMap;

//足迹
public class TrackActivity extends AppCompatActivity implements ZixiaoContract.Contract.ZiView {
    private RecyclerView zujirv;
    private ZiLiaoPresenter ziLiaoPresenter;
    private ZujiAdapter zujiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
    }

    private void initData() {
        ziLiaoPresenter=new ZiLiaoPresenter(this);
        HashMap<String,String> params =new HashMap<>();
        params.put("page","1");
        params.put("count","5");
        ziLiaoPresenter.ZuJi(params);
    }

    private void initView() {
        zujirv=findViewById(R.id.zujirv);
        zujiAdapter=new ZujiAdapter(this);
        zujirv.setAdapter(zujiAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        zujirv.setLayoutManager(layoutManager);

    }

    @Override
    public void ZiSuccess(ZiliaoBean ziliaoBean) {

    }

    @Override
    public void MingSuccess(MingBean mingBean) {

    }

    @Override
    public void ZujiSuccess(ZuJiBean zuJiBean) {
        zujiAdapter.setList(zuJiBean.result);
        Toast.makeText(this,zuJiBean.message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shangSuccess(ShangBean shangBean) {

    }

    @Override
    public void Failure(String msg) {

    }
}
