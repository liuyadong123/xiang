package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.XinxiBean;
import com.example.dong.xiang.view.MyView;

import java.util.List;

public class DingAdapter extends RecyclerView.Adapter<DingAdapter.DingVH> {
    private List<XinxiBean.OrderListBean> list;
    private Context context;

    public DingAdapter(List<XinxiBean.OrderListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DingAdapter.DingVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.ding_item,viewGroup,false);
        DingVH dingVH =new DingVH(view);
        return dingVH;
    }

    @Override
    public void onBindViewHolder(@NonNull DingAdapter.DingVH dingVH, final int i) {
       dingVH.dingdanhao.setText("订单号"+list.get(i).getOrderId());
       dingVH.shijian.setText(list.get(i).getExpressSn());
       dingVH.duoshao.setText("需付款"+list.get(i).getPayAmount()+"");
        List<XinxiBean.OrderListBean.DetailListBean> detailList = list.get(i).getDetailList();
        DanAdapter danAdapter  =new DanAdapter(detailList,context);
        dingVH.danrv.setAdapter(danAdapter);
        dingVH.danrv.setLayoutManager(new LinearLayoutManager(context));
        dingVH.quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callback.delete(list.get(i).getOrderId());
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        dingVH.tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 callback.zhifu(list.get(i).getOrderId(),list.get(i).getPayAmount()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class DingVH extends RecyclerView.ViewHolder {
        private TextView dingdanhao,shijian,duoshao,tijiao,quxiao;
        private RecyclerView danrv;
        private MyView myView;
        public DingVH(@NonNull View itemView) {
            super(itemView);
            dingdanhao=itemView.findViewById(R.id.dingdanhao);
            shijian=itemView.findViewById(R.id.shijian);
            duoshao=itemView.findViewById(R.id.duoshao);
            tijiao=itemView.findViewById(R.id.tijiao);
            danrv=itemView.findViewById(R.id.jirv);
            quxiao=itemView.findViewById(R.id.quxiao);
            myView=itemView.findViewById(R.id.myview);

        }
    }
    public   DeleteCallback callback;

    public void setCallback(DeleteCallback callback) {
        this.callback = callback;
    }

    public  interface DeleteCallback{
        void delete(String id);
        void zhifu(String id,String price);
    }
}
