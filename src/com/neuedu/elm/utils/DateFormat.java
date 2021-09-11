package com.neuedu.elm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
