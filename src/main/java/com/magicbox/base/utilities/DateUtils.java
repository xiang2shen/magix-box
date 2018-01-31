package com.magicbox.base.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magicbox.base.exception.BusinessException;

/**
 * 日期工具
 * 
 * @author xiangshuo
 *
 */
public final class DateUtils {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	private DateUtils() {}
	
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String TIME_PATTERN = "HH:mm:ss";
	
	/**
	 * 获取一天的起始时间，即yyyy-MM-dd 00:00:00.000
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartTimeOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取一天的结束时间，即yyyy-MM-dd 23:59:59.999
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndTimeOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * 格式日期,格式：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATE_PATTERN);
		
	}
	
	/**
	 * 格式日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 格式日期,格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return formatDateTime(date, DATE_TIME_PATTERN);
	}
	
	/**
	 * 格式日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDateTime(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 解析日期时间
	 * 
	 * @param source
	 * @return
	 */
	public static Date parseDateTime(String source) {
		return parseDate(source, DATE_TIME_PATTERN);
	}
	
	/**
	 * 解析日期
	 * 
	 * @param source
	 * @return
	 */
	public static Date parseDate(String source) {
		return parseDate(source, DATE_PATTERN);
	}
	
	/**
	 * 解析日期
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String source, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
			throw new BusinessException("解析日期异常, source=" + source, e);
		}
	}
	
	/**
	 * 获取指定日期开始n年后的日期(n可为负数)
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		return add(date, years, Calendar.YEAR);
	}
	
	/**
	 * 获取指定日期开始n月后的日期(n可为负数)
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		return add(date, months, Calendar.MONTH);
	}

	/**
	 * 获取指定日期开始n天后的日期(n可为负数)
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		return add(date, days, Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 获取指定日期开始n小时后的日期(n可为负数)
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHours(Date date, int hours) {
		return add(date, hours, Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获取指定日期开始n分钟后的日期(n可为负数)
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		return add(date, minutes, Calendar.MINUTE);
	}
	
	/**
	 * 获取指定日期开始n秒后的日期(n可为负数)
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds) {
		return add(date, seconds, Calendar.SECOND);
	}
	
	// 获取指定时间之前或之后的时间
	private static Date add(Date date, int value, int calendarType) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarType, value);
		return cal.getTime();
	}
	
	/**
	 * 获取日期时间
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, day);
		
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		
		return cal.getTime();
	}
	
	/**
	 * 获取日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		return getDate(year, month, day, 0, 0, 0, 0);
	}
	
	/**
	 * 获取日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
		return getDate(year, month, day, hour, minute, second, 0);
	}
	
	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/**
	 * 获取当年某月的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int month) {
		return getLastDayOfMonth(Calendar.getInstance().get(Calendar.YEAR), month);
	}
}
