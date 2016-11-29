package com.zhoujian.android.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class BookManagerService extends Service
{

    public static  final String TAG = "BookManagerService";

    private AtomicBoolean mAtomicBoolean = new AtomicBoolean(false);

    //CopyOnWriteArrayList 支持并发读与写
    private CopyOnWriteArrayList<Book> mBooksList = new CopyOnWriteArrayList<Book>();

    private CopyOnWriteArrayList<IOnNewBookArriedListener> mListeners = new CopyOnWriteArrayList<IOnNewBookArriedListener>();

    private Binder mBinder = new IBookManager.Stub()
    {

        @Override
        public List<Book> getBookList() throws RemoteException
        {
            return mBooksList;
        }

        @Override
        public void addBook(Book book) throws RemoteException
        {

            mBooksList.add(book);

        }

        @Override
        public void registListener(IOnNewBookArriedListener listener) throws RemoteException
        {

            if (!mListeners.contains(listener))
            {
                mListeners.add(listener);
            }
            else
            {
                Log.d(TAG, "监听事件已存在");
            }

            Log.d(TAG, "监听事件的数量为:" + mListeners.size());
        }


        @Override
        public void unregistListener(IOnNewBookArriedListener listener) throws RemoteException
        {

            if (mListeners.contains(listener)) {

                mListeners.remove(listener);

                Log.d(TAG, "解除注册成功");

            }else{
                Log.d(TAG, "没有发现监听事件，不能注册");
            }
        }
    };

    @Override
    public void onCreate()
    {
        super.onCreate();
        mBooksList.add(new Book(1,"离散数学"));
        mBooksList.add(new Book(2,"操作系统"));

        //开启线程每5秒钟向书库中添加一本新书，并通知所有感兴趣的客户
        new Thread()
        {
            private int mInt;
            @Override
            public void run()
            {
                while (! mAtomicBoolean.get())
                {
                    try
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    mInt = mBooksList.size() + 1;
                    Book book = new Book(mInt,"新的书籍"+mInt);
                    try
                    {
                        onNewBookArrived(book);
                    }
                    catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void onNewBookArrived(Book book) throws RemoteException
    {
        mBooksList.add(book);
        for (int i = 0; i < mListeners.size(); i++)
        {
            IOnNewBookArriedListener mListener = mListeners.get(i);

            Log.d(TAG, "新书到啦");
            mListener.onNewBookArrived(book);
        }
    }


    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }


    @Override
    public void onDestroy()
    {
        mAtomicBoolean.set(true);
        super.onDestroy();
    }
}
