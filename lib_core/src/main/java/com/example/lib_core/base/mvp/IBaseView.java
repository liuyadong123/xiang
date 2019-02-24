package com.example.lib_core.base.mvp;

public interface IBaseView {
    BasePresenter inpresenter();
    void failure(String msg);
}
