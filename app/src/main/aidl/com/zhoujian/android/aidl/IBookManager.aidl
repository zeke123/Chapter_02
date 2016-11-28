// IBookManager.aidl
package com.zhoujian.android.aidl;
import com.zhoujian.android.aidl.Book;

// Declare any non-default types here with import statements

interface IBookManager
{
    List<Book>  getBookList();
    void addBook(in Book book);
}
