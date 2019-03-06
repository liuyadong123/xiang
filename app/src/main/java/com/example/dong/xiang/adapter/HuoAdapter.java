package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.XinxiBean;

import java.util.List;

public class HuoAdapter extends RecyclerView.Adapter<HuoAdapter.DingVH> {
    private List<XinxiBean.OrderListBean> list;
    private Context context;

    public HuoAdapter(List<XinxiBean.OrderListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HuoAdapter.DingVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.huo_item,viewGroup,false);
        DingVH dingVH =new DingVH(view);
        return dingVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HuoAdapter.DingVH dingVH, final int i) {
       dingVH.dingdanhao.setText("订单号"+list.get(i).getOrderId());
       dingVH.shijian.setText(list.get(i).getExpressSn());
        List<XinxiBean.OrderListBean.DetailListBean> detailList = list.get(i).getDetailList();
        DanAdapter danAdapter  =new DanAdapter(detailList,context);
        dingVH.danrv.setAdapter(danAdapter);
        dingVH.danrv.setLayoutManager(new LinearLayoutManager(context));
        dingVH.paijian.setText("派件公司:"+list.get(i).getExpressCompName());
        dingVH.danhao.setText("快递单号:"+list.get(i).getExpressSn());
        dingVH.tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 notifyDataSetChanged();
                 callback.zhifu(list.get(i).getOrderId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class DingVH extends RecyclerView.ViewHolder {
        private TextView dingdanhao,shijian,danhao,tijiao,paijian;
        private RecyclerView danrv;
        public DingVH(@NonNull View itemView) {
            super(itemView);
            dingdanhao=itemView.findViewById(R.id.dingdanhao);
            shijian=itemView.findViewById(R.id.shijian);
            danhao=itemView.findViewById(R.id.danhao);
            tijiao=itemView.findViewById(R.id.tijiao);
            danrv=itemView.findViewById(R.id.jirv);
            paijian=itemView.findViewById(R.id.paijian);

        }
    }
    public   DeleteCallback callback;

    public void setCallback(DeleteCallback callback) {
        this.callback = callback;
    }

    public  interface DeleteCallback{
        void zhifu(String id);
    }
}
