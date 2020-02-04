package com.yoohoo.en.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {
	public static final long MINUTE = 60;
	public static final long HALF_HOUR = 60 * 30;
	public static final long HOUR = 60 * 60;
	public static final long ONE_DAY = 60 * 60 * 24;
	public static final long TWO_DAY = 60 * 60 * 24 * 2;

	public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> YYYYMMDDS = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_mm = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_mm_SS = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> YYYYMMDDHHmmSS = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> YMD = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy年M月d日");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> YMDHH_mm = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy年M月d日 HH:mm");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> MM_dd_HH_mm = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("MM-dd HH:mm");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> HH_mm_MM_DD = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm MM-dd");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> MM_DD = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("MM-dd");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> MD = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("M月d日");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> MDHH_mm = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("M月d日 HH:mm");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> HH_mm = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm");
		}
	};
	public static final ThreadLocal<SimpleDateFormat> HH_mm_SS = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss");
		}
	};

	/**
	 * 获取当前时间的日期-时 数据，即将当前的分钟、秒置为0后的数值, *
	 * 
	 * @param hour
	 *            要加减的小时数，如果为0表示当前小时
	 * @return
	 */
	public static long dateHourOfHour(int hour) {
		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(System.currentTimeMillis());
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.add(Calendar.HOUR_OF_DAY, hour);
		return today.getTimeInMillis() / 1000l;
	}

	public static long dateHourOfToday() {
		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(System.currentTimeMillis());
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return today.getTimeInMillis() / 1000l;
	}

	/**
	 * 获得某天0时0分0秒的秒数
	 * 
	 * @param day
	 *            今天就是0，昨天就是-1，明天就是1，由此类推
	 * @return
	 */
	public static long dateOfSomeDay(int day) {
		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(System.currentTimeMillis());
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.add(Calendar.DAY_OF_YEAR, day);
		return today.getTimeInMillis() / 1000l;
	}

	/**
	 * 获得某个时间对应的天（00：00：00）的秒数
	 * 
	 * @param
	 * @return
	 */
	public static long dateOfTime(long time) {
		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(time * 1000);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return today.getTimeInMillis() / 1000l;
	}

	/**
	 * 获取相对某个时间的某天的时间秒
	 * 
	 * @param time
	 *            初始化的某天
	 * @param day
	 *            相对某天的天数。前一天就是-1，后一天就是1，由此类推
	 * @return
	 */
	public static long dateOfTimeAndSomeDay(long time, int day) {
		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(time * 1000l);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.add(Calendar.DAY_OF_YEAR, day);
		return today.getTimeInMillis() / 1000l;
	}

	/**
	 * 获得今天0时0分0秒的秒数
	 * 
	 * @return
	 */
	public static long dateOfToday() {
		return dateOfSomeDay(0);
	}

	/**
	 * 1分钟内： 刚刚 半小时内： XX分钟前 今天： 今天**：**（时：分） 昨天： 昨天**：**（时：分） 前天： 前天**：**（时：分）
	 * 早于前天： mm月dd日 **：**（时：分） 早于今年： yyyy年mm月dd日 **：**（时：分）
	 * 
	 * @param displayTime
	 *            (单位:s)
	 * @param isShowHM
	 * @return
	 */
	public static String displayTime(long displayTime, boolean isShowHM) {
		Calendar now = Calendar.getInstance();
		Calendar show = Calendar.getInstance();
		show.setTimeInMillis(displayTime * 1000);
		Date mDownloadDate = show.getTime();
		long time = (now.getTimeInMillis() - mDownloadDate.getTime()) / 1000;
		if (time < (-5) * MINUTE) {
			return YMDHH_mm.get().format(mDownloadDate);
		}
		if (time < MINUTE) {
			return "刚刚";
		}
		if (time < HALF_HOUR) {
			return (time / 60) + "分钟前";
		}
		long todayPassTime = now.get(Calendar.HOUR_OF_DAY) * HOUR
				+ now.get(Calendar.MINUTE) * MINUTE + now.get(Calendar.SECOND);
		if (time < todayPassTime) {
			return "今天" + (isShowHM ? HH_mm.get().format(mDownloadDate) : "");
		}
		if (time < ONE_DAY + todayPassTime) {
			return "昨天" + (isShowHM ? HH_mm.get().format(mDownloadDate) : "");
		}
		if (time < TWO_DAY + todayPassTime) {
			return "前天" + (isShowHM ? HH_mm.get().format(mDownloadDate) : "");
		}
		SimpleDateFormat format = (isShowHM) ? YMDHH_mm.get() : YMD.get();
		if (now.get(Calendar.YEAR) == show.get(Calendar.YEAR)) {
			format = (isShowHM) ? MDHH_mm.get() : MD.get();
		}
		return format.format(mDownloadDate);
	}

	/**
	 * 1分钟内： 刚刚 半小时内： XX分钟前 今天： 今天**：**（时：分） 昨天： 昨天**：**（时：分） 前天： 前天**：**（时：分）
	 * 早于前天： mm月dd日 **：**（时：分）
	 * 
	 * @param displayTime
	 *            (单位:s)
	 * @param isShowHM
	 * @return
	 */
	public static String displayTime2(long displayTime, boolean isShowHM) {
		Calendar now = Calendar.getInstance();
		Calendar show = Calendar.getInstance();
		show.setTimeInMillis(displayTime * 1000);
		Date mDownloadDate = show.getTime();
		long time = (now.getTimeInMillis() - mDownloadDate.getTime()) / 1000;
		if (time < (-5) * MINUTE) {
			return YMDHH_mm.get().format(mDownloadDate);
		}
		if (time < MINUTE) {
			return "刚刚";
		}
		if (time < HALF_HOUR) {
			return (time / 60) + "分钟前";
		}
		long todayPassTime = now.get(Calendar.HOUR_OF_DAY) * HOUR
				+ now.get(Calendar.MINUTE) * MINUTE + now.get(Calendar.SECOND);
		if (time < todayPassTime) {
			return "今天" + (isShowHM ? HH_mm.get().format(mDownloadDate) : "");
		}
		if (time < ONE_DAY + todayPassTime) {
			return "昨天" + (isShowHM ? HH_mm.get().format(mDownloadDate) : "");
		}
		if (time < TWO_DAY + todayPassTime) {
			return "前天" + (isShowHM ? HH_mm.get().format(mDownloadDate) : "");
		}
		SimpleDateFormat format = (isShowHM) ? MDHH_mm.get() : MD.get();
		return format.format(mDownloadDate);
	}

	public static String format(long second, SimpleDateFormat format) {
		return format.format(second * 1000);
	}

	public static String formatDay(long second) {
		return format(second, YYYY_MM_DD_HH_mm_SS.get());
	}

	public static List<String> formatsBetween(long time1, long time2,
			SimpleDateFormat format) {
		long date1 = dateOfTime(time1 <= 0 ? getNow() : time1);
		long date2 = dateOfTime(time2 <= 0 ? getNow() : time2);
		if (date1 > date2) {
			long tmp = date2;
			date2 = date1;
			date1 = tmp;
		}
		List<String> dateList = GenericTools.createList();
		for (int i = 0; i <= (date2 - date1) / ONE_DAY; i++) {
			dateList.add(format.format((date1 + i * ONE_DAY) * 1000));
		}
		return dateList;
	}

	public static String formatShortDay(long second) {
		return format(second, YYYY_MM_DD.get());
	}

	public static List<String> formatShortDaysBetween(long time1, long time2) {
		return formatsBetween(time1, time2, YYYY_MM_DD.get());
	}

	public static String formatToChinaDayShort(long second) {
		return format(second, MD.get());
	}

	public static String formatToDay(long second) {
		return format(second, YYYY_MM_DD.get());
	}

	public static String formatToDayHour(long second) {
		return format(second, YYYY_MM_DD_HH.get());
	}

	public static String formatToDayShort(long second) {
		return format(second, MM_DD.get());
	}

	public static List<String> formatToDayShortsBetween(long time1, long time2) {
		return formatsBetween(time1, time2, MM_DD.get());
	}
	
	public static Date parseToDay(String time){
		try{
			return YYYY_MM_DD.get().parse(time);
		}catch(Exception e){
		}
		return new Date();
	}
	
	public static Date parseToDayM(String time){
		try{
			return YYYY_MM_DD_HH_mm.get().parse(time);
		}catch(Exception e){
		}
		return new Date();
	}

	/**
	 * 根据长整形的毫秒数转化成时间格式，精确到天
	 * 
	 * @param time
	 * @return
	 */
	public static String formatToDayWithEmpty(long time) {
		if (time <= 0) {
			return "";
		}
		return formatToDay(time);
	}

	public static String formatToMinute(long second) {
		return format(second, YYYY_MM_DD_HH_mm.get());
	}

	public static String formatToSecond(long second) {
		return format(second, YYYY_MM_DD_HH_mm_SS.get());
	}

	public static String formatToyyyyMMdd(long second) {
		return format(second, YYYYMMDDS.get());
	}

	public static String formatToyyyyMMddHHmmss(long second) {
		return format(second, YYYYMMDDHHmmSS.get());
	}

	public static String getCurFullTime() {
		return format(getNow(), YYYY_MM_DD_HH_mm_SS.get());
	}

	/**
	 * 获取两个时间之间的天数差, 参数单位为秒, 当time1>time2时返回值为正, 否则为负
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int getDaysBetween(long time1, long time2) {
		long date1 = dateOfTime(time1);
		long date2 = dateOfTime(time2);
		return (int) ((date1 - date2) / ONE_DAY);
	}

	public static long getNow() {
		return System.currentTimeMillis() / 1000l;
	}

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis() / 1000;
		System.out.println(displayTime(currentTimeMillis, true));
		System.out.println(displayTime(currentTimeMillis + 30, true));
		System.out.println(displayTime(currentTimeMillis + 60 * 2, true));
		System.out.println(displayTime(currentTimeMillis + 60 * 60, true));
		System.out.println(displayTime(currentTimeMillis + 60 * 60 * 2, true));
		System.out.println(displayTime(currentTimeMillis - 30, true));
		System.out.println(displayTime(currentTimeMillis - 60 * 2, true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60, true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 2, true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 24, true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 25, true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 24 * 2,
				true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 24 * 5,
				true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 24 * 30,
				true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 24 * 35,
				true));
		System.out.println(displayTime(currentTimeMillis - 60 * 60 * 24 * 30
				* 13, true));
		System.out.println("------------------------");
		System.out.println(displayTime2(currentTimeMillis, true));
		System.out.println(displayTime2(currentTimeMillis + 30, true));
		System.out.println(displayTime2(currentTimeMillis + 60 * 2, true));
		System.out.println(displayTime2(currentTimeMillis + 60 * 60, true));
		System.out.println(displayTime2(currentTimeMillis + 60 * 60 * 2, true));
		System.out.println(displayTime2(currentTimeMillis - 30, true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 2, true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 60, true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 60 * 2, true));
		System.out
				.println(displayTime2(currentTimeMillis - 60 * 60 * 24, true));
		System.out
				.println(displayTime2(currentTimeMillis - 60 * 60 * 25, true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 60 * 24 * 2,
				true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 60 * 24 * 5,
				true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 60 * 24 * 30,
				true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 60 * 24 * 35,
				true));
		System.out.println(displayTime2(currentTimeMillis - 60 * 60 * 24 * 30
				* 13, true));

		System.out.println(formatToDayWithEmpty(1369353600000l));

		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				+ ONE_DAY * 1));
		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				- ONE_DAY * 1));
		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				- ONE_DAY * 100));
		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				+ ONE_DAY * 100));
		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				+ ONE_DAY * 365));
		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				- ONE_DAY * 365));
		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				- ONE_DAY * 365 * 2));
		System.out.println(getDaysBetween(currentTimeMillis, currentTimeMillis
				+ ONE_DAY * 365 * 2));
	}

	public static long parse(String time, SimpleDateFormat format) {
		if (time == null) {
			return 0;
		}
		try {
			return format.parse(time).getTime() / 1000;
		} catch (ParseException e) {
			return 0;
		}
	}

	public static long parseDay(String day) {
		return parse(day, YYYY_MM_DD.get());
	}

	public static String subDay(String fulltime) {
		if (null != fulltime && !"".equals(fulltime.trim()) && fulltime.length() >= 10) {
			return fulltime.substring(0, 10);
		}
		return fulltime;
	}

	public static long parseDayWithNow(String day) {
		long date = parseDay(day);
		date = date <= 0 ? getNow() : date;
		return date;
	}

	public static long parseMinute(String minute) {
		return parse(minute, YYYY_MM_DD_HH_mm.get());
	}

	public static long parseSubDay(String time) {
		if (time == null) {
			return 0;
		}
		if (time.length() <= 5) {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			long daytime = parseDay(year + "-" + time);
			long diff = daytime - getNow();
			if (Math.abs(diff) <= ONE_DAY * 180) {
				return daytime;
			}
			year += (diff < 0) ? 1 : -1;
			return parseDay(year + "-" + time);
		}
		return parseDay(time);
	}

	public static long parseSubDayWithNow(String day) {
		long date = parseSubDay(day);
		date = date <= 0 ? getNow() : date;
		return date;
	}

	public static long parseTime(String time) {
		return parse(time, YYYY_MM_DD_HH_mm_SS.get());
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

}
