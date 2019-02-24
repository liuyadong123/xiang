package com.example.lib_core.base.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M,V> {
    public M modle;
    public V view;
    public WeakReference<V> weakReference;
    public abstract M getModle();

    public  void attach(M modle,V view){
         this.modle=modle;
         weakReference=new WeakReference<>(view);
         this.view=weakReference.get();
    }
    public void deattach(){
        weakReference.clear();;
        this.weakReference=null;
        this.view=null;
    }
}
