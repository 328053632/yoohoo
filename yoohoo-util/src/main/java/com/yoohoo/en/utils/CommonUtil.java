package com.yoohoo.en.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonUtil
{
    public static String webappPath;

    /**
     * 获取临时文件目录
     */
    public static String getTempDir()
    {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int imonth = c.get(Calendar.MONTH) + 1;
        String timePath = "";
        if (imonth < 10)
        {
            timePath = year + "0" + imonth;
        }
        else
        {
            timePath = year + "" + imonth;
        }
        return webappPath  + File.separator + "temp" + File.separator + timePath + File.separator;
    }

    /**
     * 判断字符串是否为空
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNull(String str)
    {
        if (null == str || "".equals(str.trim()))
        {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNull(String str)
    {
        if (null != str && !"".equals(str.trim()))
        {
            return true;
        }
        return false;
    }

    /**
     * 判断列表是否为空
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param list
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmptyList(List<?> list)
    {
        if (null == list || list.size() == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * 日期格式转换 转为yyyy-MM-dd格式
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param dateStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String formatDate(String dateStr)
    {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = sdf.parse(dateStr);
            if (null != date)
            {
                str = sdf.format(date);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 日期加减
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param dateStr 日期字符串
     * @param num     天数
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String addDays(String dateStr, int num)
    {
        String str = "";
        if (isNull(dateStr))
        {
            return str;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = sdf.parse(dateStr);
            if (null != date)
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                //加减日期
                cal.add(Calendar.DATE, num);
                //格式化输出
                str = sdf.format(cal.getTime());
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 日期加减
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param dateStr 日期字符串
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date strToDate(String dateStr)
    {
        Date date = null;
        if (isNull(dateStr))
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            date = sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param date
     * @param pattern
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String formatDate(Date date, String pattern)
    {
        String str = "";
        if (null == date)
        {
            return str;
        }
        if (isNull(pattern))
        {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        str = sdf.format(date);
        return str;
    }

    /**
     * 日期格式转换 转为yyyy-MM-dd格式
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param dateStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date strToDate(String dateStr, String pattern)
    {
        Date date = null;
        if (isNull(pattern))
        {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try
        {
            //            Date date = sdf.parse(dateStr);
            date = sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期增加分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, Integer minutes)
    {
        try
        {
            if (null == date)
            {
                return null;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            //加减日期
            cal.add(Calendar.MINUTE, minutes);
            date = cal.getTime();
        }
        catch (Exception e)
        {
            date = null;
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期格式转换
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date dateFormat(Date date, String pattern)
    {
        if (isNull(pattern))
        {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try
        {
            String dateStr = sdf.format(date);
            date = sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
}
