package com.zhoujian.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.zhoujian.android.R;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class FourthActivity extends Activity {


    //option+command+k   对生命
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fourth);

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
