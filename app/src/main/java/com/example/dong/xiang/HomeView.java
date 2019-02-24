package com.example.dong.xiang;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeView extends LinearLayout {
    private ImageView shop_class;
    private ImageView select;
    private EditText et_seleceshop;
    public HomeView(Context context) {
        this(context,null);
    }

    public HomeView(Context context, AttributeSet attrs) {
       this(context, attrs,0);
    }

    public HomeView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_view,this,true);
        shop_class= view.findViewById(R.id.shop_class);
        et_seleceshop = view.findViewById(R.id.et_seleceshop);
        select = view.findViewById(R.id.select);

        shop_class.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchViewCallback!=null){
                    searchViewCallback.onClickListener(v);
                }

            }
        });

       select.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_seleceshop.getText().toString())){
                    if (searchViewCallback!=null)
                        searchViewCallback.keywordsEmpty();
                    return;
                }
                if (searchViewCallback!=null)
                    searchViewCallback.searchClick(et_seleceshop.getText().toString());


            }
        });

    }
    private SearchViewCallback searchViewCallback;

    /**
     * 调用者去调用
     * @param searchViewCallback
     */
    public void setSearchViewCallback(SearchViewCallback searchViewCallback) {
        this.searchViewCallback = searchViewCallback;
    }

    public interface SearchViewCallback{

        void keywordsEmpty();
        void onClickListener(View v);//点击事件
        void searchClick(String keywords);//搜索点击事件
    }
}
