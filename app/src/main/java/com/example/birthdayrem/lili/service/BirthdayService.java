package com.example.birthdayrem.lili.service;

import android.content.Context;

import com.example.birthdayrem.lili.dao.IBirthdayDao;
import com.example.birthdayrem.lili.dao.impl.BirthdayDaoImpl;
import com.example.birthdayrem.lili.db.DBConnection;
import com.example.birthdayrem.lili.pojo.Birthday;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class BirthdayService implements IBirthdayDao {
    private DBConnection dbConnection;
    private BirthdayDaoImpl dao;

    public BirthdayService(Context context) throws Exception {
        dbConnection = new DBConnection(context);
        dao = new BirthdayDaoImpl(dbConnection.getDb());
    }
    @Override
    public long addBirthday(Birthday birthday) throws Exception {
        long _id = dao.addBirthday(birthday);
        dbConnection.dbClose();
        return _id;
    }

    @Override
    public List<Birthday> selectAllBirthday() throws Exception {
        List<Birthday> list = dao.selectAllBirthday();
        dbConnection.dbClose();
        return list;
    }

    @Override
    public Birthday selectOneBirthdayById(long _id) throws Exception {
        Birthday birthday = dao.selectOneBirthdayById(_id);
        dbConnection.dbClose();
        return birthday;
    }

    @Override
    public int updateBirthday(Birthday birthday) throws Exception {
        int count = dao.updateBirthday(birthday);
        dbConnection.dbClose();
        return count;
    }

    @Override
    public int deleteBirthday(long _id) throws Exception {
        int count = dao.deleteBirthday(_id);
        dbConnection.dbClose();
        return count;
    }
}
