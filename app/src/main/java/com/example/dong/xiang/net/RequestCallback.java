package com.example.dong.xiang.net;

import com.example.dong.xiang.bean.LadingBean;

import java.util.List;

public interface RequestCallback {
    void success(String result);
    void failure(String msg);
    void succeess(LadingBean ladingBean);

}
