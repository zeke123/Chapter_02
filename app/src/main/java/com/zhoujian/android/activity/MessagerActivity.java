package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.zhoujian.android.R;
import com.zhoujian.android.service.MessagerService;

/**
 * Created by zhoujian on 2016/11/16.
 */

public class MessagerActivity extends Activity
{

    public static final int MSG_FROM_CLIENT=1;

    ServiceConnection conn = new ServiceConnection()
    {

        private Messenger mMessenger;

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder)
        {

            //创建信使
            mMessenger = new Messenger(binder);

            //创建Message
            Message msg = Message.obtain(null,MSG_FROM_CLIENT);

            //创建Bundle
            Bundle bundle = new Bundle();

            //把消息放入binder
            bundle.putString("messager","你好！我来自客户端");

            //设置数据
            msg.setData(bundle);

            try
            {
                //用信使发送Mesage
                mMessenger.send(msg);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {

        }
    };

    //option+command+k  对生命周期方法进行排序
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messager);
        Intent intent = new Intent(MessagerActivity.this, MessagerService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        unbindService(conn);
        super.onDestroy();
    }
}
