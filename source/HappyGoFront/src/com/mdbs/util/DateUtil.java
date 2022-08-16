package com.mdbs.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	/**
	* 取得指定日期幾天前 / 後的日期
	* @param date
	* @param day
	* @return Date
	*/
	public static Date addDay(final Date date, final int day) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}

	/**
	 * 傳入年月日轉Date
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date parse(String year, String month, String date) throws Exception {
		int iYear = Integer.parseInt(year);
		int iMonth = Integer.parseInt(month);
		int iDate = Integer.parseInt(date);
		if (iMonth < 1 || iMonth > 12) {
			throw new Exception("invalid month!");
		}
		iMonth -= 1; // zero-base for Calendar's month
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, iYear);
		calendar.set(Calendar.MONTH, iMonth);
		calendar.set(Calendar.DATE, 1);

		int minDate = calendar.getActualMinimum(Calendar.DATE);
		int maxDate = calendar.getActualMaximum(Calendar.DATE);
		if (iDate < minDate || iDate > maxDate) {
			throw new Exception("invalid date!");
		}
		calendar.set(Calendar.DATE, iDate);
		return calendar.getTime();
	}

    /**
     * 日期String轉Date
     * 例:yyyy/MM/dd HH:mm:ss
     * yyyy-MM-dd HH:mm:ss
     * yyyyMMddHHmmss
     * @param time
     * @param pattern
     * @return
     * @throws Exception
     */
    public static Date parse(String time, String pattern) throws Exception {
        return parse(time, pattern, null, null);
    }

    public static Date parse(String time, String pattern, Locale locale, String timeZone) throws Exception {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        if (timeZone != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        return sdf.parse(time);
    }
    
    /**
	 * 傳入年月日轉Timestamp
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 * @throws Exception
	 */
    public static Timestamp parseTimestamp(String time, String pattern) throws Exception {
        return parseTimestamp(time, pattern, null, null);
    }
    
    public static Timestamp parseTimestamp(String time, String pattern, Locale locale, String timeZone) throws Exception {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        if (timeZone != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        return new Timestamp(sdf.parse(time).getTime());
    }

    /**
     * 日期Date轉String,例:yyyy/MM/dd
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, null, null);
    }

    /**
     * 日期timestamp轉String,例:yyyy/MM/dd
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String format(Timestamp timestamp, String pattern) {
        return format(timestamp, pattern, null, null);
    }

    public static String format(Date date, String pattern, Locale locale) {
        return format(date, pattern, locale, null);
    }

    public static String format(Date date, String pattern, String timeZone) {
        return format(date, pattern, null, timeZone);
    }

    public static String format(Date date, String pattern, Locale locale, String timeZone) {
        try {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            DateFormat df = new SimpleDateFormat(pattern, locale);
            if (timeZone != null) {
                df.setTimeZone(TimeZone.getTimeZone(timeZone));
            } else {    // to default timezone
                df.setTimeZone(TimeZone.getDefault());
            }
            return df.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 將傳入日期物件的時分秒更改為當年的第一秒
     * @param date date
     * @return Date
     */
    public static Date getYearBegin(Date date) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        return cal.getTime();
    }

    /**
     * 將傳入日期物件的時分秒更改為當年的最後一秒
     * @param date date
     * @return Date
     */
    public static Date getYearEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 將傳入日期物件的時分秒更改為當年的最後一秒
     * @param Timestamp
     * @return Timestamp
     */
    public static Timestamp getYearEndTimestamp(Timestamp date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 將傳入日期物件的時分秒更改為當日的第一秒
     * @param date date
     * @return Date
     */
    public static Date getDayBegin(Date date) {
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 將傳入日期物件的時分秒更改為當日的最後一秒
     * @param date date
     * @return Date
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 取得當前日期所在周的第一天
     * @param date date
     * @return Date
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得當前日期所在周的最後一天
     * @param date date
     * @return Date
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 取得指定日期所在月的第一天
     * @param date date
     * @return Date
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        int minDate = c.getActualMinimum(Calendar.DATE);
        c.set(Calendar.DATE, minDate);
        Date first = getDayBegin(c.getTime());
        return first;
    }

    /**
     * 取得指定日期所在月的最後一天
     * @param date date
     * @return Date
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        int maxDate = c.getActualMaximum(Calendar.DATE);
        c.set(Calendar.DATE, maxDate);
        Date end = getDayEnd(c.getTime());
        return end;
    }
    
    /**
     * 返回指定日期的季的第一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}
	
	/**
     * 返回指定日期的季的最後一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}
    
    /**
     * 返回指定日期的上一季的最後一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getLastDayOfLastQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}
	
	/**
     * 返回指定年季的上一季的最後一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 12 - 1;
		} else if (quarter == 2) {
			month = 3 - 1;
		} else if (quarter == 3) {
			month = 6 - 1;
		} else if (quarter == 4) {
			month = 9 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}
	
	/**
     * 返回指定年月的月的第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }
    
    /**
     * 返回指定年月的月的最後一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    
	/**
     * 返回指定年季的季的第一天
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }
    
    /**
     * 返回指定年季的季的最後一天
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }
    
	/**
     * 返回指定日期的季度
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 取得幾年前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalYear(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        c.set(year, month, day);
        c.add(Calendar.YEAR, amount);
        return c.getTime();
    }

    /**
     * 取得幾月前後的日期
     * @param date
     * @param amount
     * @return
     */
    public static Date getCalMonth(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        c.set(year, month, day);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }

    /**
     * 取得幾天前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalDay(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        c.set(year, month, day);
        c.add(Calendar.DATE, amount);
        return c.getTime();
    }

    /**
     * 取得幾小時前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalHour(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);

        c.set(year, month, day, hh, mm, ss);
        c.add(Calendar.HOUR_OF_DAY, amount);
        return c.getTime();
    }

    /**
     * 取得幾分鐘前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalMin(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);

        c.set(year, month, day, hh, mm, ss);
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
    }

    /**
     * 計算二日期相差幾天
     * @param dateStr1	:	字串日期1
     * @param dateStr2	:	字串日期2
     * @return
     */
    public static long getCalDayDiff(String dateStr1, String dateStr2) {
		long dateDiff = 0;
		try {
			Date date1 = parse(dateStr1, "yyyyMMdd");
			Date date2 = parse(dateStr2, "yyyyMMdd");
			if (date1.getTime() < date2.getTime()) {
				dateDiff = ((date2.getTime() / 1000 - date1.getTime() / 1000) / (24 * 60 * 60));
			} else {
				dateDiff = ((date1.getTime() / 1000 - date2.getTime() / 1000) / (24 * 60 * 60));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateDiff;
	}

    /**
     * 計算二日期相差幾天
     * @param date1	:	Date日期1
     * @param date2	:	Date日期2
     * @return
     */
    public static long getCalDayDiff(Date date1, Date date2) {
		long dateDiff = 0;
		try {
			//日期1,2會被更改為當天的第一秒
			date1 = getDayBegin(date1);
			date2 = getDayBegin(date2);
			if (date1.getTime() < date2.getTime()) {
				dateDiff = ((date2.getTime() / 1000 - date1.getTime() / 1000) / (24 * 60 * 60));
			} else {
				dateDiff = ((date1.getTime() / 1000 - date2.getTime() / 1000) / (24 * 60 * 60));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateDiff;
	}
    
    /**
     * 計算二日期相差幾小時
     * @param dateStr1	:	字串日期1
     * @param dateStr2	:	字串日期2
     * @return
     */
	public static long getCalHoursDiff(String dateStr1, String dateStr2) {
		long dateDiff = 0;
		try {
			Date date1 = parse(dateStr1, "yyyyMMdd HH:mm");
			Date date2 = parse(dateStr2, "yyyyMMdd HH:mm");
			if (date1.getTime() < date2.getTime()) {
				dateDiff = ((date2.getTime() / 1000 - date1.getTime() / 1000) / (60 * 60));
			} else {
				dateDiff = ((date1.getTime() / 1000 - date2.getTime() / 1000) / (60 * 60));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateDiff;
	}

    /**
     * 格式化民國年月日 至 時分秒 ex:101/01/02 18:37:22
     * @param now now
     * @return String
     */
    public static String convertTwyDateTime(Date now) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        int year = cal.get(Calendar.YEAR) - 1911;
        String strYear = year < 100 ? "0" + year : "" + year;
        String month = DateUtil.format(now, "MM");
        String date = DateUtil.format(now, "dd");
        String hh = DateUtil.format(now, "HH");
        String mm = DateUtil.format(now, "mm");
        String ss = DateUtil.format(now, "ss");
        return strYear + "/" + month + "/" + date + " " + hh + ":" + mm + ":" + ss;
    }

    /**
     * 格式化民國年月日 ex:1010102
     * @param now now
     * @return String
     */
    public static String convertTwyDate(Date now) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        int year = cal.get(Calendar.YEAR) - 1911;
        String strYear = year < 100 ? "0" + year : "" + year;
        String month = DateUtil.format(now, "MM");
        String date = DateUtil.format(now, "dd");
        return strYear + "" + month + "" + date;
    }

    /**
     * 格式化民國年
     * @param now now
     * @return String
     */
    public static String convertTwyYear(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int year = cal.get(Calendar.YEAR) - 1911;
        return year < 100 ? "0" + year : "" + year;
    }

    /**
     * 格式化民國年月日yyy年M月d日
     * @param date date
     * @return String
     */
	public static final String formatToChineseDate(Date date) {
		if (date == null) {
			return "";
		} else {
			StringBuffer chiDate = new StringBuffer();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			chiDate.append(cal.get(Calendar.YEAR) - 1911);
			chiDate.append("年");
			chiDate.append(cal.get(Calendar.MONTH) + 1);
			chiDate.append("月");
			chiDate.append(cal.get(Calendar.DAY_OF_MONTH));
			chiDate.append("日");
			return chiDate.toString();
		}
	}
	
    /**
     * 格式化民國年月日 yyy/M/d
     * @param date          :   date
     * @param separator     :   "/",可指定格式
     * @return
     */
	public static final String formatToChineseDate(Date date, String separator) {
		if (date == null) {
			return "";
		} else {
			StringBuffer chiDate = new StringBuffer();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			chiDate.append(cal.get(Calendar.YEAR) - 1911);
			chiDate.append(separator);
			chiDate.append(cal.get(Calendar.MONTH) + 1);
			chiDate.append(separator);
			chiDate.append(cal.get(Calendar.DAY_OF_MONTH));
			return chiDate.toString();
		}
	}
	
	/**
     * 格式化民國年月日,XXX年XX月XX日
     * @param date          :   date
     * @return
     */
	public static String formatToChineseDate(String strDate) {
		String twyDate = "";
		if (strDate == null) {
			return "";
		} else {
			try {
				Date date = DateUtil.parse(strDate, "yyyy-MM-dd");
				twyDate = formatToChineseDate(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return twyDate;
	}
	
	/**
     * 格式化民國年月日 yyy/M/d
     * @param date          :   date
     * @param separator     :   "/",可指定格式
     * @return
     */
	public static String formatToTwyDate(String strDate, String separator) {
		String twyDate = "";
		if (strDate == null) {
			return "";
		} else {
			try {
				Date date = DateUtil.parse(strDate, "yyyy-MM-dd");
				twyDate = formatToChineseDate(date, separator);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return twyDate;
	}
	
    /**
     * 民國年轉西元年,例:1011231
     * @param date date
     * @return Date
     */
    public static Date twyConvertDate(String date) {
        Date dd = null;
        SimpleDateFormat sdf;
        try {
            if (date != null && date.length() == 7) {
                String yr = date.substring(0, 3);
                int year = Integer.parseInt(yr) + 1911;
                date = year + date.substring(3);
                sdf = new SimpleDateFormat("yyyyMMdd");
                dd = sdf.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dd;
    }

    /**
     * 取得該日期所屬星期幾(中文)
     * @param date
     * @return
     */
    public static String getChineseWeekDayName(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(date);
		return week;
    }

    /**
     * 取得該日期所屬星期幾(數值)
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek - 1;
	}

    public static void main(String[] args) throws Exception{
		String _value = "2016-03-21 11:00:17.0";
		System.out.println(formatToChineseDate(_value));
		System.out.println(formatToTwyDate(_value, "/"));
	}

}
