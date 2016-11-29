package com.zhoujian.android.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import com.zhoujian.android.R;
import com.zhoujian.android.aidl.Book;
import com.zhoujian.android.aidl.BookManagerService;
import com.zhoujian.android.aidl.IBookManager;
import com.zhoujian.android.aidl.IOnNewBookArriedListener;

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

    private IBookManager mIBookManager;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what)
            {
                case 1:

                    Log.d(TAG, "添加的新书为:" + msg.obj);

                    break;

            }
        }
    };

    private ServiceConnection conn = new ServiceConnection() {
        private List<Book> mList1;
        private List<Book> mList;

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            IBookManager bookManager = IBookManager.Stub.asInterface(binder);
            try {

                mIBookManager= bookManager;
                mList = bookManager.getBookList();
                //返回的是类型   结构为：java.util.ArrayList
                mList.getClass().getCanonicalName();
                Log.d(TAG, "查询书籍的列表为：" + mList.toString());
                //在服务端在添加一本书
                Book book = new Book(3,"面向对象分析与设计");
                bookManager.addBook(book);
                mList1 = bookManager.getBookList();
                Log.d(TAG,"查询的书籍为：==="+mList1.toString());
                bookManager.registListener(mIOnNewBookArriedListener);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIBookManager=null;
        }
    };

    private IOnNewBookArriedListener mIOnNewBookArriedListener = new IOnNewBookArriedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(1,newBook).sendToTarget();
        }
    };


    //对生命周期方法进行排序  optioncommand+k
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bookmanager);

        ButterKnife.inject(this);
        Intent intent = new Intent(BookManagerActivity.this, BookManagerService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

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

        if (mIBookManager != null && mIBookManager.asBinder().isBinderAlive()) {
            try {
                mIBookManager.unregistListener(mIOnNewBookArriedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(conn);
        super.onDestroy();
    }
}
