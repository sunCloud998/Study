package com.sfy.util.lang;

import java.util.Calendar;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private DateUtils() {
	}

	/**
	 * @功能: 根据毫秒数计算总时长
	 * flight
	 * @创建日期: 2015年1月30日 上午1:10:39
	 */
	public static String getDuration(long millis) {
		final long hourInMillis = 60 * 60 * 1000;
		final long minuteInMillis = 60 * 1000;
		final long secondInMillis = 1000;

		StringBuilder sb = new StringBuilder();
		if (millis > hourInMillis) {
			sb.append(millis / hourInMillis).append("小时");
			millis = millis % hourInMillis;
		}
		if (millis > minuteInMillis) {
			sb.append(millis / minuteInMillis).append("分");
			millis = millis % minuteInMillis;
		}
		if (millis > secondInMillis) {
			sb.append(millis / secondInMillis).append("秒");
		}
		return sb.toString();
	}

	/**
	 * @功能: 获取相差天数
	 * flight
	 * @创建日期: 2013-11-12 下午03:00:30
	 */
	public static long getOffsetDays(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		c1 = DateUtils.truncate(c1, Calendar.DATE);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		c2 = DateUtils.truncate(c2, Calendar.DATE);

		return Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60 * 60 * 24);
	}

	/**
	 * @功能: 指定日期中月份的最大天数
	 * flight
	 * @创建日期: 2013-11-12 下午03:44:16
	 */
	public static int getMaxDaysOfMonth(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @功能: 当前时间是否为白昼
	 * flight
	 * @创建日期: 2013-11-12 下午03:12:18
	 */
	public static boolean isDaytime() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if (hour >= 6 && hour <= 17) {
			return true;
		}
		return false;
	}

	/**
	 * @功能: 是否为闰年
	 * flight
	 * @创建日期: 2013-11-12 下午03:27:02
	 */
	public static boolean isLeapYear(int year) {
		if (year <= 0) {
			throw new IllegalArgumentException("The year must > 0");
		}
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public static void main(String[] args) {
		System.out.println(DateUtils.getDuration(7694080));
		System.out.println(DateUtils.getMaxDaysOfMonth(new Date()));
	}

}
