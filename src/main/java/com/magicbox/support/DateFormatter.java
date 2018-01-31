package com.magicbox.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {
	
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public String print(Date date, Locale locale) {
		return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		try {
			return new SimpleDateFormat(DATE_TIME_PATTERN).parse(text);
		} catch (ParseException e) {
			return new SimpleDateFormat(DATE_PATTERN).parse(text);
		}
	}

}
