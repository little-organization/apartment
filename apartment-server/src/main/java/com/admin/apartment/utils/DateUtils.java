package com.admin.apartment.utils;

import java.text.SimpleDateFormat;

public class DateUtils {

    /**
     * 日期样式
     * */
    public static String pattern = "";

    /**
     * 日期短格式
     * */
    public static SimpleDateFormat formatter;

    /**
     * 根据指定类型获取日期格式
     * @param type   指定的日期类型
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat DateUtils(int type){
        switch(type){
            case 0:
                //2003-05-19
                pattern = "yyyy-MM-dd";
                break;
            case 1:
                //2003-05-19 星期一
                pattern = "yyyy-MM-dd EEE";
                break;
            case 2:
                //16:09:31
                pattern = "HH_mm_ss";
                break;
            case 3:
                //2003-05-19 at 16:09:31
                pattern = "yyyy-MM-dd 'at' HH:mm:ss";
                break;
            case 4:
                //9月1日10:57
                pattern = "M'月'd'日'hh:mm";
                break;
            case 5:
                //2003-05-19 16:09:31
                pattern = "yyyy-MM-dd HH:mm:ss";
                break;
            case 6:
                //20030519160931 black/red list
                pattern = "yyyyMMddHHmmss";
                break;
            case 7:
                //20030519 black/red list
                pattern = "yyyyMMdd";
                break;
        }
        formatter = new SimpleDateFormat(pattern);
        return formatter;
    }

}
