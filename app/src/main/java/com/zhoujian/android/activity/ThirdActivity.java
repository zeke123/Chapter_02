package com.zhoujian.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.zhoujian.android.R;

/**
 * Created by zhoujian on 2016/11/16.
 */

public class ThirdActivity  extends Activity
{

    //option+command+k  对生命周期方法进行排序
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
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
