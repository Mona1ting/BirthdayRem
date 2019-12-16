package com.example.birthdayrem.lili.factory;

import android.content.Context;

import com.example.birthdayrem.lili.dao.IBirthdayDao;
import com.example.birthdayrem.lili.service.BirthdayService;

/**
 * Created by Administrator on 2017/4/20.
 */

public class BirthdayDaoFactory {
    public static IBirthdayDao getEmptyBirthdayDaoInstance(Context context)throws Exception {
        return new BirthdayService(context);
    }
}
