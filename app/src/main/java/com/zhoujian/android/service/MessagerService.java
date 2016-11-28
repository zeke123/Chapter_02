package com.zhoujian.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;


/**
 * Created by zhoujian on 2016/11/28.
 */

public class MessagerService extends Service
{

    public static final String TAG = "MessagerService";

    public static final int MSG_FROM_CLIENT=1;

    Handler handler = new Handler()
    {

        private String  messager;

        @Override
       public void handleMessage(Message msg)
       {
           switch (msg.what)
           {
               case MSG_FROM_CLIENT:

                   messager = msg.getData().getString("messager");
                   Log.e(TAG,"接受的消息来自客户端："+messager);
           }
       }
    };

    Messenger mMessenger = new Messenger(handler);

    @Override
    public IBinder onBind(Intent intent)
    {
        return mMessenger.getBinder();
    }
}
