package com.example.birthdayrem.lili.util;

/**
 * Created by Administrator on 2017/4/24.
 */

public class DateUtil {
    public static String formatDate(int year, int month, int day) {
        if (month < 10) {
            return year + "-" + ("0" + (month + 1)) + "-" + day;
        }
        if (day < 10) {
            return year + "-" + (month + 1) + "-" + ("0" + day);
        }
        if (month < 10 && day < 10) {
            return year + "-" + ("0" + (month + 1)) + "-" + ("0" + day);
        }
        return year + "-" + (month + 1) + "-" + day;
    }

}
