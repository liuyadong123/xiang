package com.example.dong.xiang.activity;

import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.contract.RegisterContract;
import com.example.dong.xiang.presenter.RegisterPresenter;
import com.example.lib_core.base.mvp.UserActivity;
import com.example.lib_core.base.mvp.UserPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisteredActivity extends UserActivity<RegisterContract.IRegisterModle,RegisterContract.RegisterPresenter>implements RegisterContract.IRegisterView {
    @BindView(R.id.ed_phone)
    EditText phone;
    @BindView(R.id.ed_pwd)
    EditText pwd;
    @BindView(R.id.tv_deng)
    TextView deng;


    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }
    @OnClick(R.id.tv_deng)
    public void click(){
        String phones = phone.getText().toString();
        String pwds = pwd.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("phone",phones);
        params.put("pwd",pwds);
        if (params!=null){
            presenter.LadingPresenters(params);
        }

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_registered;
    }

    @Override
    public void success(String result) {
        Toast.makeText(RegisteredActivity.this,result+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void keywordsEmpty(String error) {

    }

    @Override
    public UserPresenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void failure(String msg) {

    }
}
