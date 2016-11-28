package com.zhoujian.android.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class BookManagerService extends Service
{


    //CopyOnWriteArrayList 支持并发读与写
    private CopyOnWriteArrayList<Book> mBooksList = new CopyOnWriteArrayList<Book>();

    private Binder mBinder = new IBookManager.Stub(){

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooksList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {

            mBooksList.add(book);

        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        mBooksList.add(new Book(1,"离散数学"));
        mBooksList.add(new Book(2,"操作系统"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
