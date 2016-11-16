package com.zhoujian.android.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by zhoujian on 2016/11/16.
 */

public class ProcessNameUtil {

    public static String getCurProcessName(Context context)
    {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses())
        {
            if (appProcess.pid == pid)
            {
                return appProcess.processName;
            }
        }
        return null;
    }
}
