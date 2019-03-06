package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.XinxiBean;

import java.util.List;

public class PingAdapter extends RecyclerView.Adapter<PingAdapter.DingVH> implements JiaAdapter.FabuCallback {
    private List<XinxiBean.OrderListBean> list;
    private Context context;

    public PingAdapter(List<XinxiBean.OrderListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PingAdapter.DingVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.ping_item,viewGroup,false);
        DingVH dingVH =new DingVH(view);
        return dingVH;
    }

    @Override
    public void onBindViewHolder(@NonNull PingAdapter.DingVH dingVH, final int i) {
       dingVH.dingdanhao.setText("订单号"+list.get(i).getOrderId());
       dingVH.duoshao.setText(list.get(i).getExpressSn());
        List<XinxiBean.OrderListBean.DetailListBean> detailList = list.get(i).getDetailList();
        JiaAdapter danAdapter  =new JiaAdapter(detailList,context);
        dingVH.danrv.setAdapter(danAdapter);
        dingVH.danrv.setLayoutManager(new LinearLayoutManager(context));
        dingVH.shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callback.deletes(list.get(i).getOrderId());
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        danAdapter.setFabuCallback(this);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public void bucallback() {
        callback.picallback();

    }

    public class DingVH extends RecyclerView.ViewHolder {
        private TextView dingdanhao,duoshao;
        private RecyclerView danrv;
        private ImageView shanchu;
        public DingVH(@NonNull View itemView) {
            super(itemView);
            dingdanhao=itemView.findViewById(R.id.dingdanhao);
            duoshao=itemView.findViewById(R.id.duoshao);
            danrv=itemView.findViewById(R.id.jirv);
            shanchu=itemView.findViewById(R.id.shanchu);


        }
    }
    public   DeleteCallback callback;

    public void setCallback(DeleteCallback callback) {
        this.callback = callback;
    }

    public  interface DeleteCallback{
        void deletes(String id);
       void picallback();
    }
}
