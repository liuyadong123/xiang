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
import com.example.dong.xiang.view.MyView;

import java.util.ArrayList;
import java.util.List;

public class JisuanAdapter extends RecyclerView.Adapter<JisuanAdapter.ChaVh> {
    private List<ChaBean.Result> list;
    private Context context;

    public JisuanAdapter(Context context) {
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
        View view =LayoutInflater.from(context).inflate(R.layout.jisuan_item,viewGroup,false);
        ChaVh chaVh =new ChaVh(view);
        return chaVh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChaVh chaVh, final int i) {
        Glide.with(context).load(list.get(i).pic).into(chaVh.gouimage);
        chaVh.gouprice.setText(list.get(i).price+"");
        chaVh.goutext.setText(list.get(i).commodityName);
        chaVh.myView.setAddJIanCallback(new MyView.AddJIanCallback() {
            @Override
            public void AddCallback(int name) {
                list.get(i).Chanum=name;
                hejiCallback.PriceCallback();
                notifyDataSetChanged();
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
            goutext=itemView.findViewById(R.id.goutext);
            gouprice=itemView.findViewById(R.id.gouprice);
            gouimage=itemView.findViewById(R.id.gouimage);
        }
    }

}
