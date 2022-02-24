package com.co.kafkapoc.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

/**
 * @author alobaton
 *
 */
public class DateUtil
{
	private DateUtil()
	{

	}

	public static final String ZONE_ID = "America/Bogota";
	public static final String DATE_PATTERN_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DATE_PATTERN_DD_MM_YYYY_WITH_SLASH = "dd/MM/yyyy";
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_PATTERN_HHMMSS = "HHmmss";
	public static final String DATE_PATTERN_HH_MM_SS = "HH:mm:ss";
	public static final String DATE_PATTERN_DD_MM_YYYY_HH_MM_SS_A = "dd-MM-yyyy hh:mm:ss a";
	public static final String DATE_PATTERN_DD_MM_YYYY_HH_MM_A = "dd-MM-yyyy hh:mm a";
	public static final String DATE_PATTERN_DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	public static final String DATE_PATTERN_DD_MM_YYYY_T_HH_MM_SS = "dd-MM-yyyy'T'HH:mm:ss";
	public static final String DATE_PATTERN_YYYY_MM_DD_T_HH_MM_SS_SSS_SS_S = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]";
	public static final String UTC_DATE_FORMAT_MS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
	public static final String HOURS = "hours";
	public static final String MINUTES = "minutes";

	private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN_DD_MM_YYYY_HH_MM_SS_A);
	private static final DateTimeFormatter LOCAL_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN_HH_MM_SS);
	private static final DateTimeFormatter UTC_DATE_FORMATTER_MS = DateTimeFormatter.ofPattern(UTC_DATE_FORMAT_MS).withZone(UTC_ZONE_ID);

	public static LocalDateTime toLocalDateTime(Date d)
	{
		return LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
	}

	public static LocalDateTime toLocalDateTime(String s, String pattern)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(s, formatter);
	}

	public static LocalDate toLocalDate(String s, String pattern)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(s, formatter);
	}

	public static String toString(LocalDateTime ldt)
	{
		return toString(ldt, DateUtil.DATE_PATTERN_DD_MM_YYYY_HH_MM_SS_A);
	}

	public static String toString(LocalDateTime ldt, String pattern)
	{
		return DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH).format(ldt);
	}

	public static String toString(LocalDate ld)
	{
		return toString(ld, DateUtil.DATE_PATTERN_DD_MM_YYYY);
	}

	public static String toString(LocalDate ld, String pattern)
	{
		return DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH).format(ld);
	}

	public static String formatLocalDate(TemporalAccessor datetime)
	{
		return LOCAL_DATE_FORMATTER.format(datetime);
	}

	public static String formatLocalTime(TemporalAccessor time)
	{
		return LOCAL_TIME_FORMATTER.format(time);
	}

	public static LocalDateTime parseLocalDate(String str)
	{
		return LOCAL_DATE_FORMATTER.parse(str, LocalDateTime::from);
	}

	public static LocalTime parseLocalTime(String str)
	{
		return LOCAL_TIME_FORMATTER.parse(str, LocalTime::from);
	}

	public static String formatUtcMillis(TemporalAccessor time)
	{
		return UTC_DATE_FORMATTER_MS.format(time);
	}

}