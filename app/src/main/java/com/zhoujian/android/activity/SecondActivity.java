package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhoujian.android.R;
import com.zhoujian.android.UserManager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhoujian on 2016/11/16.
 */

public class SecondActivity extends Activity
{

    public static final String TAG = "SecondActivity";
    @InjectView(R.id.bt_start)
    Button mBtStart;


    private Object mObject;
    private boolean b;
    private Object mObject1;
    private Object mObject2;
    private String mObject3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);
        //在MainActivity中已经把UserManager.mUserId 赋值为2
        //但是在SeondActivity中UserManager.mUserId 值为1
        //这就是多进程使用带来的问题
        Log.e(TAG, "UserManager.mUserId ====" + UserManager.mUserId);
        initEvent();


        //Android Studio中常用的后缀补全
        //强制类型转换
        //mObject.cast
        mObject3 = ((String) mObject);

        //if语句
        // boolean.if
        //boolen.else
        if (b) {

        }
        if (!b) {

        }

        //field
        //new Object().field
        mObject2 = new Object();


        //fori
        //lists.fori
        //lists.for
        ArrayList<String> mLists = new ArrayList<String>();
        for (int i = 0; i < mLists.size(); i++)
        {

        }
        for (String list : mLists)
        {

        }


        //判null操作
        //mObject3.null
        //mObject3.nn
        if (mObject3 == null)
        {

        }
        if (mObject3 != null)
        {

        }


        //switch
        //objet.switch
        switch (4)
        {

        }

        //var
        //object.var
        Object object;
        Object object1 = null;


        //.log
        //Log.d(TAG, mObject3);


        //.toast
        //Toast.makeText(this, mObject3, Toast.LENGTH_SHORT).show();


    }

    private void initEvent()
    {
        mBtStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
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
