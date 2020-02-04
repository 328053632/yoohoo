package com.yoohoo.en.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

/**
 * 自定义日期工具类 <一句话功能简述> <功能详细描述>
 *
 * @author 姓名 工号
 * @version [版本号, 2014年12月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DateUtil
{

    private static GregorianCalendar calendar = new GregorianCalendar();

    /**
     * 缺省的日期字符串格式
     */
    public static final String DEFAULT_FORMAT = "yyyyMMddHHmmss";

    /**
     * 带连字符"-"精确到时间的字符串格式
     */
    public static final String HOR_SEC_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 带连字符"-"精确到天的字符串格式
     */
    public static final String HOR_DAY_FORMAT = "yyyy-MM-dd";

    /**
     * 不带连字符精确到天的字符串格式
     */
    public static final String NOHOR_DAY_FORMAT = "yyyyMMdd";

    /**
     * 不带连字符精确到天的字符串格式
     */
    public static final String NOHOR_DAY_FORMAT_DOT = "yyyy.MM.dd";

    /**
     * 手机报对账时间格式串
     */
    public static final String RECON_DAY_FORMAT = "yyyyMMdd-HH:mm:ss";

    /**
     * yyyyMMddHH
     */
    public static final String YEAR_MONTH_DAY_HOUR_FORMAT = "yyyyMMddHH";

    /**
     * MMdd
     */
    public static final String MONTH_DAY_FORMAT = "MMdd";

    /**
     * HH:mm
     */
    public static final String HOUR_SPLIT_MINUTE_FORMAT = "HH:mm";

    /**
     * 年月时间
     */
    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";

    /**
     * 年月时间
     */
    public static final String YEAR_MONTH_YYYYMM_FORMAT = "yyyyMM";

    /**
     * 只有年份的格式
     */
    public static final String YEAR_FORMAT = "yyyy";

    /**
     * 只有月份的格式
     */
    public static final String MONTH_FORMAT = "MM";

    /**
     * 日期转换格式字符串 <一句话功能简述> <功能详细描述>
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dateToString(Date date, String pattern)
    {
        if (null == date)
        {
            return "";
        }
        if (null == pattern || "".equals(pattern.trim()))
        {
            // 默认格式
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * Date 转秒
     *
     * @param date
     * @return
     */
    public static Integer dateToSecond(Date date)
    {
        if (date == null)
        {
            return null;
        }
        long t = date.getTime();
        return Integer.parseInt(String.valueOf(t / 1000));
    }

    /**
     * 秒转Date
     *
     * @param second
     * @return
     */
    public static Date secondToDate(int second)
    {
        long t = second * 1000l;
        return new Date(t);
    }

    public static Date parseDefault(String strDate)
    {
        return StringUtil.isEmpty(strDate) ? null : parse(strDate, HOR_DAY_FORMAT);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern)
    {
        if (StringUtil.isEmpty(strDate))
        {
            return null;
        }
        try
        {

            return new SimpleDateFormat(pattern).parse(strDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String[] labels = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

    public static Map<String, Date> dateMapper()
    {
        Map<String, Date> mapp = new HashMap<>(7);
        Date toDay = new Date();
        calendar.setTime(toDay);

        int dayWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        if (dayWeek == 1)
        {
            calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
        }

        calendar.setFirstDayOfWeek(GregorianCalendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);

        for (int i = 0; i < 7; i++)
        {
            mapp.put(labels[i], calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
        return mapp;
    }

    /**
     * 获取一天的开始时间
     *
     * @return
     */
    public static Date startWithDay(Date date)
    {
        return parse(dateToString(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    /**
     * 计算得到上一周, 下一周时间
     *
     * @param monday
     * @param sunday
     * @param type
     * @return
     */
    public static Map<String, Date> dateMapper(Date monday, Date sunday, int type)
    {
        Map<String, Date> mapp = new HashMap<>(7);
        if (type == 1)
        {
            Date time = monday;
            // 上一周
            for (int i = 6; i >= 0; i--)
            {
                time = DateUtils.addDays(time, -1);
                mapp.put(labels[i], time);
            }
        }
        else
        {
            // 下一周
            Date time = sunday;
            for (int i = 0; i < 7; i++)
            {
                time = DateUtils.addDays(time, 1);
                mapp.put(labels[i], time);
            }
        }
        return mapp;
    }
    
    /**
     * localDate 转 date
     * @param localDate
     * @return
     */
    public static Date LocalDateToDate(LocalDate localDate) {

        ZoneId zoneId = ZoneId.systemDefault();

        ChronoZonedDateTime<LocalDate> zonedDateTime = localDate.atStartOfDay(zoneId);

        return Date.from(zonedDateTime.toInstant());

    }
    
    /**
     * 	获取yyMMdd日期
     * @param localDate
     * @return
     */
    public static Integer getIntValueFromLocalDate(LocalDate localDate) {
        return Integer.parseInt(dateToString(LocalDateToDate(localDate), NOHOR_DAY_FORMAT));

    }
    
    /**
     * 	获取yyMMdd日期
     * @param localDate
     * @return
     */
    public static Integer getIntValueFromDate(Date date) {
        return Integer.parseInt(dateToString(date, NOHOR_DAY_FORMAT));
    }
}
