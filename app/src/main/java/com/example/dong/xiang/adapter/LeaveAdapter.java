package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.ShouyeBean;

import java.util.List;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.HotVH> {
    private List<ShouyeBean.ResultBean.PzshBean.CommodityListBeanX> listHot;
    private Context context;

    public LeaveAdapter(List<ShouyeBean.ResultBean.PzshBean.CommodityListBeanX> listHot, Context context) {
        this.listHot = listHot;
        this.context = context;
    }

    @NonNull
    @Override
    public HotVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(context).inflate(R.layout.home_styleone,viewGroup,false);
        HotVH hotVH =new HotVH(view);
        return hotVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HotVH hotVH, final int i) {
        Glide.with(context).load(listHot.get(i).getMasterPic()).into(hotVH.image1);
        hotVH.name1.setText(listHot.get(i).getCommodityName());
        hotVH.price1.setText("￥"+listHot.get(i).getPrice()+"");
        hotVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productCallBack.onclickitemCallback(listHot.get(i).getCommodityId()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listHot==null?0:listHot.size();
    }

    public class HotVH extends RecyclerView.ViewHolder {
        ImageView image1;
        TextView name1;
        TextView price1;
        public HotVH(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image1);
            name1 = itemView.findViewById(R.id.name1);
            price1 = itemView.findViewById(R.id.price1);

        }
    }
    private StytleAdapter.ProductCallBacks productCallBack;

    public void setProductCallBack(StytleAdapter.ProductCallBacks productCallBack) {
        this.productCallBack = productCallBack;
    }

    public interface ProductCallBacks{
        void onclickitemCallback(String id);
    }
}
