package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import com.zhoujian.android.R;
import com.zhoujian.android.bean.Person;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class FourthActivity extends Activity
{

    @InjectView(R.id.bt_start)
    Button mBtStart;

    //option+command+k   对生命周期方法进行排序
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        ButterKnife.inject(this);
        clickEvent();
        //把数据写入文件中
        writeToFile();
    }

    private void writeToFile()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                Person person = new Person(true,2,"zhoujian");
                String path =  Environment.getExternalStorageDirectory().getPath()+"/cache/";
                File f = new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                File cacheFile = new File(path+"person.txt");
                ObjectOutputStream out=null;
                try
                {
                    out = new ObjectOutputStream(new FileOutputStream(cacheFile));
                    out.writeObject(person);
                    out.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void clickEvent()
    {
        mBtStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(FourthActivity.this,FifthActivity.class));
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
    protected void onResume() {
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
