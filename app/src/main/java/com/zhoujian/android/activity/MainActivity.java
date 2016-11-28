package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhoujian.android.R;
import com.zhoujian.android.UserManager;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
  2.1   Android IPC 简介

  含义：进程间通信或者跨进程通信，是指两个进程之间交换数据的过程

  线程与进程
  线程：是CPU调度的最小单元，同时线程也是一种有限的系统资源。
  进程：指一个执行单元，在PC端和移动设备上指的是一个程序或者一个应用。

  在Android中主线程也叫UI线程，在UI线程中才能操作界面元素
  Android中进程间通讯的方式就是Binder
  Android中对单个应用使用的内存做了限制，早期版本是16MB,不同的设备有不同的大小


  2.2 Android中多进程模式

  通过给四大组件指定android：process属性，我们可以轻易开启多进程模式

  使用多进程会有问题：
 （1）静态成员和单例模式失效
 （2）线程同步机制完全失效
 （3）SharedPreferences的可靠性下降
 （4）Application会多次创建

 2.3 IPC基础介绍


 Serializable和Parcelable接口可以完成对象的序列化过程
 当我们通过Intent和Binder传输数据时就需要使用Serializable和Parcelable，
 还有当我们需要把对象持久化到存储设备上或者通过网络传输给其他客户端，就要完成对象的序列化

 2.3.1 Serializable 接口

 2.3.2 Parcelable接口

 2.3.3 Binder

 2.4 Android中IPC的方式

 2.4.1 使用Bundle

 四大组件中的三大组件（Activity、Sevice、Receive）都支持在Intent中传递数据

 Bundle实现了Parcelable接口，所以可以在不同进程间传输

 2.4.2 使用文件共享实现IPC

 2.4.3 使用Messenger实现进程间通讯

 可以翻译为信使，通过它可以在不同进程中传递Message对象，底层是AIDL

 一次处理一个请求，不存在并发执行的情形




 */

public class MainActivity extends Activity
{

    @InjectView(R.id.bt_start)
    Button mBtStart;
    //option+command+k    生命周期排序
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //把mUserId赋值为2
        UserManager.mUserId = 2;
        clickEvent();
    }

    private void clickEvent()
    {
        mBtStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
