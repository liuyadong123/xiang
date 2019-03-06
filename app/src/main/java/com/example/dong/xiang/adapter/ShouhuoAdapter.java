package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.ShouhuoBean;

import java.util.List;

public class ShouhuoAdapter extends RecyclerView.Adapter<ShouhuoAdapter.ShouVH> {
    private List<ShouhuoBean.Result> list;
    private Context context;

    public ShouhuoAdapter(List<ShouhuoBean.Result> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ShouVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.shouhuo_item,viewGroup,false);
        ShouVH shouVH =new ShouVH(view);
        return shouVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShouVH shouVH, final int i) {
         shouVH.shouname.setText(list.get(i).realName);
         shouVH.shouphone.setText(list.get(i).phone);
         shouVH.shoudizi.setText(list.get(i).address);
         shouVH.shanchu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context,"删除",Toast.LENGTH_SHORT).show();
                 list.remove(i);
                 notifyDataSetChanged();

             }
         });
         shouVH.shezhi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context,"默认地址",Toast.LENGTH_SHORT).show();
                 callback.MoRenCallback(list.get(i).id );

             }
         });
         shouVH.xiugain.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context,"修改",Toast.LENGTH_SHORT).show();
                 callback.XiuGaiCallback(list.get(i).id);
             }
         });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ShouVH extends RecyclerView.ViewHolder {
        private TextView  shouname,shouphone,shoudizi,shanchu,xiugain;
        private CheckBox shezhi;

        public ShouVH(@NonNull View itemView) {
            super(itemView);
            shouname=itemView.findViewById(R.id.shouname);
            shouphone=itemView.findViewById(R.id.shouphone);
            shoudizi=itemView.findViewById(R.id.shoudizhi);
            shanchu=itemView.findViewById(R.id.shanchu);
            xiugain=itemView.findViewById(R.id.xiugai);
            shezhi=itemView.findViewById(R.id.shezhi);
        }
    }
     private  ShouhuoCallback callback;

    public void setCallback(ShouhuoCallback callback) {
        this.callback = callback;
    }

    public interface ShouhuoCallback{
         void XiuGaiCallback(String id);
         void MoRenCallback(String id);
     }
}
