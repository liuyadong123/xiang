package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.QianBean;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class QianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private QianBean.ResultBean list1;
    private List<QianBean.ResultBean.DetailListBean> list;
    private Context context;
    public static final String QuanZi_FaBu_Time = "yyyy-MM-dd HH:mm:ss";
    public QianAdapter(QianBean.ResultBean list1,List<QianBean.ResultBean.DetailListBean> list, Context context) {
        this.list = list;
        this.context = context;
        this.list1=list1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i)==0){
            View view=LayoutInflater.from(context).inflate(R.layout.bao_item,viewGroup,false);
            QianVH qianVH =new QianVH(view);
            return qianVH;
        }else {
            View view=LayoutInflater.from(context).inflate(R.layout.qianbao_item,viewGroup,false);
            BaoVH baoVH =new BaoVH(view);
            return baoVH;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (getItemViewType(i)==0){
            ((QianVH) holder).qian.setText(list1.getBalance()+"");
        }else {
            ((BaoVH) holder).jine.setText("￥:"+list.get(0).getAmount());
            //3 时间转换格式

          //将发布时间 long类型转换

           SimpleDateFormat dateFormat = new SimpleDateFormat(QuanZi_FaBu_Time, Locale.getDefault());
           // holder.mTxtTimeQZAdapter.setText(dateFormat.format(mList.get(position).getCreat
            ((BaoVH) holder).shijian.setText(dateFormat.format(list.get(0).getConsumerTime()));
        }

    }


    @Override
    public int getItemCount() {
        return list1==null?0:2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else {
            return 1;
        }
    }

    public class QianVH extends RecyclerView.ViewHolder {
        private TextView qian;
        public QianVH(@NonNull View itemView) {
            super(itemView);
            qian=itemView.findViewById(R.id.qian);
        }
    }
    public class BaoVH extends RecyclerView.ViewHolder {
        private TextView jine,shijian;
        public BaoVH(@NonNull View itemView) {
            super(itemView);
            jine=itemView.findViewById(R.id.jine);
            shijian=itemView.findViewById(R.id.shijian);
        }
    }
}
