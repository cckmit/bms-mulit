/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.amigosoft.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期处理
 * 
 * @author Mark sunlightcs@gmail.com
 */
public class DateUtils {

    /**
     * 时间格式 (HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN_HH_MM_SS = "HH:mm:ss";

    /**
     * 时间格式(yyyy-MM)
     */
    public final static String DATE_MONTH_PATTERN = "yyyy-MM";
    /**
     * 时间格式(yyyyMM)
     */
    public final static String DATE_MONTH_PATTERN_NOT_LINE = "yyyyMM";

    /**
     * 时间格式(yyyyMMddHH)
     */
    public final static String DATE_HOUR_PATTERN = "yyyyMMddHH";


    /**
     * 时间格式(yyyyMMdd)
     */
    public final static String DATE_DAY_PATTERN = "yyyyMMdd";

    /**
     * 时间格式(yyyyMM)
     */
    public final static String YEAR_MONTH_PATTERN = "yyyyMM";

    /**
     * 时间格式(yyyy)
     */
    public final static String DATE_DAY_PATTERN_YEAR = "yyyy";

    /**
     * 时间格式(yyyy/MM/dd)
     */
    public final static String DATE_DAY_PATTERN_LINE = "yyyy/MM/dd";

    /**
     * 时间格式(yyyyMMddHHmmss)
     */
    public final static String CURRENT_DATETIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * 时间格式(yyyy年MM月dd日)
     */
    public final static String DATETIME_CHINESE_PATTERN = "yyyy年MM月dd日";

    /**
     * 时间格式(yyyy年MM月)
     */
    public final static String DATETIME_CHINESE_YYMM_PATTERN = "yyyy年MM月";

    /**
     * 时间格式 MM月dd日hh时mm分
     */
    public final static String DATETIME_CHINESE_MMDDHHMM_PATTERN = "MM月dd日HH时mm分";

    /**
     * 时间格式(HH)
     */
    public final static String CURRENT_HOUR_DATETIME_PATTERN = "HH";
    /*
     * 时间格式(dd)
     */
    public final static String DATE_PATTERN_DAY = "dd";

    public final static String DATE_PATTERN_MONTH = "MM";

    public final static String DATE_PATTERN_HH_MM_SS = "HHmmss";

    public final static String DATE_ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public final static String DATE_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";

    public final static String DATE_HOUR_MINUTE = "HH:mm";

	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期解析
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回Date
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    public static Map<String, String> getTodayWeekDate(String formatStr) {
        Map<String, String> todayWeekDate = new HashMap<>();
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(new Date());
        // 设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);

        boolean isFirstSunday = (calToday.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekToday = calToday.get(Calendar.DAY_OF_WEEK);
        if (isFirstSunday) {
            weekToday = weekToday - 1;
            if (weekToday == 0) {
                weekToday = 7;
            }
        }
        String today = sdf.format(calToday.getTime());
        todayWeekDate.put("dayInWeek", weekToday + "");
        todayWeekDate.put("todayDateStr", today);

        return todayWeekDate;
    }

}
