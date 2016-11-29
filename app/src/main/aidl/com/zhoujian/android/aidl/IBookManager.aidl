// IBookManager.aidl
package com.zhoujian.android.aidl;
import com.zhoujian.android.aidl.Book;
import com.zhoujian.android.aidl.IOnNewBookArriedListener;

// Declare any non-default types here with import statements

interface IBookManager
{
    List<Book>  getBookList();
    void addBook(in Book book);
    void registListener(IOnNewBookArriedListener listener);
    void unregistListener(IOnNewBookArriedListener listener);
}
