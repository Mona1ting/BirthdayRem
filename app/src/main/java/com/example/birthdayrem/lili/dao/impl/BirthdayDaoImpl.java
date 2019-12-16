package com.example.birthdayrem.lili.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.birthdayrem.lili.dao.IBirthdayDao;
import com.example.birthdayrem.lili.pojo.Birthday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class BirthdayDaoImpl implements IBirthdayDao {
    private static final String TABLE_NAME = "birthday";
    private SQLiteDatabase db;
    public BirthdayDaoImpl(SQLiteDatabase db){
        this.db = db;
    }
    @Override
    public long addBirthday(Birthday birthday) throws Exception {
        /**
         * 使用sqlite数据库的两种方式
         * 一套通过方法的参数执行sql语句的方法
         * 通过编写sql语句去执行
         * 参数1：数据表名
         * 参数2：哪一列为空，如果都插入的话，null
         * 参数3：键值对构成的ContentValues-->PrepardStatment里的占位符
         * 返回值：当前这一行的_id的值
         */
        ContentValues values = new ContentValues();
        values.put("name",birthday.getName());
        values.put("year",birthday.getYear());
        values.put("month",birthday.getMonth());
        values.put("day",birthday.getDay());

        long _id = db.insert(TABLE_NAME,null,values);
        return _id;
    }

    @Override
    public List<Birthday> selectAllBirthday() throws Exception {
        String sql = "select * from birthday";
        List<Birthday> list = new ArrayList<Birthday>();
        /**
         * 参数1：表名
         * 参数2：要查询的类名组成的数据
         * 参数3：where字句
         * 参数4：where字句占位符的值组成的数据
         * 参数5：groupby字句
         * 参数6：having字句
         * 参数7：排序
         * 返回值：cursor结果集
         */
        Cursor cursor = db.query(TABLE_NAME,new String[]{"_id","name","year","month","day"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Birthday birthday = new Birthday();
            birthday.set_id(cursor.getLong(0));
            birthday.setName(cursor.getString(1));
            birthday.setYear(cursor.getInt(2));
            birthday.setMonth(cursor.getInt(3));
            birthday.setDay(cursor.getInt(4));
            list.add(birthday);
        }
        cursor.close();
        return list;
    }

    @Override
    public Birthday selectOneBirthdayById(long _id) throws Exception {
        //select * from birthday where _id=?
        Cursor cursor = db.query(TABLE_NAME,new String[]{"_id","name","year","month","day"},"_id=?",new String[]{_id+""},null,null,null);
        Birthday birthday = new Birthday();
        while (cursor.moveToNext()){
            birthday.set_id(cursor.getLong(0));
            birthday.setName(cursor.getString(1));
            birthday.setYear(cursor.getInt(2));
            birthday.setMonth(cursor.getInt(3));
            birthday.setDay(cursor.getInt(4));

        }
        cursor.close();
        return birthday;
    }

    @Override
    public int updateBirthday(Birthday birthday) throws Exception {
        ContentValues values = new ContentValues();
        values.put("name",birthday.getName());
        values.put("year",birthday.getYear());
        values.put("month",birthday.getMonth());
        values.put("day",birthday.getDay());
        int count = db.update(TABLE_NAME,values,"_id=?",new String[]{birthday.get_id()+""});
        return count;
    }

    @Override
    public int deleteBirthday(long _id) throws Exception {
        int count = db.delete(TABLE_NAME,"_id=?",new String[]{_id+""});
        return count;
    }
}
