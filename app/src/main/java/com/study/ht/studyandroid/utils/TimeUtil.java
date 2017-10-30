package com.study.ht.studyandroid.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ttarfall
 * @date 2016-09-14 19:48
 */
public class TimeUtil {

    /**
     * 格式化到秒
     *
     * @param time
     * @return
     */
    public static String formatDateToDay(long time) {
        return formatDateToDay(new Date(time));
    }

    /**
     * 格式化到时分秒
     *
     * @param time
     * @return
     */
    public static String formatDateToHMS(long time) {
        return formatDateToHMS(new Date(time));
    }

    /**
     * 格式化date字符串日期到 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatStrToDateDay(String date) {
        try {
            if (!TextUtils.isEmpty(date)) {
                String r = "\\d{4}-\\d{1,2}-\\d{1,2}";
                Pattern p = Pattern.compile(r);
                Matcher matcher = p.matcher(date);
                if (matcher.find()) {
                    return matcher.group();
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date time = df.parse(date);
                return formatDateToDay(time);
            }
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 格式化date字符串日期到 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatStrToDateHM(String date) {
        try {
            if (!TextUtils.isEmpty(date)) {
                String r = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}";
                Pattern p = Pattern.compile(r);
                Matcher matcher = p.matcher(date);
                if (matcher.find()) {
                    return matcher.group();
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date time = df.parse(date);
                return formatDateToHMS(time);
            }
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 格式化date字符串日期到 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatStrToDateHMS(String date) {
        try {
            if (!TextUtils.isEmpty(date)) {
                String r = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}";
                Pattern p = Pattern.compile(r);
                Matcher matcher = p.matcher(date);
                if (matcher.find()) {
                    return matcher.group();
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date time = df.parse(date);
                return formatDateToHMS(time);
            }
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 格式化字符串yyyy-MM-dd HH:mm:ss日期到Date
     *
     * @param dateStr
     * @return
     */
    public static Date formatStrToDate(String dateStr) {
        try {
            if (!TextUtils.isEmpty(dateStr)) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = df.parse(dateStr);
                return date;
            }
        } catch (ParseException e) {
        }
        return null;
    }

    public static String formatDateToDay(Date date) {
        try {
            if (date != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.format(date);
            }
        } catch (Exception e) {

        }
        return "";
    }

    public static String formatDateToHMS(Date date) {
        try {
            if (date != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return df.format(date);
            }
        } catch (Exception e) {

        }
        return "";
    }


    /**
     * 格式化到毫秒
     *
     * @param time
     * @return
     */
    public static String formatDateToHMSS(long time) {
        return formatDateToHMSS(new Date(time));
    }

    public static String formatDateToHMSS(Date date) {
        try {
            if (date != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                return df.format(date);
            }
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 返回当前日期的0时，0分，0秒的时间
     *
     * @return
     */
    public static long getCurrentDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            calendar.set(year, month, day, 0, 0, 0);
            return calendar.getTimeInMillis();
        }
        return 0;
    }

    /**
     * 日期字符产格式如 yyyy-MM-dd
     * 比较日期A和日期B的大小，如果A>B返回1，如果A=B返回0，如果A<B返回-1 ，出错返回-2
     *
     * @param dateA
     * @param dateB
     * @return
     */
    public static int compareDateAB(String dateA, String dateB) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long a = df.parse(dateA).getTime();
            long b = df.parse(dateB).getTime();
            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return -2;
    }

    /**
     * 转化日期到 yyyy日MM月dd日
     *
     * @param date
     * @return
     */
    public static String getYYYYMMDD2(String date) {
        if (!TextUtils.isEmpty(date)) {
            String r = "\\d{4}年\\d{1,2}月\\d{1,2}日";
            Pattern p = Pattern.compile(r);
            Matcher matcher = p.matcher(date);
            if (matcher.find()) {
                return matcher.group();
            }
            r = "\\d{4}-\\d{1,2}-\\d{1,2}";
            p = Pattern.compile(r);
            matcher = p.matcher(date);
            String d = "";
            if (matcher.find()) {
                d = matcher.group();
            }
            if (!TextUtils.isEmpty(d)) {
                try {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date da = df.parse(d);
                    df = new SimpleDateFormat("yyyy年MM月dd日");
                    return df.format(da);
                } catch (Exception e) {
                }
            }
        }
        return "";
    }

    /**
     * 获取当前毫秒值的月份
     *
     * @param longTimes 时间毫秒值
     * @return
     */
    public static String getMM(String longTimes) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(longTimes));
            int month = calendar.get(Calendar.MONTH) + 1;
            return month + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前毫秒值的月份+年份
     *
     * @param longTimes 时间毫秒值
     * @return
     */
    public static String getMMDD(String longTimes) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(longTimes));
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DATE);
            return month + "-" + day;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前毫秒值的月份+年份
     *
     * @param longTimes 时间毫秒值
     * @return
     */
    public static String getYYMM(String longTimes) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(longTimes));
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            return year + "-" + month;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取日期字符串中的MM-DD
     *
     * @param date
     * @return
     */
    public static String getDateMMDD(String date) {
        if (!TextUtils.isEmpty(date)) {
            String r = "\\d{4}-\\d{2}-\\d{2}";
            Pattern p = Pattern.compile(r);
            Matcher matcher = p.matcher(date);
            if (matcher.find()) {
                date = matcher.group();
            }
            if (!TextUtils.isEmpty(date)) {
                int l = date.length();
                if (l > 5)
                    return date.substring(l - 5, l);
            }
        }
        return date;
    }

    /**
     * 格式化date字符串日期到 年月
     *
     * @param date
     * @return
     */
    public static String getDateYYYYMM(String date) {
        try {
            if (!TextUtils.isEmpty(date)) {
                String r = "\\d{4}-\\d{1,2}-\\d{1,2}";
                Pattern p = Pattern.compile(r);
                Matcher matcher = p.matcher(date);
                if (matcher.find()) {
                    return matcher.group();
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM");
                Date time = df.parse(date);
                return formatDateToDay(time);
            }
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 返回当前时间 格式yyyyMMddHHmmss
     * @return
     */
    public static String getNowDateYMdHms() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
