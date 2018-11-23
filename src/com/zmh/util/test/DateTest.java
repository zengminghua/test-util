package com.zmh.util.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * xxx
 *
 * @author zengminghua
 */
public class DateTest {

    public static final String INFLUXDB_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String args[]) {
        for (int i = 0; i < 1; i++) {
            List<String> dateList = getDateList(15);
            System.out.println(dateList.get(i).substring(0, 10));
        }
    }

    /**
     * 获取日期列表
     *
     * @return
     */
    public static List<String> getDateList(int dateLength) {

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(INFLUXDB_TIME_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - dateLength + 1);
        Date time = calendar.getTime();

        // 存储每天日期列表
        List<String> dateList = new ArrayList<>();
        for (int i = 0; i < dateLength; i++) {
            dateList.add(formatter.format(time));
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            time = calendar.getTime();
        }

        return dateList;
    }
}
