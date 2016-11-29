package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhoujian.android.R;
import com.zhoujian.android.aidl.Book;
import com.zhoujian.android.aidl.BookManagerService;
import com.zhoujian.android.aidl.IBookManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class BookManagerActivity extends Activity
{

    public static final String TAG = "BookManagerActivity";
    @InjectView(R.id.bt_start)
    Button mBtStart;

    private ServiceConnection conn = new ServiceConnection() {
        private List<Book> mList1;
        private List<Book> mList;

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            IBookManager bookManager = IBookManager.Stub.asInterface(binder);
            try {
                mList = bookManager.getBookList();
                //返回的是类型   结构为：java.util.ArrayList
                mList.getClass().getCanonicalName();
                Log.d(TAG, "查询书籍的列表为：" + mList.toString());

                //在服务端在添加一本书

                Book book = new Book(3,"面向对象分析与设计");

                bookManager.addBook(book);

                mList1 = bookManager.getBookList();

                Log.d(TAG,"查询的书籍为：==="+mList1.toString());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    //对生命周期方法进行排序  optioncommand+k
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bookmanager);

        ButterKnife.inject(this);


        clickEvent();


    }

    private void clickEvent()
    {
        mBtStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(BookManagerActivity.this, BookManagerService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
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

        unbindService(conn);
        super.onDestroy();
    }
}
