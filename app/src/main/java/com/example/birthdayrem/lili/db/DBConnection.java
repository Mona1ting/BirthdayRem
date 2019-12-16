package com.example.birthdayrem.lili.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/4/20.
 */

public class DBConnection {
    private SQLiteDatabase db;
    private MyDBHelper helper;
    private static final String DB_NAME = "birthday";
    private static final int DB_VERSION = 1;

    public DBConnection(Context context){
        helper = new MyDBHelper(context,DB_NAME,null,DB_VERSION);
        db = helper.getWritableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
    public void dbClose(){
        db.close();
    }
}
