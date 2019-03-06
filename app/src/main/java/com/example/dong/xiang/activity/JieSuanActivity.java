package com.example.dong.xiang.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.adapter.JisuanAdapter;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.DeleteBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.JieSuanBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.QueBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.ShouhuoBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.bean.XinxiBean;
import com.example.dong.xiang.bean.XiuBean;
import com.example.dong.xiang.bean.ZhifuBean;
import com.example.dong.xiang.bean.zhengBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.contract.JieSuanContract;
import com.example.dong.xiang.net.HejiCallback;
import com.example.dong.xiang.presenter.JieSuanPresenter;
import com.example.dong.xiang.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

public class JieSuanActivity extends AppCompatActivity implements JieSuanContract.SuanView,
        Contract.IView,HejiCallback{

    private JieSuanPresenter jieSuanPresenter;
    private Presenter presenter;
    private RecyclerView jirv;
    private JisuanAdapter jisuanAdapter;
    private TextView  duoshao,tijiao;
    private List<ChaBean.Result> list;
    private int address=874;
    private String reQuesetBody;
    private String commodityId;
    private int chanum;
    private double num;
    private TextView tianjia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_suan);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initData();
        initView();
    }

    private void initView() {
        jirv=findViewById(R.id.jirv);
        duoshao=findViewById(R.id.duoshao);
        tijiao=findViewById(R.id.tijiao);
        tianjia=findViewById(R.id.tianjia);
        jisuanAdapter=new JisuanAdapter(this);
        jirv.setAdapter(jisuanAdapter);
        jirv.setLayoutManager(new LinearLayoutManager(this));
        jisuanAdapter.setHejiCallback(this);

         tianjia.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });

        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reQuesetBody = "[{" +
                        "commodityId:"+ commodityId +
                        ",amount:"+ chanum +"}]";
                HashMap<String,String> params =new HashMap<>();
                params.put("orderInfo",reQuesetBody);
                params.put("totalPrice", JieSuanActivity.this.num+"");
                params.put("addressId",address+"");
                jieSuanPresenter.suan(params);
            }
        });

    }


    private void initData() {
        jieSuanPresenter = new JieSuanPresenter(this);

        presenter = new Presenter(this);
        presenter.ChaGOU(new HashMap<String, String>());

    }
    private void getPrice() {
        num = 0;
        for (ChaBean.Result result : list) {
            commodityId = result.commodityId;
             chanum = result.Chanum;

            num +=result.price*result.Chanum;

        }
        duoshao.setText("合计:￥"+ num);


    }

    @Override
    public void suanSuccess(JieSuanBean jieSuanBean) {

        Toast.makeText(JieSuanActivity.this,jieSuanBean.message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void xinSuccess(XinxiBean xinxiBean) {

    }

    @Override
    public void DeleteSuccess(DeleteBean deleteBean) {

    }

    @Override
    public void ZhifuSuccess(ZhifuBean zhifuBean) {

    }

    @Override
    public void quefuSuccess(QueBean queBean) {

    }


    @Override
    public void ChaGouSUccess(ChaBean chaBean) {
        list=chaBean.result;
        jisuanAdapter.setList(chaBean.result);
        for (ChaBean.Result result : list) {
            result.Chanum=1;
        }
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
    public void Failure(String msg) {

    }

    @Override
    public void JIaGouSUccess(JiaGouBean jiaGouBean) {

    }

    @Override
    public void PriceCallback() {
        getPrice();
    }
}
