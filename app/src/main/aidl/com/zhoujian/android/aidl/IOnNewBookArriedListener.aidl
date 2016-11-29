// IOnNewBookArriedListener.aidl
package com.zhoujian.android.aidl;
import com.zhoujian.android.aidl.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArriedListener {
   void onNewBookArrived(in Book newBook);
}
