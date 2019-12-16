package com.example.birthdayrem.lili.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/20.
 * 数据库助手类
 */

public class MyDBHelper extends SQLiteOpenHelper {
    //参数1：上下文
    //参数2：数据库的名字
    //参数3：null
    //参数4：版本号
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /*
    当数据库不存在的时候，创建数据库时调用
    */


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table birthday(_id integer primary key autoincrement,name varchar(20),year integer,month integer,day integer)";
        sqLiteDatabase.execSQL(sql);

    }
    /*
    当数据库已经存在，版本号发生变化的时候
     */

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
