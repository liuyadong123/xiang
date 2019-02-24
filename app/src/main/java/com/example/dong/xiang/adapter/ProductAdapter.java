package com.example.dong.xiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dong.xiang.R;
import com.example.dong.xiang.bean.ProductBean;
import com.example.dong.xiang.bean.ShouyeBean;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ShouyeBean.ResultBean list;
    private List<String> list1;
    private List<ShouyeBean.ResultBean.RxxpBean> listHot;
    private List<ShouyeBean.ResultBean.MlssBean> listStyle;
    private List<ShouyeBean.ResultBean.PzshBean> listLive;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setList(ShouyeBean.ResultBean list, List<ShouyeBean.ResultBean.RxxpBean> listHot, List<ShouyeBean.ResultBean.MlssBean> listStyle, List<ShouyeBean.ResultBean.PzshBean> listLive) {
        this.list = list;
        this.listHot = listHot;
        this.listStyle = listStyle;
        this.listLive = listLive;
        notifyDataSetChanged();
    }

    public void setListBanner(List<ProductBean.ResultBean> listBanner) {

        list1 = new ArrayList<>();
        for (ProductBean.ResultBean l : listBanner) {
            list1.add(l.getImageUrl());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       if (getItemViewType(i)==0){
           View view =LayoutInflater.from(context).inflate(R.layout.home_lun,viewGroup,false);
           oneVH oneVH =new oneVH(view);
           return oneVH;
       }else if (getItemViewType(i)==1){
          View view =  LayoutInflater.from(context).inflate(R.layout.home_hot,viewGroup,false);
           ViewHolder_hot viewHolder_hot = new ViewHolder_hot(view);
           return viewHolder_hot;

       }else if (getItemViewType(i)==2){
          View view =  LayoutInflater.from(context).inflate(R.layout.home_style,viewGroup,false);
           ViewHolder_style holder_style = new ViewHolder_style(view);
           return holder_style;
       }else{
           View view =  LayoutInflater.from(context).inflate(R.layout.home_live,viewGroup,false);
           ViewHolder_live holder_live = new ViewHolder_live(view);
           return holder_live;
       }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
         if (getItemViewType(i)==0){

             ((oneVH)holder).fly.setImagesUrl(list1);
         }else if (getItemViewType(i)==1){
             ((ViewHolder_hot)holder).hot.setText(listHot.get(0).getName());
             List<ShouyeBean.ResultBean.RxxpBean.CommodityListBean> commodityList = listHot.get(0).getCommodityList();
             HotAdapter hotAdapter =new HotAdapter(commodityList,context);
             ((ViewHolder_hot)holder).rvhot.setAdapter(hotAdapter);
             ((ViewHolder_hot)holder).rvhot.setLayoutManager(new GridLayoutManager(context,3));
             ((ViewHolder_hot)holder).imageid.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     productCallBack.onclickCallback(listHot.get(0).getId());
                 }
             });
             hotAdapter.setProductCallBack(new StytleAdapter.ProductCallBacks() {
                 @Override
                 public void onclickitemCallback(String id) {
                     productCallBack.onclickitem(id);
                 }
             });
         }
         else if (getItemViewType(i)==2) {
             ((ViewHolder_style) holder).mo.setText(listStyle.get(0).getName());
             final List<ShouyeBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = listStyle.get(0).getCommodityList();
             StytleAdapter stytleAdapter =new StytleAdapter(commodityList,context);
             ((ViewHolder_style) holder).styrv.setAdapter(stytleAdapter);
             ((ViewHolder_style) holder).styrv.setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolder_style)holder).image.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     productCallBack.onclickCallback(listStyle.get(0).getId());
                 }
             });
              stytleAdapter.setProductCallBack(new StytleAdapter.ProductCallBacks() {
                  @Override
                  public void onclickitemCallback(String id) {
                      Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
                       productCallBack.onclickitem(id);
                  }
              });

         }
         else{
             ((ViewHolder_live)holder).ping.setText(listLive.get(0).getName());
             final List<ShouyeBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = listLive.get(0).getCommodityList();
             LeaveAdapter leaveAdapter =new LeaveAdapter(commodityList,context);
             ((ViewHolder_live)holder).liveRv.setAdapter(leaveAdapter);
             ((ViewHolder_live)holder).liveRv.setLayoutManager(new GridLayoutManager(context,2));
          ((ViewHolder_live)holder).image.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     productCallBack.onclickCallback(listLive.get(0).getId());
                 }
             });
          leaveAdapter.setProductCallBack(new StytleAdapter.ProductCallBacks() {
              @Override
              public void onclickitemCallback(String id) {
                  productCallBack.onclickitem(id);
              }
          });
         }
    }

    @Override
    public int getItemCount() {
        return list==null?0:4;
    }

    @Override
    public int getItemViewType(int position) {
      if (position==0){
          return  0;
      } else if (position==1&&1002==listHot.get(position-1).getId()) {
          return 1;
      }else if (position==2&&1003==listStyle.get(position-2).getId()){
          return 2;
      }else {
          return 3;
      }
    }




    public  class  oneVH extends  RecyclerView.ViewHolder {
        private FlyBanner fly;

        public oneVH(@NonNull View itemView) {
            super(itemView);
            fly = itemView.findViewById(R.id.vp_lun);
        }
    }
        class ViewHolder_hot extends RecyclerView.ViewHolder{
             ImageView imageid;
            TextView hot;
            RecyclerView rvhot;

            public ViewHolder_hot(View itemView) {
                super(itemView);
                 hot=itemView.findViewById(R.id.hot);
                 rvhot=itemView.findViewById(R.id.hotRv);
                 imageid=itemView.findViewById(R.id.imageid);

            }
        }

        class ViewHolder_style extends RecyclerView.ViewHolder{
            TextView mo;
            ImageView image;
            RecyclerView styrv;

            public ViewHolder_style(View itemView) {
                super(itemView);
                styrv=itemView.findViewById(R.id.styleRv);
                mo=itemView.findViewById(R.id.mo);
                image=itemView.findViewById(R.id.image);
            }
        }

        class ViewHolder_live extends RecyclerView.ViewHolder{

            TextView ping;
            ImageView image;
             RecyclerView liveRv;
            public ViewHolder_live(View itemView) {
                super(itemView);
                liveRv=itemView.findViewById(R.id.liveRv);
                ping=itemView.findViewById(R.id.ping);
                image=itemView.findViewById(R.id.image);

            }
        }
        private ProductCallBack productCallBack;

    public void setProductCallBack(ProductCallBack productCallBack) {
        this.productCallBack = productCallBack;
    }

    public interface ProductCallBack{
            void onclickCallback(int id);
            void onclickitem(String id);
        }

}

