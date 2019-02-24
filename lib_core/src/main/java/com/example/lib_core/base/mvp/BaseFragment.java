package com.example.lib_core.base.mvp;

import android.view.KeyEvent;
import android.view.View;

public abstract class BaseFragment<M extends  IBaseModle , P extends  BasePresenter> extends com.example.lib_core.base.BaseFragment implements IBaseView  {
    public  M modle;
    public  P presenter;

    @Override
    protected void initData() {
        presenter= (P) inpresenter();
        if (presenter!=null){
            modle= (M) presenter.getModle();
            presenter.attach(modle,this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.deattach();
    }



}
