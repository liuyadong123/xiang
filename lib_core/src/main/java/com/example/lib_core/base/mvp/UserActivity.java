package com.example.lib_core.base.mvp;

import com.example.lib_core.base.BaseActivity;

public abstract class UserActivity<M extends  IUserModle,P extends UserPresenter> extends BaseActivity implements  IUserView {
   private  M modle;
   protected P presenter;



    @Override
    protected void initData() {
     presenter= (P) initPresenter();
        if (presenter!=null){
         modle= (M) presenter.getModle();
         presenter.attach(modle,this);
     }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){

            presenter.deattach();
        }

    }
}
