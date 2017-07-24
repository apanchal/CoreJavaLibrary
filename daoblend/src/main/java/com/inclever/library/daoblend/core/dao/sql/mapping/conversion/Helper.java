package com.inclever.library.daoblend.core.dao.sql.mapping.conversion;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.inclever.library.common.StringPool;
import com.inclever.library.daoblend.exception.DaoConversionException;

public class Helper {

	/**
	 * PERF: Used to cache a set of calendars for conversion/printing purposes.
	 */
	protected static Queue<Calendar> calendarCache = initCalendarCache();

	/** PERF: Cache default timezone for calendar conversion. */
	protected static TimeZone defaultTimeZone = TimeZone.getDefault();

	/**
	 * PERF: This is used to optimize Calendar conversion/printing. This should
	 * only be used when a calendar is temporarily required, when finished it
	 * must be released back.
	 */
	public static Calendar allocateCalendar() {
		Calendar calendar = getCalendarCache().poll();
		if (calendar == null) {
			calendar = Calendar.getInstance();
		}
		return calendar;
	}

	/**
	 * PERF: Return the calendar cache use to avoid calendar creation for
	 * processing java.sql/util.Date/Time/Timestamp objects.
	 */
	public static Queue<Calendar> getCalendarCache() {
		return calendarCache;
	}

	/**
	 * PERF: Init the calendar cache use to avoid calendar creation for
	 * processing java.sql/util.Date/Time/Timestamp objects.
	 */

	public static Queue<Calendar> initCalendarCache() {
		Queue<Calendar> calendarCache = new ConcurrentLinkedQueue<Calendar>();
		for (int index = 0; index < 10; index++) {
			calendarCache.add(Calendar.getInstance());
		}
		return calendarCache;
	}

	/**
	 * PERF: Return the cached default platform. Used for ensuring Calendar are
	 * in the local timezone. The JDK method clones the timezone, so cache it
	 * locally.
	 */
	public static TimeZone getDefaultTimeZone() {
		return defaultTimeZone;
	}

	/**
	 * Answer a Calendar from a date.
	 */
	public static Calendar calendarFromUtilDate(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// In jdk1.3, millisecond is missing
		if (date instanceof Timestamp) {
			calendar.set(Calendar.MILLISECOND, ((Timestamp) date).getNanos() / 1000000);
		}
		return calendar;
	}

	/**
	 * Answer a Date from a timestamp
	 *
	 * This implementation is based on the java.sql.Date class, not
	 * java.util.Date.
	 * 
	 * @param timestampObject
	 *            - timestamp representation of date
	 * @return - date representation of timestampObject
	 */
	public static java.sql.Date dateFromTimestamp(java.sql.Timestamp timestamp) {
		return sqlDateFromUtilDate(timestamp);
	}

	/**
	 * Answer a sql.Date from a timestamp.
	 */
	public static java.sql.Date sqlDateFromUtilDate(java.util.Date utilDate) {
		// PERF: Avoid deprecated get methods, that are now very inefficient.
		Calendar calendar = allocateCalendar();
		calendar.setTime(utilDate);
		java.sql.Date date = dateFromCalendar(calendar);
		releaseCalendar(calendar);
		return date;
	}

