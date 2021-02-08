package com.nowavesnokings.firstwin.util;

import java.util.Date;

/**
 * @author ssx
 * @version V1.0
 * @className CommonUtils
 * @description 通用工具类
 * @date 2020-12-31 14:04
 * @since 1.8
 */
public class CommonUtils {
    private CommonUtils() {throw new IllegalStateException("CommonUtils class");}

    public static boolean isTimeInLine(Date date, Date start, Date end) {
        long dateTime = date.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();
        return startTime < dateTime && endTime >= dateTime;
    }

    public static Boolean isTimeExpired(Date now, Date expiredTime) {
        return expiredTime.getTime() < now.getTime();
    }
}
