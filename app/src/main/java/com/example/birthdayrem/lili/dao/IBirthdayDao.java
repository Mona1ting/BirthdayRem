package com.example.birthdayrem.lili.dao;


import com.example.birthdayrem.lili.pojo.Birthday;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public interface IBirthdayDao {
    public long addBirthday(Birthday birthday) throws Exception;
    public List<Birthday> selectAllBirthday() throws Exception;
    public Birthday selectOneBirthdayById(long _id) throws Exception;
    public int updateBirthday(Birthday birthday) throws Exception;
    public int deleteBirthday(long _id)throws Exception;
}