	/**
	 * Answer a sql.Date from a Calendar.
	 */
	public static java.sql.Date dateFromCalendar(Calendar calendar) {
		if (!defaultTimeZone.equals(calendar.getTimeZone())) {
			// Must convert the calendar to the local timezone if different, as
			// dates have no timezone (always local).
			Calendar localCalendar = allocateCalendar();
			localCalendar.setTimeInMillis(calendar.getTimeInMillis());
			java.sql.Date date = dateFromYearMonthDate(localCalendar.get(Calendar.YEAR),
					localCalendar.get(Calendar.MONTH), localCalendar.get(Calendar.DATE));
			releaseCalendar(localCalendar);
			return date;
		} else if ((calendar.get(Calendar.HOUR_OF_DAY) == 0) && (calendar.get(Calendar.MINUTE) == 0)
				&& (calendar.get(Calendar.SECOND) == 0) && (calendar.get(Calendar.MILLISECOND) == 0)) {
			// PERF: If just a date set in the Calendar, then just use its
			// millis.
			return new java.sql.Date(calendar.getTimeInMillis());
		}
		return dateFromYearMonthDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE));
	}

	/**
	 * Answer a Date with the year, month, date. This builds a date avoiding the
	 * deprecated, inefficient and concurrency bottleneck date constructors.
	 * This implementation is based on the java.sql.Date class, not
	 * java.util.Date. The year, month, day are the values calendar uses, i.e.
	 * year is from 0, month is 0-11, date is 1-31.
	 */
	public static java.sql.Date dateFromYearMonthDate(int year, int month, int day) {
		// Use a calendar to compute the correct millis for the date.
		Calendar localCalendar = allocateCalendar();
		localCalendar.clear();
		localCalendar.set(year, month, day, 0, 0, 0);
		long millis = localCalendar.getTimeInMillis();
		java.sql.Date date = new java.sql.Date(millis);
		releaseCalendar(localCalendar);
		return date;
	}

	/**
	 * Answer a Date from a string representation. The string MUST be a valid
	 * date and in one of the following formats: YYYY/MM/DD, YYYY-MM-DD,
	 * YY/MM/DD, YY-MM-DD.
	 *
	 * This implementation is based on the java.sql.Date class, not
	 * java.util.Date.
	 *
	 * The Date class contains some minor gotchas that you have to watch out
	 * for.
	 * 
	 * @param dateString
	 *            - string representation of date
	 * @return - date representation of string
	 */
	public static java.sql.Date dateFromString(String dateString) throws DaoConversionException {
		int year;
		int month;
		int day;
		StringTokenizer dateStringTokenizer;

		if (dateString.indexOf('/') != -1) {
			dateStringTokenizer = new StringTokenizer(dateString, "/");
		} else if (dateString.indexOf('-') != -1) {
			dateStringTokenizer = new StringTokenizer(dateString, "- ");
		} else {
			throw DaoConversionException.incorrectDateFormat(dateString);
		}

		try {
			year = Integer.parseInt(dateStringTokenizer.nextToken());
			month = Integer.parseInt(dateStringTokenizer.nextToken());
			day = Integer.parseInt(dateStringTokenizer.nextToken());
		} catch (NumberFormatException exception) {
			throw DaoConversionException.incorrectDateFormat(dateString);
		}

		// Java returns the month in terms of 0 - 11 instead of 1 - 12.
		month = month - 1;

		return dateFromYearMonthDate(year, month, day);
	}

	/**
	 * Answer a Date from a long
	 *
	 * This implementation is based on the java.sql.Date class, not
	 * java.util.Date.
	 * 
	 * @param longObject
	 *            - milliseconds from the epoch (00:00:00 GMT Jan 1, 1970).
	 *            Negative values represent dates prior to the epoch.
	 */
	public static java.sql.Date dateFromLong(Long longObject) {
		return new java.sql.Date(longObject.longValue());
	}

	/**
	 * Answer a Timestamp from a java.util.Date.
	 */
	public static java.sql.Timestamp timestampFromDate(java.util.Date date) {
		return timestampFromLong(date.getTime());
	}

	/**
	 * Answer a Time from a long
	 *
	 * @param longObject
	 *            - milliseconds from the epoch (00:00:00 GMT Jan 1, 1970).
	 *            Negative values represent dates prior to the epoch.
	 */
	public static java.sql.Timestamp timestampFromLong(Long millis) {
		return timestampFromLong(millis.longValue());
	}

	/**
	 * Print the sql.Timestamp.
	 */
	public static String printTimestamp(java.sql.Timestamp timestamp) {
		// PERF: Avoid deprecated get methods, that are now very inefficient and
		// used from toString.
		Calendar calendar = allocateCalendar();
		calendar.setTime(timestamp);

		String nanosString;

		if (timestamp.getNanos() == 0) {
			nanosString = "0";
		} else {
			nanosString = buildZeroPrefixAndTruncTrailZeros(timestamp.getNanos(), 9);
		}

		StringBuffer timestampBuf = new StringBuffer();
		timestampBuf.append(printDate(calendar));
		timestampBuf.append(" ");
		timestampBuf.append(printTime(calendar));
		timestampBuf.append(".");
		timestampBuf.append(nanosString);

		releaseCalendar(calendar);

		return timestampBuf.toString();
	}

	/**
	 * Print the Calendar.
	 */
	public static String printCalendar(Calendar calendar) {
		return printCalendar(calendar, true);
	}

	/**
	 * Print the Calendar. Normally the calendar must be printed in the local
	 * time, but if the timezone is printed, it must be printing in its
	 * timezone.
	 */
	public static String printCalendar(Calendar calendar, boolean useLocalTime) {
		String millisString;

		if (calendar.get(Calendar.MILLISECOND) == 0) {
			millisString = "0";
		} else {
			millisString = buildZeroPrefixAndTruncTrailZeros(calendar.get(Calendar.MILLISECOND), 3);
		}

		StringBuffer timestampBuf = new StringBuffer();
		timestampBuf.append(printDate(calendar, useLocalTime));
		timestampBuf.append(StringPool.SPACE);
		timestampBuf.append(printTime(calendar, useLocalTime));
		timestampBuf.append(StringPool.DOT);
		timestampBuf.append(millisString);

		return timestampBuf.toString();
	}

	/**
	 * Print the sql.Date.
	 */
	public static String printDate(java.sql.Date date) {
		// PERF: Avoid deprecated get methods, that are now very inefficient and
		// used from toString.
		Calendar calendar = allocateCalendar();
		calendar.setTime(date);
		String string = printDate(calendar);
		releaseCalendar(calendar);
		return string;
	}

	/**
	 * Print the date part of the calendar.
	 */
	public static String printDate(Calendar calendar) {
		return printDate(calendar, true);
	}

	/**
	 * Print the date part of the calendar. Normally the calendar must be
	 * printed in the local time, but if the timezone is printed, it must be
	 * printing in its timezone.
	 */
	public static String printDate(Calendar calendar, boolean useLocalTime) {
		int year;
		int month;
		int day;
		if (useLocalTime && (!defaultTimeZone.equals(calendar.getTimeZone()))) {
			// Must convert the calendar to the local timezone if different, as
			// dates have no timezone (always local).
			Calendar localCalendar = allocateCalendar();
			localCalendar.setTimeInMillis(calendar.getTimeInMillis());
			year = localCalendar.get(Calendar.YEAR);
			month = localCalendar.get(Calendar.MONTH) + 1;
			day = localCalendar.get(Calendar.DATE);
			releaseCalendar(localCalendar);
		} else {
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			day = calendar.get(Calendar.DATE);
		}

		char[] buf = "2000-00-00".toCharArray();
		buf[0] = Character.forDigit(year / 1000, 10);
		buf[1] = Character.forDigit((year / 100) % 10, 10);
		buf[2] = Character.forDigit((year / 10) % 10, 10);
		buf[3] = Character.forDigit(year % 10, 10);
		buf[5] = Character.forDigit(month / 10, 10);
		buf[6] = Character.forDigit(month % 10, 10);
		buf[8] = Character.forDigit(day / 10, 10);
		buf[9] = Character.forDigit(day % 10, 10);

		return new String(buf);
	}

	/**
	 * Print the sql.Time.
	 */
	public static String printTime(java.sql.Time time) {
		// PERF: Avoid deprecated get methods, that are now very inefficient and
		// used from toString.
		Calendar calendar = allocateCalendar();
		calendar.setTime(time);
		String string = printTime(calendar);
		releaseCalendar(calendar);
		return string;
	}

	/**
	 * Print the time part of the calendar.
	 */
	public static String printTime(Calendar calendar) {
		return printTime(calendar, true);
	}

	/**
	 * Print the time part of the calendar. Normally the calendar must be
	 * printed in the local time, but if the timezone is printed, it must be
	 * printing in its timezone.
	 */
	public static String printTime(Calendar calendar, boolean useLocalTime) {
		int hour;
		int minute;
		int second;
		if (useLocalTime && (!defaultTimeZone.equals(calendar.getTimeZone()))) {
			// Must convert the calendar to the local timezone if different, as
			// dates have no timezone (always local).
			Calendar localCalendar = allocateCalendar();
			localCalendar.setTimeInMillis(calendar.getTimeInMillis());
			hour = localCalendar.get(Calendar.HOUR_OF_DAY);
			minute = localCalendar.get(Calendar.MINUTE);
			second = localCalendar.get(Calendar.SECOND);
			releaseCalendar(localCalendar);
		} else {
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
		}
		String hourString;
		String minuteString;
		String secondString;
		if (hour < 10) {
			hourString = "0" + hour;
		} else {
			hourString = Integer.toString(hour);
		}
		if (minute < 10) {
			minuteString = "0" + minute;
		} else {
			minuteString = Integer.toString(minute);
		}
		if (second < 10) {
			secondString = "0" + second;
		} else {
			secondString = Integer.toString(second);
		}
		return hourString.concat(StringPool.COLON).concat(minuteString).concat(StringPool.COLON).concat(secondString);
	}

	/**
	 * Build a numerical string with leading 0s and truncate trailing zeros.
	 * number is an existing number that the new string will be built on.
	 * totalDigits is the number of the required digits of the string.
	 */
	public static String buildZeroPrefixAndTruncTrailZeros(int number, int totalDigits) {
		String zeros = "000000000";
		String numbString = Integer.toString(number);

		// Add leading zeros
		numbString = zeros.substring(0, (totalDigits - numbString.length())) + numbString;
		// Truncate trailing zeros
		char[] numbChar = new char[numbString.length()];
		numbString.getChars(0, numbString.length(), numbChar, 0);
		int truncIndex = totalDigits - 1;
		while (numbChar[truncIndex] == '0') {
			truncIndex--;
		}
		return new String(numbChar, 0, truncIndex + 1);
	}

	/**
	 * Answer a Time from a string representation. This method will accept times
	 * in the following formats: HH-MM-SS, HH:MM:SS
	 *
	 * @param timeString
	 *            - string representation of time
	 * @return - time representation of string
	 */
	public static java.sql.Time timeFromString(String timeString) throws DaoConversionException {
		int hour;
		int minute;
		int second;
		String timePortion = timeString;

		if (timeString.length() > 12) {
			// Longer strings are Timestamp format (ie. Sybase & Oracle)
			timePortion = timeString.substring(11, 19);
		}

		if ((timePortion.indexOf('-') == -1) && (timePortion.indexOf('/') == -1) && (timePortion.indexOf('.') == -1)
				&& (timePortion.indexOf(':') == -1)) {
			throw DaoConversionException.incorrectTimeFormat(timePortion);
		}
		StringTokenizer timeStringTokenizer = new StringTokenizer(timePortion, " /:.-");

		try {
			hour = Integer.parseInt(timeStringTokenizer.nextToken());
			minute = Integer.parseInt(timeStringTokenizer.nextToken());
			second = Integer.parseInt(timeStringTokenizer.nextToken());
		} catch (NumberFormatException exception) {
			throw DaoConversionException.incorrectTimeFormat(timeString);
		}

		return timeFromHourMinuteSecond(hour, minute, second);
	}

	/**
	 * Answer a Time with the hour, minute, second. This builds a time avoiding
	 * the deprecated, inefficient and concurrency bottleneck date constructors.
	 * The hour, minute, second are the values calendar uses, i.e. year is from
	 * 0, month is 0-11, date is 1-31.
	 */
	public static java.sql.Time timeFromHourMinuteSecond(int hour, int minute, int second) {
		// Use a calendar to compute the correct millis for the date.
		Calendar localCalendar = allocateCalendar();
		localCalendar.clear();
		localCalendar.set(1970, 0, 1, hour, minute, second);
		long millis = localCalendar.getTimeInMillis();
		java.sql.Time time = new java.sql.Time(millis);
		releaseCalendar(localCalendar);
		return time;
	}

	/**
	 * Answer a java.util.Date from a sql.date
	 *
	 * @param sqlDate
	 *            - sql.date representation of date
	 * @return - java.util.Date representation of the sql.date
	 */
	public static java.util.Date utilDateFromSQLDate(java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}

	/**
	 * Answer a java.util.Date from a sql.Time
	 *
	 * @param time
	 *            - time representation of util date
	 * @return - java.util.Date representation of the time
	 */
	public static java.util.Date utilDateFromTime(java.sql.Time time) {
		return new java.util.Date(time.getTime());
	}

	/**
	 * Answer a Date from a long
	 *
	 * This implementation is based on the java.sql.Date class, not
	 * java.util.Date.
	 * 
	 * @param longObject
	 *            - milliseconds from the epoch (00:00:00 GMT Jan 1, 1970).
	 *            Negative values represent dates prior to the epoch.
	 */
	public static java.util.Date utilDateFromLong(Long longObject) {
		return new java.util.Date(longObject.longValue());
	}

	/**
	 * Answer a Timestamp from a Calendar.
	 */
	public static java.sql.Timestamp timestampFromCalendar(Calendar calendar) {
		return timestampFromLong(calendar.getTimeInMillis());
	}

	/**
	 * Answer a Time from a Date
	 *
	 * This implementation is based on the java.sql.Date class, not
	 * java.util.Date.
	 * 
	 * @param timestampObject
	 *            - time representation of date
	 * @return - time representation of dateObject
	 */
	public static java.sql.Time timeFromDate(java.util.Date date) {
		// PERF: Avoid deprecated get methods, that are now very inefficient.
		Calendar calendar = allocateCalendar();
		calendar.setTime(date);
		java.sql.Time time = timeFromCalendar(calendar);
		releaseCalendar(calendar);
		return time;
	}

	/**
	 * Answer a Time from a long
	 *
	 * @param longObject
	 *            - milliseconds from the epoch (00:00:00 GMT Jan 1, 1970).
	 *            Negative values represent dates prior to the epoch.
	 */
	public static java.sql.Time timeFromLong(Long longObject) {
		return new java.sql.Time(longObject.longValue());
	}

	/**
	 * Answer a sql.Time from a Calendar.
	 */
	public static java.sql.Time timeFromCalendar(Calendar calendar) {
		if (!defaultTimeZone.equals(calendar.getTimeZone())) {
			// Must convert the calendar to the local timezone if different, as
			// dates have no timezone (always local).
			Calendar localCalendar = allocateCalendar();
			localCalendar.setTimeInMillis(calendar.getTimeInMillis());
			java.sql.Time date = timeFromHourMinuteSecond(localCalendar.get(Calendar.HOUR_OF_DAY),
					localCalendar.get(Calendar.MINUTE), localCalendar.get(Calendar.SECOND));
			releaseCalendar(localCalendar);
			return date;
		}
		return timeFromHourMinuteSecond(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND));
	}

	/**
	 * Answer a Time from a Timestamp Usus the Hours, Minutes, Seconds instead
	 * of getTime() ms value.
	 */
	public static java.sql.Time timeFromTimestamp(java.sql.Timestamp timestamp) {
		return timeFromDate(timestamp);
	}

	/**
	 * Answer a Timestamp from a string representation. This method will accept
	 * strings in the following formats: YYYY/MM/DD HH:MM:SS, YY/MM/DD HH:MM:SS,
	 * YYYY-MM-DD HH:MM:SS, YY-MM-DD HH:MM:SS
	 *
	 * @param timestampString
	 *            - string representation of timestamp
	 * @return - timestamp representation of string
	 */
	@SuppressWarnings("deprecation")
	public static java.sql.Timestamp timestampFromString(String timestampString) throws DaoConversionException {
		if ((timestampString.indexOf('-') == -1) && (timestampString.indexOf('/') == -1)
				&& (timestampString.indexOf('.') == -1) && (timestampString.indexOf(':') == -1)) {
			throw DaoConversionException.incorrectTimestampFormat(timestampString);
		}
		StringTokenizer timestampStringTokenizer = new StringTokenizer(timestampString, " /:.-");

		int year;
		int month;
		int day;
		int hour;
		int minute;
		int second;
		int nanos;
		try {
			year = Integer.parseInt(timestampStringTokenizer.nextToken());
			month = Integer.parseInt(timestampStringTokenizer.nextToken());
			day = Integer.parseInt(timestampStringTokenizer.nextToken());
			try {
				hour = Integer.parseInt(timestampStringTokenizer.nextToken());
				minute = Integer.parseInt(timestampStringTokenizer.nextToken());
				second = Integer.parseInt(timestampStringTokenizer.nextToken());
			} catch (java.util.NoSuchElementException endOfStringException) {
				// May be only a date string desired to be used as a timestamp.
				hour = 0;
				minute = 0;
				second = 0;
			}
		} catch (NumberFormatException exception) {
			throw DaoConversionException.incorrectTimestampFormat(timestampString);
		}

		try {
			String nanoToken = timestampStringTokenizer.nextToken();
			nanos = Integer.parseInt(nanoToken);
			for (int times = 0; times < (9 - nanoToken.length()); times++) {
				nanos = nanos * 10;
			}
		} catch (java.util.NoSuchElementException endOfStringException) {
			nanos = 0;
		} catch (NumberFormatException exception) {
			throw DaoConversionException.incorrectTimestampFormat(timestampString);
		}

		// Java dates are based on year after 1900 so I need to delete it.
		year = year - 1900;

		// Java returns the month in terms of 0 - 11 instead of 1 - 12.
		month = month - 1;

		java.sql.Timestamp timestamp;
		// TODO: This was not converted to use Calendar for the conversion
		// because calendars do not take nanos.
		// but it should be, and then just call setNanos.
		timestamp = new java.sql.Timestamp(year, month, day, hour, minute, second, nanos);
		return timestamp;
	}

	/**
	 * Answer a java.util.Date from a timestamp
	 *
	 * @param timestampObject
	 *            - timestamp representation of date
	 * @return - java.util.Date representation of timestampObject
	 */
	public static java.util.Date utilDateFromTimestamp(java.sql.Timestamp timestampObject) {
		// Conditionally remove workaround for java bug which truncated
		// nanoseconds from timestamp.getTime(). We will now only recalculate
		// the nanoseconds
		// When timestamp.getTime() results in nanoseconds == 0;
		long time = timestampObject.getTime();
		boolean appendNanos = ((time % 1000) == 0);
		if (appendNanos) {
			return new java.util.Date(time + (timestampObject.getNanos() / 1000000));
		} else {
			return new java.util.Date(time);
		}
	}

	/**
	 * Convert the HEX string to a byte array. HEX allows for binary data to be
	 * printed.
	 */
	public static byte[] buildBytesFromHexString(String hex) {
		String tmpString = hex;
		if ((tmpString.length() % 2) != 0) {
			throw DaoConversionException.couldNotConvertToByteArray(hex);
		}
		byte[] bytes = new byte[tmpString.length() / 2];
		int byteIndex;
		int strIndex;
		byte digit1;
		byte digit2;
		for (byteIndex = bytes.length - 1, strIndex = tmpString.length()
				- 2; byteIndex >= 0; byteIndex--, strIndex -= 2) {
			digit1 = (byte) Character.digit(tmpString.charAt(strIndex), 16);
			digit2 = (byte) Character.digit(tmpString.charAt(strIndex + 1), 16);
			if ((digit1 == -1) || (digit2 == -1)) {
				throw DaoConversionException.couldNotBeConverted(hex, ClassConstants.APBYTE);
			}
			bytes[byteIndex] = (byte) ((digit1 * 16) + digit2);
		}
		return bytes;
	}

	/**
	 * Convert the byte array to a HEX string. HEX allows for binary data to be
	 * printed.
	 */
	public static String buildHexStringFromBytes(byte[] bytes) {
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer stringBuffer = new StringBuffer();
		int tempByte;
		for (int byteIndex = 0; byteIndex < (bytes).length; byteIndex++) {
			tempByte = (bytes)[byteIndex];
			if (tempByte < 0) {
				tempByte = tempByte + 256;// compensate for the fact that byte
				// is signed in Java
			}
			tempByte = (byte) (tempByte / 16);// get the first digit
			if (tempByte > 16) {
				throw DaoConversionException.couldNotBeConverted(bytes, ClassConstants.STRING);
			}
			stringBuffer.append(hexArray[tempByte]);

			tempByte = (bytes)[byteIndex];
			if (tempByte < 0) {
				tempByte = tempByte + 256;
			}
			tempByte = (byte) (tempByte % 16);// get the second digit
			if (tempByte > 16) {
				throw DaoConversionException.couldNotBeConverted(bytes, ClassConstants.STRING);
			}
			stringBuffer.append(hexArray[tempByte]);
		}
		return stringBuffer.toString();
	}

	/**
	 * PERF: This is used to optimize Calendar conversion/printing. This should
	 * only be used when a calendar is temporarily required, when finished it
	 * must be released back.
	 */
	public static void releaseCalendar(Calendar calendar) {
		getCalendarCache().offer(calendar);
	}
}
