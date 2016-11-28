package com.zhoujian.android.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhoujian on 2016/11/28.
 */

public class User implements Parcelable
{

    public int userId;

    public String userName;

    public boolean isMalel;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeByte(this.isMalel ? (byte) 1 : (byte) 0);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.isMalel = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
