package com.example.meitu2.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time2StringUtils {
    public static String time2String(){
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        return sdf.format(date);
    }
    public static String generateFileName(String type){
        String name = time2String();
        return name + "." + type;
    }
}
