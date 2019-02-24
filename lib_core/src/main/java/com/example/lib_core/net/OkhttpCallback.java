package com.example.lib_core.net;

public interface OkhttpCallback {
    void success(String result);
    void failure(String msg);
}
