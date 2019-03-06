package com.example.dong.xiang.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.ShopBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ZujiAdapter extends RecyclerView.Adapter<ZujiAdapter.ShopVH> {
  private List<ZuJiBean.ZuJiResult> list;
  private Context context;

    public ZujiAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public  void  setList(List<ZuJiBean.ZuJiResult> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public  void  addall(List<ZuJiBean.ZuJiResult> list){
        if (list!=null) {
           this.list.addAll(list);
            notifyDataSetChanged();
        }


 }
    @NonNull
    @Override
    public ZujiAdapter.ShopVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.zuji_item,parent,false);
        ShopVH shopVH =new ShopVH(view);
        return shopVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ZujiAdapter.ShopVH holder, final int position) {
        Uri uri =Uri.parse(list.get(position).masterPic);

        DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.image.setController(draweeController);
        holder.price.setText(list.get(position).price);
        holder.name.setText(list.get(position).commodityName);
        holder.bzd.setText("已浏览"+list.get(position).browseNum);

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ShopVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView name,price,bzd,time;
        public ShopVH(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            bzd=itemView.findViewById(R.id.bzd);
            time=itemView.findViewById(R.id.time);
        }
    }
}
