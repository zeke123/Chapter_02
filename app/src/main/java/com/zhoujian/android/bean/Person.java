package com.zhoujian.android.bean;

import java.io.Serializable;

/**
 * Created by zhoujian on 2016/11/16.
 */

public class Person implements Serializable
{

    private static final long serialVersionUID = -3911585324063896233L;

    //option+enter  自动生成serialVersionUID

    public int userid;

    public String userName;

    public  boolean isMale;


    //构造函数
    public Person(boolean isMale, int userid, String userName) {
        this.isMale = isMale;
        this.userid = userid;
        this.userName = userName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }


    @Override
    public String toString() {
        return "Person{" +
                "isMale=" + isMale +
                ", userid=" + userid +
                ", userName='" + userName + '\'' +
                '}';
    }
}
