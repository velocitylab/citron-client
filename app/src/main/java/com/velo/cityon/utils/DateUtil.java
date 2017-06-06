package com.velo.cityon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiho87.shin on 2017-05-08.
 */
public class DateUtil {

    private static final String DEFAUL_PATTERN = "HH:mm";
    private static SimpleDateFormat defaultDateFormat= new SimpleDateFormat(DEFAUL_PATTERN);

    public static String getDate(){
        Date date = new Date();
        return defaultDateFormat.format(date);
    }

}
