package com.wer.parking.utlis;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


/**
 * 字符串转时间工具类
 *
 */
@Component
public class TimeUtils {

    /** 字符串转时间 **/
    public static Date getDate(String param){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /** 当前时间转时间戳 秒**/
    public static long getTimestamp(){
        Instant now = Instant.now();
        return now.getEpochSecond();
    }

    /** 当前时间转时间戳 毫秒**/
    public static long getTimestampMs(){
        Instant now = Instant.now();
        return now.toEpochMilli();
    }

    /**
     * 获取当天最小数据
     */
    public static String getMinTime(){
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(today_start));
        return df.format(today_start);
    }

    /**
     * 获取当天最大数据
     */
    public static String getMaxTime(){
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(today_start));
        return df.format(today_start);
    }

    /**
     * 获取当天数据
     */
    public static String getDayTime(){
        String format = "YYYY-MM-dd";
        String formatDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        return formatDateTime;
    }

    /**
     * 获取本月的最小天数
     */
    public static String getMonthMinTime(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime firstday = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        return firstday.format(fmt);
    }

    /**
     * 获取本月的最大天数
     */
    public static String getMonthMaxTime(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime lastDay = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        return lastDay.format(fmt);
    }

    /**
     * 获取当前时间前1分钟或后1分钟
     */
    public static String getCurrentTime(String param){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, Integer.valueOf(param));
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 获取当前时间前7天
     */
    public static String getDayTime(String param){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.DAY_OF_WEEK, Integer.valueOf(param));
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * java获取当前月的天数
     * @return
     */
    public static int getDayOfMonth(){
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day=aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * 获取两个日期之间的所有日期
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取当月所有天
     * @return
     */
    public static List<String> getDayListOfMonth() {
        List<String> list = new ArrayList<String>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = String.valueOf(year)+"-"+month+"-"+i;
            list.add(aDate);
        }
        return list;
    }
    /**
     * 获得月
     * @return
     */
    public static List<String> getMonth() {
        List<String> list = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        for(int i = 1; i < 13; i ++){
            int k = c.get(Calendar.YEAR);
            String  date= "";
            if(i<10){
                date = "0"+i;
            }else{
                date = String.valueOf(i);
            }
            list.add(date);
        }
        return list;
    }
}
