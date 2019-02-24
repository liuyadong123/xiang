package com.example.dong.xiang.app;
import android.app.Application;
import android.content.Context;

import com.example.dong.xiang.net.Contstans;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import org.greenrobot.eventbus.EventBus;

public class App extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = this;
        //初始化各种第三方平台sdk
        CrashReport.initCrashReport(getApplicationContext(), "6f1c884600", true);
        UMConfigure.init(this,"5c371e8ff1f556ba330008d5"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }
    private static Context context;
    public static Context getContext() {
        return context;
    }
    }



