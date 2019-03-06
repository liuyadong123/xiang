package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.ChaBean;
import com.example.dong.xiang.bean.XinxiBean;
import com.example.dong.xiang.net.HejiCallback;
import com.example.dong.xiang.view.MyView;

import java.util.ArrayList;
import java.util.List;

public class DanAdapter extends RecyclerView.Adapter<DanAdapter.ChaVh> {
    private List<XinxiBean.OrderListBean.DetailListBean> list;
    private Context context;


    public DanAdapter(List<XinxiBean.OrderListBean.DetailListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ChaVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.dan_item,viewGroup,false);
        ChaVh chaVh =new ChaVh(view);
        return chaVh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChaVh chaVh, final int i) {
        String commodityPic = list.get(i).getCommodityPic();
        String[] split = commodityPic.split(",");

        Glide.with(context).load(split[0]).into(chaVh.gouimage);
        chaVh.gouprice.setText(list.get(i).getCommodityPrice()+"");
        chaVh.goutext.setText(list.get(i).getCommodityName());



    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }




    public class ChaVh extends RecyclerView.ViewHolder {
        private MyView myView;
        private CheckBox checkBox;
        private TextView goutext,gouprice;
        private ImageView gouimage;
        public ChaVh(@NonNull View itemView) {
            super(itemView);
            myView=itemView.findViewById(R.id.myview);
            checkBox=itemView.findViewById(R.id.gouck);
            goutext=itemView.findViewById(R.id.goutext);
            gouprice=itemView.findViewById(R.id.gouprice);
            gouimage=itemView.findViewById(R.id.gouimage);
        }
    }
}
