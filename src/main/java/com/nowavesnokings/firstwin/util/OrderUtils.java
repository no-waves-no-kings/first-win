package com.nowavesnokings.firstwin.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author ssx
 * @version V1.0
 * @className OrderUtils
 * @description 订单工具类
 * @date 2021-01-29 15:14
 * @since 1.8
 */
@Component
public class OrderUtils {

    public static String getOrderNo() {
        StringBuilder joiner = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        char c = (char) (calendar.get(Calendar.YEAR) - 2020 + 65);
        String millis = String.valueOf(System.currentTimeMillis());
        String micro = LocalDateTime.now().toString();
        String random = String.valueOf(Math.random() * 1000).substring(0, 2);
        return joiner.append(c)
                .append(Integer.toHexString(calendar.get(Calendar.MONTH) +1 ).toUpperCase())
                .append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(millis.substring(millis.length() - 5, millis.length() - 1))
                .append(micro.substring(micro.length() - 3, micro.length() - 1))
                .append(random).toString();
    }
}
