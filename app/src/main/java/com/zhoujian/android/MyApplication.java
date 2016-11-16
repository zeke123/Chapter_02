package com.zhoujian.android;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.zhoujian.android.utils.ProcessNameUtil;

/**
 * Created by zhoujian on 2016/11/16.
 */

public class MyApplication extends Application
{


    public static final String TAG = "MyApplication";

    //同时选中修改变量 ：control+command+g
    private String processName;
    @Override
    public void onCreate() {
        super.onCreate();
        //抽取变量  option+command+f
        processName = ProcessNameUtil.getCurProcessName(getApplicationContext());
        Process.myPid();
        Log.e(TAG,"MyApplication start,process name:"+processName);


        //分别启动三个Activity  Application会重新创建
        //MyApplication start,process name:com.zhoujian.android
        //MyApplication start,process name:com.zhoujian.android:romote
        //MyApplication start,process name:com.zhoujian.android.romote
    }
}
