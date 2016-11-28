package com.zhoujian.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.zhoujian.android.aidl.Book;
import com.zhoujian.android.aidl.BookManagerService;
import com.zhoujian.android.aidl.IBookManager;

import java.util.List;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class BookManagerActivity extends Activity
{

    private ServiceConnection conn = new ServiceConnection()
    {
        private List<Book> mList;

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder)
        {

            IBookManager bookManager = IBookManager.Stub.asInterface(binder);

            try
            {
                mList = bookManager.getBookList();
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



    //对生命周期方法进行排序  optioncommand+k
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bookmanager);

        Intent intent = new Intent(BookManagerActivity.this, BookManagerService.class);

        bindService(intent,conn, Context.BIND_AUTO_CREATE);


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
