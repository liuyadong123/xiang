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
import com.example.dong.xiang.net.HejiCallback;
import com.example.dong.xiang.net.NumCallback;
import com.example.dong.xiang.view.MyView;

import java.util.ArrayList;
import java.util.List;

public class ChaAdapter extends RecyclerView.Adapter<ChaAdapter.ChaVh> {
    private List<ChaBean.Result> list;
    private Context context;

    public ChaAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void setList(List<ChaBean.Result> list){
        this.list=list;
        notifyDataSetChanged();
    }
    private HejiCallback hejiCallback;

    public void setHejiCallback(HejiCallback hejiCallback) {
        this.hejiCallback = hejiCallback;
    }

    @NonNull
    @Override
    public ChaVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.gouwu_item,viewGroup,false);
        ChaVh chaVh =new ChaVh(view);
        return chaVh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChaVh chaVh, final int i) {
        Glide.with(context).load(list.get(i).pic).into(chaVh.gouimage);
        chaVh.gouprice.setText(list.get(i).price+"");
        chaVh.goutext.setText(list.get(i).commodityName);
        chaVh.checkBox.setChecked(list.get(i).checkedbox);
        chaVh.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ChaBean.Result result : list) {
                    result.checkedbox=chaVh.checkBox.isChecked();
                }
                notifyDataSetChanged();
                hejiCallback.PriceCallback();
            }
        });
        chaVh.myView.setAddJIanCallback(new MyView.AddJIanCallback() {
            @Override
            public void AddCallback(int name) {
              list.get(i).Chanum=name;
                hejiCallback.PriceCallback();
            }
        });

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
