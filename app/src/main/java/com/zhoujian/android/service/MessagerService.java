package com.zhoujian.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
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

        private Messenger mTo;
        private String  messager;

        @Override
       public void handleMessage(Message msg)
       {
           switch (msg.what)
           {
               case MSG_FROM_CLIENT:

                   messager = msg.getData().getString("messager");
                   Log.e(TAG,"接受的消息来自客户端："+messager);

                   //服务端收到客户端的消息后，在发送一条消息到客户端

                   mTo = msg.replyTo;

                   //创建Message

                   Message mMessae = Message.obtain(null,2);

                   Bundle bundle = new Bundle();

                   bundle.putString("reply","您的消息已经收到，稍后给您回复！");

                   mMessae.setData(bundle);

                   try
                   {
                       mTo.send(mMessae);
                   }
                   catch (Exception e)
                   {
                       e.printStackTrace();
                   }
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
