package com.example.dong.xiang.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dong.xiang.HomeView;
import com.example.dong.xiang.R;
import com.example.dong.xiang.activity.XiangActivity;
import com.example.dong.xiang.adapter.LalbeAdapter;
import com.example.dong.xiang.adapter.ProductAdapter;
import com.example.dong.xiang.adapter.ShopAdapter;
import com.example.dong.xiang.adapter.StytleAdapter;
import com.example.dong.xiang.adapter.ViewPagerAdapter;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.JiaGouBean;
import com.example.dong.xiang.bean.LableBean;
import com.example.dong.xiang.bean.ProductBean;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.ShouyeBean;
import com.example.dong.xiang.bean.XiangBean;
import com.example.dong.xiang.contract.Contract;
import com.example.dong.xiang.contract.ProductContract;
import com.example.dong.xiang.presenter.Presenter;
import com.example.dong.xiang.presenter.ProductPreseter;
import com.example.lib_core.base.mvp.BaseFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment  extends BaseFragment<ProductContract.IProductModle,
        ProductContract.ProductPreseter>
        implements ProductContract.IProductView ,Contract.IView,ShopAdapter.OnitemCallback,ProductAdapter.ProductCallBack {

    private ProductAdapter productAdapter;
    private RecyclerView rv, rvs,dierge,disange;
    private XRecyclerView rvsss;
    private LinearLayout linerse;
    private LinearLayout linersss, shibai, lable;
    private ImageView sbai, tvs;
    private TextView ivs, ev, ivss, evs;
    private TextView tv;

    private EditText ed, eds, edss;
    private Presenter presenr = new Presenter(this);
    private ShopAdapter shopAdapter;
    private int page = 1;
    private String count = "5";

    //返回键
    private View.OnKeyListener backListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (linerse.getVisibility() == View.GONE) {
                    linerse.setVisibility(View.VISIBLE);
                }
                return true;
            }
            return false;
        }
    };

    @Override
    public void success(List<ProductBean.ResultBean> list) {
        productAdapter.setListBanner(list);
    }

    @Override
    protected void initView(View view) {

        //返回键
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setOnKeyListener(backListener);
        //获取id
        shibai = view.findViewById(R.id.shibai);
        sbai = view.findViewById(R.id.sbai);
        ed = view.findViewById(R.id.ed);
        ev = view.findViewById(R.id.ev);
        evs = view.findViewById(R.id.evs);
        ivs = view.findViewById(R.id.ivs);
        ivss = view.findViewById(R.id.ivss);
        eds = view.findViewById(R.id.eds);
        edss = view.findViewById(R.id.edss);
        tv = view.findViewById(R.id.tv);
        rv = view.findViewById(R.id.rv);
        rvs = view.findViewById(R.id.rvs);
        lable = view.findViewById(R.id.lable);
        linerse = view.findViewById(R.id.linerse);
        linersss = view.findViewById(R.id.linersss);
        productAdapter = new ProductAdapter(getActivity());
        rv.setAdapter(productAdapter);
        productAdapter.setProductCallBack(this);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvsss = view.findViewById(R.id.rvss);
        shopAdapter = new ShopAdapter(getActivity());
        rvsss.setAdapter(shopAdapter);
        shopAdapter.setCallback(this);
        rvsss.setPullRefreshEnabled(true);
        rvsss.setLoadingMoreEnabled(true);
        //上下拉加载
        rvsss.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                page = 1;
                initDatas();
            }

            @Override
            public void onLoadMore() {
                page++;
                initDatas();
            }
        });
        rvsss.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //返回键
        ivs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerse.setVisibility(View.VISIBLE);
                linersss.setVisibility(View.INVISIBLE);
            }
        });
        //返回键
        ivss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerse.setVisibility(View.VISIBLE);
                linersss.setVisibility(View.INVISIBLE);
            }
        });
        //搜素
        evs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatasss();
                linersss.setVisibility(View.VISIBLE);
                linerse.setVisibility(View.GONE);
                lable.setVisibility(View.GONE);
                shibai.setVisibility(View.GONE);
            }
        });
        //搜素
        ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initDatass();
                linersss.setVisibility(View.VISIBLE);
                linerse.setVisibility(View.GONE);
                lable.setVisibility(View.GONE);
                shibai.setVisibility(View.GONE);
            }
        });
        //搜素
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatas();
                linersss.setVisibility(View.VISIBLE);
                linerse.setVisibility(View.GONE);
                lable.setVisibility(View.GONE);
                shibai.setVisibility(View.GONE);

            }
        });


    }
    //搜素
    private void initDatasss() {
        String s = edss.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("keyword", s);
        params.put("page", page + "");
        params.put("count", count);
        presenr.shop(params);
    }
    //搜素
    private void initDatass() {
        String s = eds.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("keyword", s);
        params.put("page", page + "");
        params.put("count", count);
        presenr.shop(params);
    }
     //搜素
    private void initDatas() {
        String s = ed.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("keyword", s);
        params.put("page", page + "");
        params.put("count", count);
        presenr.shop(params);
    }
    //首页和 无限轮播
    @Override
    protected void initData() {
        super.initData();
        presenter.product(new HashMap<String, String>());
        presenter.shouye(new HashMap<String, String>());
    }


    @Override
    protected int getLayoutid() {
        return R.layout.activity_home;
    }

    @Override
    public BasePresenter inpresenter() {
        return new ProductPreseter();
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void succes(String result) {

    }
   //首页
    @Override
    public void successs(ShouyeBean shouyeBean) {

        productAdapter.setList(shouyeBean.getResult(), shouyeBean.getResult().getRxxp(),
                shouyeBean.getResult().getMlss(), shouyeBean.getResult().getPzsh());

    }

   //搜素列表
    @Override
    public void ShopSuccess(ShopBean shopBean) {
        List<ShopBean.shopData> result = shopBean.result;
            if (result.size() != 0) {
                if (page==1) {
                    rvsss.refreshComplete();
                    shopAdapter.setList(shopBean.result);
                } else {
                    rvsss.loadMoreComplete();
                    shopAdapter.addList(shopBean.result);
                }



            } else {
                linersss.setVisibility(View.GONE);
                shibai.setVisibility(View.VISIBLE);
                linerse.setVisibility(View.GONE);
                lable.setVisibility(View.GONE);
            }




}

   //详情
    @Override
    public void XiangSuccess(XiangBean xiangBean) {
        EventBus.getDefault().postSticky(xiangBean);
        startActivity(new Intent(getActivity(),XiangActivity.class));



    }
     //首页二级列表
    @Override
    public void LavleSuccess(LableBean lableBean) {
        List<LableBean.ResultBean> result = lableBean.getResult();
        LalbeAdapter lalbeAdapter =new LalbeAdapter(result,getActivity());
        rvs.setAdapter(lalbeAdapter);
        rvs.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //详情
        lalbeAdapter.setProductCallBack(new StytleAdapter.ProductCallBacks() {
            @Override
            public void onclickitemCallback(String id) {
                presenr.xiang(id);
            }
        });
    }

    @Override
    public void ChaGouSUccess(ChaBean chaBean) {

    }

    @Override
    public void Failure(String msg) {
        Toast.makeText(getActivity(),"msg",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void JIaGouSUccess(JiaGouBean jiaGouBean) {

    }

    //详情
    @Override
    public void ccback(String id) {
        presenr.xiang(id);
    }
     //首页二级列表
    @Override
    public void onclickCallback(int id) {
        lable.setVisibility(View.VISIBLE);
        linersss.setVisibility(View.GONE);
        linerse.setVisibility(View.GONE);
        shibai.setVisibility(View.GONE);
        HashMap<String,String> params= new HashMap<>();
        params.put("labelId",id+"");
        params.put("page",page+"");
        params.put("count",count);
        presenr.lable(params);
    }
 //详情
    @Override
    public void onclickitem(String id) {
        presenr.xiang(id);
    }


}
