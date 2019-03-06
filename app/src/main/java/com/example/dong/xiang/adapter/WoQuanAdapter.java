package com.example.dong.xiang.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.QuanBean;
import com.example.dong.xiang.bean.WoQuanBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WoQuanAdapter extends RecyclerView.Adapter<WoQuanAdapter.ShopVH> {
  private List<WoQuanBean.ResultBean> list;
  private Context context;
    public static final String Quan_FaBu_Time = "yyyy-MM-dd HH:mm:ss";
    public WoQuanAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public  void  setList(List<WoQuanBean.ResultBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public  void  addall(List<WoQuanBean.ResultBean> list){
        if (list!=null) {
           this.list.addAll(list);
            notifyDataSetChanged();
        }


 }
    @NonNull
    @Override
    public WoQuanAdapter.ShopVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.quan_item,parent,false);
        ShopVH shopVH =new ShopVH(view);
        return shopVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final WoQuanAdapter.ShopVH holder, final int position) {


        SimpleDateFormat dateFormat = new SimpleDateFormat(Quan_FaBu_Time, Locale.getDefault());
        holder.time.setText(dateFormat.format(list.get(position).getCreateTime()));
        holder.fa.setText(list.get(position).getContent());
        Uri uri1 =Uri.parse(list.get(position).getImage());
        DraweeController draweeController1 =Fresco.newDraweeControllerBuilder()
                .setUri(uri1)
                .setAutoPlayAnimations(true)
                .build();
        holder.quantupian.setController(draweeController1);
        holder.quanshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanShanchuCallback.shancallback(list.get(position).getId()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ShopVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView quantupian;
        private TextView time,fa;
        private CheckBox quanshan;
        public ShopVH(View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.quanshijian);
            quantupian=itemView.findViewById(R.id.quantupian);
            fa=itemView.findViewById(R.id.fa);
            quanshan=itemView.findViewById(R.id.shanquan);
        }
    }
    public QuanShanchuCallback quanShanchuCallback;

    public void setQuanShanchuCallback(QuanShanchuCallback quanShanchuCallback) {
        this.quanShanchuCallback = quanShanchuCallback;
    }

    public interface  QuanShanchuCallback{
        void shancallback(String id);
    }
}
