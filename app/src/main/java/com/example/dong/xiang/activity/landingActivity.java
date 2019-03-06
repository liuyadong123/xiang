package com.example.dong.xiang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.MainActivity;
import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.LadingBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.bean.XiuBean;
import com.example.dong.xiang.bean.zhengBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.contract.LadingContract;
import com.example.dong.xiang.presenter.LadingPresenter;
import com.example.dong.xiang.presenter.Presenter;
import com.example.lib_core.base.mvp.UserActivity;
import com.example.lib_core.base.mvp.UserPresenter;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public  class landingActivity extends UserActivity<LadingContract.ILadingModle,LadingContract.LadingPresenter> implements LadingContract.ILadingView,Contract.IView {

    @BindView(R.id.ed_phone)
    EditText phone;
    @BindView(R.id.ed_pwd)
    EditText pwd;
    @BindView(R.id.tv_deng)
    TextView deng;
    @BindView(R.id.tv_qq)
    TextView qq;
    @BindView(R.id.tv_zhu)
    TextView zhu;
    @BindView(R.id.jz)
    CheckBox jz;
    @BindView(R.id.cb_eye)
    CheckBox cb;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    UMShareAPI umShareAPI;



    @Override
    protected void initData() {
        super.initData();


    }
    @OnClick(R.id.tv_deng)
    public void click(){
        String phones = phone.getText().toString();
        String pwds = pwd.getText().toString();
        if (jz.isChecked()){
            editor.putString("phones" ,phones);
            editor.putString("pwds" ,pwds);
            editor.putBoolean("jz",true);
            editor.commit();
        }

        HashMap<String,String> params=new HashMap<>();
        params.put("phone",phones);
        params.put("pwd",pwds);
        if (params!=null){
        presenter.LadingPresenter(params);
        }

    }
    @OnClick(R.id.tv_qq)
    public void clickss(){
         UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    System.out.println("回调成功");
                    startActivity(new Intent(landingActivity.this,DibuActivity.class));
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {

                }
            });

        }



    @OnClick(R.id.tv_zhu)
    public void clicks(){
        startActivity(new Intent(landingActivity.this,RegisteredActivity.class));

    }
    @Override
    public void success(LadingBean ladingBean) {

        Toast.makeText(landingActivity.this,ladingBean.message+"",Toast.LENGTH_SHORT).show();
        if ("登录成功".equals(ladingBean.message)){
            String userId = ladingBean.result.userId;
            String sessionId = ladingBean.result.sessionId;
            Presenter present=new Presenter(this);
            present.huo(userId,sessionId);
            startActivity(new Intent(landingActivity.this,DibuActivity.class));

        }
    }

    @Override
    public void keywordsEmpty(String error) {
        Toast.makeText(landingActivity.this,error,Toast.LENGTH_SHORT).show();



    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        sharedPreferences = getSharedPreferences("uerer", MODE_PRIVATE);
        //sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();
        boolean jz1 = sharedPreferences.getBoolean("jz", false);
        if (jz1){
            String phones = sharedPreferences.getString("phones", null);
            String pwds = sharedPreferences.getString("pwds", null);
            phone.setText(phones);
            pwd.setText(pwds);
            jz.setChecked(true);

        }




    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_landing;
    }

    @Override
    public UserPresenter initPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void failure(String msg) {
        Toast.makeText(landingActivity.this,msg,Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
    public void ZhengSuccess(zhengBean bean) {

    }

    @Override
    public void XiuSuccess(XiuBean bean) {

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
