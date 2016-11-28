package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhoujian.android.R;
import com.zhoujian.android.bean.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhoujian on 2016/11/16.
 */

public class ThirdActivity extends Activity {

    public static final String TAG = "ThirdActivity";
    @InjectView(R.id.bt_start)
    Button mBtStart;

    private File file;

    private File fullFilename;

    //option+command+k  对生命周期方法进行排序
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ButterKnife.inject(this);


        clickEvent();


        try {
            File extDir = Environment.getExternalStorageDirectory();
            String filename = "catch.txt";
            fullFilename = new File(extDir, filename);
            fullFilename.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //序列化过程
        try {
            if (sdCardExit()) {
                Person person = new Person(true, 0, "zhoujian");
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fullFilename.getAbsoluteFile()));
                out.writeObject(person);
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //反序列化过程
        try {
            if (sdCardExit()) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(fullFilename.getAbsoluteFile()));
                Person newPerson = (Person) in.readObject();
                Log.e(TAG, "反序列化成功-----newPerson===" + newPerson.toString());
                Toast.makeText(this, "反序列化成功-----newPerson===" + newPerson.toString(), Toast.LENGTH_SHORT).show();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clickEvent()
    {
        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
            }
        });

    }

    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    private static boolean sdCardExit() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
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
