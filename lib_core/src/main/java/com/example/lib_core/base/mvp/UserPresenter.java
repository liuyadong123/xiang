package com.example.lib_core.base.mvp;

import java.lang.ref.WeakReference;

public abstract class UserPresenter<M,V> {
    public M  modle;
    public V View;
    private WeakReference<V> weakReference;
    public  abstract  M getModle();
    // 绑定
    public  void attach(M modle,V view){
        this.modle=modle;
       weakReference=new WeakReference<>(view);
       this.View=weakReference.get();

    }
    public  void  deattach(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
            this.View=null;
        }
        }
}
