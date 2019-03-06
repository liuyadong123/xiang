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
import com.example.dong.xiang.bean.QuanBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuanAdapter extends RecyclerView.Adapter<QuanAdapter.ShopVH> {
  private List<QuanBean.Result> list;
  private Context context;
    public static final String Quan_FaBu_Time = "yyyy-MM-dd HH:mm:ss";
    public QuanAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public  void  setList(List<QuanBean.Result> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public  void  addall(List<QuanBean.Result> list){
        if (list!=null) {
           this.list.addAll(list);
            notifyDataSetChanged();
        }


 }
    @NonNull
    @Override
    public QuanAdapter.ShopVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.dis_item,parent,false);
        ShopVH shopVH =new ShopVH(view);
        return shopVH;
    }

    @Override
    public void onBindViewHolder(@NonNull QuanAdapter.ShopVH holder, final int position) {
        Uri uri =Uri.parse(list.get(position).headPic);

        DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.quanimag.setController(draweeController);
        holder.name.setText(list.get(position).nickName);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Quan_FaBu_Time, Locale.getDefault());
        holder.time.setText(dateFormat.format(list.get(position).createTime));
        holder.fa.setText(list.get(position).content);
        Uri uri1 =Uri.parse(list.get(position).image);
        DraweeController draweeController1 =Fresco.newDraweeControllerBuilder()
                .setUri(uri1)
                .setAutoPlayAnimations(true)
                .build();
        holder.quantupian.setController(draweeController1);

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ShopVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView quanimag,quantupian;
        private TextView name,time,fa;
        public ShopVH(View itemView) {
            super(itemView);
            quanimag=itemView.findViewById(R.id.quanimage);
            name=itemView.findViewById(R.id.quanname);
            time=itemView.findViewById(R.id.quanshijian);
            quantupian=itemView.findViewById(R.id.quantupian);
            fa=itemView.findViewById(R.id.fa);
        }
    }
}
