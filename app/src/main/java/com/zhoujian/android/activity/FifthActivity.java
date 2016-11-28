package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.zhoujian.android.R;
import com.zhoujian.android.bean.Person;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class FifthActivity extends Activity
{
    public static final String TAG = "FifthActivity";
    @InjectView(R.id.bt_start)
    Button mBtStart;

    //option+command+k   对生命周期方法进行排序
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        ButterKnife.inject(this);
        clickEvent();
        readFromFile();
    }


    private void readFromFile()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Person person = null;
                String path =  Environment.getExternalStorageDirectory().getPath()+"/cache/";
                File cacheFile = new File(path+"person.txt");
                if(cacheFile.exists())
                {
                    ObjectInputStream in=null;
                    try
                    {
                        in = new ObjectInputStream(new FileInputStream(cacheFile));
                        person = (Person)in.readObject();
                        Log.e(TAG,"person==="+person.toString());
                        in.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void clickEvent()
    {
        mBtStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(FifthActivity.this,MessagerActivity.class));
            }
        });
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
        super.onDestroy();
    }
}