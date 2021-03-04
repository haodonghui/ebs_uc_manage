package com.yestae.user.manage.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	/**
	 * 获取时间戳所在日期的23:59:59的时间戳
	 * 
	 * @param timeParam
	 * @return
	 */
	public static Integer getDayEndTime(Integer timeParam) {
		if (timeParam != null) {
			String newPubDate = new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(timeParam * 1000L));
			return (int) (toTimestamp(newPubDate + " 23:59:59").getTime() / 1000);
		}
		return null;
	}
	/**
	 * 获取时间格式为yyyyMMdd
	 * 
	 * @param timeParam
	 * @return
	 */
	public static Integer getDateyyyyMMdd(Date date) {
		if(date != null){
			String format = new SimpleDateFormat("yyyyMMdd").format(date);
			return Integer.parseInt(format);
		}
		return null;
	}

	/**
	 * 获取当天00:00:00的时间戳
	 * 
	 * @return Integer
	 */
	public static Integer getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return (int) (todayStart.getTime().getTime() / 1000);
	}

	/**
	 * 获取当前时间的Int型值(精确到秒)
	 * 
	 * @return
	 */
	public static Integer getCurrentTime() {
		return (int) (System.currentTimeMillis() / 1000L);
	}

	/**
	 * JSP时间控件的字符串值转换为Integer型
	 * 
	 * @param time
	 * @return
	 */
	public static Integer getBeginTime(String time) {
		if (StringUtils.isNotEmpty(time)) {
			return (int) (toTimestamp(time).getTime() / 1000);
		}
		return 0;
	}

	/**
	 * Integer型时间转换为JSP时间控件的字符串值
	 * 
	 * @param long1
	 * @return
	 */
	public static String getStringDate(Long long1) {
		if (long1 != null) {
			String newPubDate = new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(long1 * 1000L));
			return newPubDate;
		}
		//return "1970-01-01";
		return null;
	}
	
	public static String getTimeString(Long time) {
		if(time == null){
			return null;
		}
		LocalDateTime localDateTime  = LocalDateTime.ofEpochSecond(time/1000, 0, ZoneOffset.MAX);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDateTime.format(formatter);
	}
	
	public static String getTimeString(Long time, Boolean timeFlag) {
		if(time == null){
			return null;
		}
		String format = "yyyy-MM-dd";
		if(timeFlag != null && timeFlag){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		LocalDateTime localDateTime  = LocalDateTime.ofEpochSecond(time/1000, 0, ZoneOffset.MAX);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}
	

	public static String getStringTime(Integer time) {
		if (time != null && time > 0) {
			String newPubDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(time * 1000L));
			return newPubDate;
		}
		return null;
	}

	/**
	 * 获取当前日期的前一天23：59：59的时间戳
	 * 
	 * @return
	 */
	public static Integer getYesterdayEndTime() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(c.getTime());
		long time = toTimestamp(format + " 23:59:59").getTime() / 1000L;
		return (int) time;
	}
	/**
	 * 获取当前日期的前一天00：00：00的时间戳
	 * 
	 * @return
	 */
	public static Integer getYesterdayStartTime() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(c.getTime());
		long time = toTimestamp(format + " 00:00:00").getTime() / 1000L;
		return (int) time;
	}

	/**
	 * 获取当天23:59:59的时间戳
	 * 
	 * @return Long
	 */
	public static Long getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime() / 1000;
	}

	/**
	 * 获取本周的开始时间 默认为：周一00:00:00的时间戳
	 * 
	 * @return
	 */
	public static Integer getWeekStartTime() {
		Calendar c = Calendar.getInstance();
		int days = c.get(Calendar.DAY_OF_WEEK) - 2;
		System.out.println(days);
		if (days == 0) {
			days = 7;
		}
		c.add(Calendar.DATE, -days);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return (int) (c.getTime().getTime() / 1000);
	}

	/**
	 * 获取当前月的第一天的时间Int值
	 * 
	 * @return
	 */
	public static Integer getCurrMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Long aLong = new Long(calendar.getTimeInMillis() / 1000l);
		return aLong.intValue();
	}
	
	/**
	 * 获取上一周的周一的00:00:00的Int值
	 * 
	 * @return
	 */
	public static Integer getLastWeekStartTime() {
		Calendar c = Calendar.getInstance();
		int days = c.get(Calendar.DAY_OF_WEEK) - 2;
		if (days == 0) {
			days = 7;
		}
		days += 7;
		c.add(Calendar.DATE, -days);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return (int) (c.getTime().getTime() / 1000);
	}

	/**
	 * 获取上一周的周日的23:59:59的Int值
	 * 
	 * @return
	 */
	public static Integer getLastWeekEndTime() {
		Calendar c = Calendar.getInstance();
		int days = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (days == 0) {
			days = 7;
		}
		c.add(Calendar.DATE, -days);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return (int) (c.getTime().getTime() / 1000);
	}
	
	public static Integer getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Long aLong = new Long(calendar.getTimeInMillis() / 1000l);
		return aLong.intValue();
	}
	
	public static Integer getLastMonthEndDay() {
		return getCurrMonthFirstDay() - 1;
	}

	/**
	 * JSP时间控件的字符串值转换为Integer型
	 * 
	 * @param time
	 * @return
	 */
	public static Integer getIntegerTime(String time) {
		if (StringUtils.isNotEmpty(time)) {
			return (int) (toTimestamp(time).getTime() / 1000);
		}
		return 0;
	}
	
	/* MySQL Date类型与String类型的时间转换 **************/
	
	private static final long ONE_DAY = 24 * 3600000;

    private static final long ONE_MINUTE = 60000;

    private static String datePattern = "yyyy-MM-dd";

    private static String timePattern = "HH:mm:ss";
    
    private static String speDatePattern = "yyyyMMdd";

    private static String speTimePattern = "HHmmss";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

    private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(datePattern + " " + timePattern);
    
    private static SimpleDateFormat speFormat = new SimpleDateFormat(speDatePattern + speTimePattern);

    /**
     * 将日期对象转换为字符串，格式为yyyy-MM-dd.
     * @param date
     *            日期.
     * @return 日期对应的日期字符串.
     */
    public static String toDateString(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }

    /**
     * 将字符串转换为日期对象，字符串必须符合yyyy-MM-dd的格式.
     * @param s
     *            要转化的字符串.
     * @return 字符串转换成的日期.如字符串为NULL或空串,返回NULL.
     */
    public static Date toDate(String s) {
        s = StringUtils.trim(s);
        if (s.length() < 1) {
            return null;
        }
        try {
            if (s.length() <= 10) {
                return dateFormat.parse(s);
            }
            return toDate(Timestamp.valueOf(s));
        } catch (Exception e) {
        	logger.error("字符串转换为日期对象出错！字符串必须符合yyyy-MM-dd的格式");
            return null;
        }
    }

    /**
     * 将字符串转换为日期对象，字符串必须符合(yyyy-MM-dd HH:mm:ss)的格式.
     * @param s
     * @return
     */
    public static Date toDateTime(String s) {
        s = StringUtils.trim(s);
        if (s.length() < 19) {
            return null;
        }
        try {
            return toDate(Timestamp.valueOf(s));
        } catch (Exception e) {
        	logger.error("字符串转换为日期对象出错！字符串必须符合(yyyy-MM-dd HH:mm:ss)的格式");
            return null;
        }
    }

    /**
     * 将日期对象转换为字符串，转换后的格式为yyyy-MM-dd HH:mm:ss.
     * @param date
     *            要转换的日期对象.
     * @return 字符串,格式为yyyy-MM-dd HH:mm:ss.
     */
    public static String toDatetimeString(Date date) {
        if (date == null) {
            return null;
        }
        return datetimeFormat.format(date);
    }
    
    /**
     * 将长整型日期转换为字符串，转换后的格式为yyyy-MM-dd HH:mm:ss.
     * @param time
     *            要转换的长整型日期.
     * @return 字符串,格式为yyyy-MM-dd HH:mm:ss.
     */
    public static String toDatetimeString(Long time) {
    	if(time == null){
    		return null;
    	}
    	Date date = new Date(time);
    	return datetimeFormat.format(date);
    }

    /**
     * 计算两个日期间相隔的周数
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return
     */
    public static int computeWeek(Date startDate, Date endDate) {

        int weeks = 0;

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (beginCalendar.before(endCalendar)) {

            // 如果开始日期和结束日期在同年、同月且当前月的同一周时结束循环
            if (beginCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR)
                    && beginCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH)
                    && beginCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == endCalendar
                            .get(Calendar.DAY_OF_WEEK_IN_MONTH)) {
                break;

            } else {

                beginCalendar.add(Calendar.DAY_OF_YEAR, 7);
                weeks += 1;
            }
        }

        return weeks;
    }

    /**
     * 返回当前系统时间
     * @return
     */
    public static String getCurrDateTime() {
        return toDatetimeString(new Date());

    }

    /**
     * 获取系统当前时间，待后期可扩展到取数据库时间
     * @return 系统当前时间
     */
    public static String getCurrDate() {
        return toDateString(new Date());

    }

    /**
     * 将yyyy-MM-dd HH:mm:ss转化为yyyy-MM-dd
     * @param dateTime
     * @return
     */
    public static String getCurrDate(String dateTime) {
        return toDateString(toDateTime(dateTime));
    }

    /**
     * 将Timestamp转换为日期.
     * @param timestamp
     *            时间戳.
     * @return 日期对象.如时间戳为NULL,返回NULL.
     */
    public static Date toDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp.getTime());
    }

    /**
     * 将日期转换为Timestamp.
     * @param date
     *            日期.
     * @return 时间戳.如日期为NULL,返回NULL.
     */
    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }

        return new Timestamp(date.getTime());
    }

    /**
     * 将时间戳对象转化成字符串.
     * @param t
     *            时间戳对象.
     * @return 时间戳对应的字符串.如时间戳对象为NULL,返回NULL.
     */
    public static String toDateString(Timestamp t) {
        if (t == null) {
            return null;
        }
        return toDateString(toDate(t));
    }

    /**
     * 将Timestamp转换为日期时间字符串.
     * @param t
     *            时间戳对象.
     * @return Timestamp对应的日期时间字符串.如时间戳对象为NULL,返回NULL.
     */
    public static String toDatetimeString(Timestamp t) {
        if (t == null) {
            return null;
        }
        return toDatetimeString(toDate(t));
    }

    /**
     * 将日期字符串转换为Timestamp对象.
     * @param s
     *            日期字符串.
     * @return 日期时间字符串对应的Timestamp.如字符串对象为NULL,返回NULL.
     */

    public static Timestamp toTimestamp(String s) {
        return toTimestamp(toDate(s));
    }

    /**
     * 返回年份，如2004.
     */
    public static int getYear(Date d) {

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.YEAR);
    }

    public static int getYear() {
        return getYear(new Date());
    }

    /**
     * 返回月份，为1－－ － 12内.
     */
    public static int getMonth(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getMonth() {
        return getMonth(new Date());
    }

    /**
     * 取得季度
     * @param d
     *            日期类型
     * @return
     */
    public static final int getQuarter(Date d) {
        return getQuarter(getMonth(d));
    }

    /**
     * 取得当前的季度
     * @return
     */
    public static final int getQuarter() {
        return getQuarter(getMonth());
    }

    /**
     * 传递月份,取得季度
     * @param num
     * @return
     */
    public static final int getQuarter(int num) {
        num = num % 3 == 0 ? num / 3 : (num / 3 + 1);
        return num % 4 == 0 ? 4 : num % 4;

    }

    /**
     * 返回日期，为1－－ － 31内.
     */
    public static int getDay(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDay() {
        return getDay(new Date());
    }

    /**
     * 获得将来的日期.如果timeDiffInMillis > 0,返回将来的时间;否则，返回过去的时间
     * @param currDate
     *            现在日期.
     * @param timeDiffInMillis
     *            毫秒级的时间差.
     * @return 经过 timeDiffInMillis 毫秒后的日期.
     */
    public static Date getFutureDate(Date currDate, long timeDiffInMillis) {
        long l = currDate.getTime();

        l += timeDiffInMillis;
        return new Date(l);
    }

    /**
     * 获得将来的日期.如果timeDiffInMillis > 0,返回将来的时间;否则，返回过去的时间.
     * @param currDate
     *            现在日期.
     * @param timeDiffInMillis
     *            毫秒级的时间差.
     * @return 经过 timeDiffInMillis 毫秒后的日期.
     */
    public static Date getFutureDate(String currDate, long timeDiffInMillis) {
        return getFutureDate(toDate(currDate), timeDiffInMillis);
    }

    /**
     * 获得将来的日期.如果 days > 0,返回将来的时间;否则，返回过去的时间.
     * @param currDate
     *            现在日期.
     * @param days
     *            经过的天数.
     * @return 经过days天后的日期.
     */
    public static Date getFutureDate(Date currDate, int days) {
        long l = currDate.getTime();
        long l1 = (long) days * ONE_DAY;

        l += l1;
        return new Date(l);
    }

    /**
     * 获得将来的日期.如果 days > 0,返回将来的时间;否则，返回过去的时间.
     * @param currDate
     *            现在日期,字符型如2005-05-05 [14:32:10].
     * @param days
     *            经过的天数.
     * @return 经过days天后的日期.
     */
    public static Date getFutureDate(String currDate, int days) {
        return getFutureDate(toDate(currDate), days);
    }

    /**
     * 检查是否在核算期内.
     * @param currDate
     *            当前时间.
     * @param dateRange
     *            核算期日期范围.
     * @return 是否在核算期内.
     */
    public static boolean isDateInRange(String currDate, String[] dateRange) {
        if (currDate == null || dateRange == null || dateRange.length < 2) {
            throw new IllegalArgumentException("传入参数非法");
        }

        currDate = getDatePart(currDate);
        return (currDate.compareTo(dateRange[0]) >= 0 && currDate.compareTo(dateRange[1]) <= 0);
    }

    /**
     * 只获取日期部分.获取日期时间型的日期部分.
     * @param currDate
     *            日期[时间]型的字串.
     * @return 日期部分的字串.
     */
    public static String getDatePart(String currDate) {
        if (currDate != null && currDate.length() > 10) {
            return currDate.substring(0, 10);
        }

        return currDate;
    }

    /**
     * 计算两天的相差天数,不足一天按一天算.
     * @param stopDate
     *            结束日期.
     * @param startDate
     *            开始日期.
     * @return 相差天数 = 结束日期 - 开始日期.
     */
    public static int getDateDiff(String stopDate, String startDate) {
        long t2 = toDate(stopDate).getTime();
        long t1 = toDate(startDate).getTime();

        int diff = (int) ((t2 - t1) / ONE_DAY); // 相差天数
        // 如有剩余时间，不足一天算一天
        diff += (t2 > (t1 + diff * ONE_DAY) ? 1 : 0);
        return diff;
    }

    /**
     * 计算两天的相差分钟,不足一分钟按一分钟算.
     * @param stopDate
     *            结束日期.
     * @param startDate
     *            开始日期.
     * @return 相差分钟数 = 结束日期 - 开始日期.
     */
    public static int getMinutesDiff(String stopDate, String startDate) {
        long t2 = toDate(stopDate).getTime();
        long t1 = toDate(startDate).getTime();

        int diff = (int) ((t2 - t1) / ONE_MINUTE); // 相差分钟数
        // 如有剩余时间，不足一天算一天
        diff += (t2 > (t1 + diff * ONE_MINUTE) ? 1 : 0);
        return diff;
    }
    
    /**
     * 计算两个时间相差小时数
     * @param stopDate
     * @param startDate
     * @return
     */
    public static int getHoursDiff(String stopDate, String startDate){
    	Date bDate = toDateTime(stopDate);
    	Date eDate = toDateTime(startDate);
    	long date = Math.abs(eDate.getTime()
    			- bDate.getTime());
    	return (int)(date / (1000 * 60 * 60));
    }

    /**
     * 判断两个日期是否在同一周
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 按年获取周序号
     * @param currDate
     * @return
     */
    public static int getSeqWeekByYear(Date currDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currDate);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int weekNo = c.get(Calendar.WEEK_OF_YEAR);

        Calendar lastDate = Calendar.getInstance();

        if (weekNo == 1) {
            // 获取周五时间
            lastDate.setTime(toDate(getFriday(c.getTime())));
            if (c.get(Calendar.YEAR) != lastDate.get(Calendar.YEAR)) {
                lastDate.setTime(toDate(getMonday(c.getTime())));
                lastDate.add(Calendar.DATE, -1);
                lastDate.setFirstDayOfWeek(Calendar.MONDAY);
                weekNo = lastDate.get(Calendar.WEEK_OF_YEAR) + 1;
            }
        }
        return weekNo;
    }

    /**
     * 按月获取周序号
     * @param currDate
     * @return
     */
    public static int getSeqWeekByMonth(Date currDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currDate);
        c.setFirstDayOfWeek(Calendar.MONDAY);

        return c.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取周一的日期
     * @param date
     * @return
     */
    public static String getMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 获取周一的日期
     * @param date
     * @return
     */
    public static String getMonday(String date) {
        Calendar c = Calendar.getInstance();
        c.setTime(toDate(date));
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 获得周五的日期
     */
    public static String getFriday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    // 当前日期前几天或者后几天的日期
    public static String afterNDay(int n) {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(new Date());
        c.add(Calendar.DATE, n);
        Date d2 = c.getTime();
        String s = df.format(d2);
        return s;
    }

    /**
     * 判断某年是否为闰年
     * @return boolean
     */
    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        /** 判断是否为闰年，赋值给一标识符flag */
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
            isLeep = true;
        } else if (yearNum % 400 == 0) {
            isLeep = true;
        } else {
            isLeep = false;
        }
        return isLeep;
    }

    /**
     * 计算某年某周的开始日期
     * @return interger
     */
    public static String getYearWeekFirstDay(int yearNum, int weekNum) {

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 分别取得当前日期的年、月、日
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DATE));
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return setDateFormat(tempDate, "yyyy-MM-dd");
    }

    /**
     * @see 取得指定时间的给定格式()
     * @return String
     */
    public static String setDateFormat(String myDate, String strFormat) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            String sDate = sdf.format(sdf.parse(myDate));
            return sDate;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算某年某周的结束日期
     * @return interger
     */
    public static String getYearWeekEndDay(int yearNum, int weekNum) {

        /*Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 分别取得当前日期的年、月、日
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DATE));
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return setDateFormat(tempDate, "yyyy-MM-dd");*/
    	
    	 Calendar cal = Calendar.getInstance();  
         //设置年份  
         cal.set(Calendar.YEAR,yearNum);  
         //设置周  
         cal.set(Calendar.WEEK_OF_YEAR, weekNum);  
         //设置该周第一天为星期一  
         cal.setFirstDayOfWeek(Calendar.MONDAY);   
         //设置最后一天是星期日  
         cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6); // Sunday  
         //格式化日期  
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
         String lastDayOfWeek = sdf.format(cal.getTime());  
           
         return lastDayOfWeek;  
    }

    /**
     * 计算某年某月的开始日期
     * @return interger
     */
    public static String getYearMonthFirstDay(int yearNum, int monthNum) {

        // 分别取得当前日期的年、月、日
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "1";
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return setDateFormat(tempDate, "yyyy-MM-dd");
    }

    /**
     * 计算某年某月的结束日期
     * @return interger
     */
    public static String getYearMonthEndDay(int yearNum, int monthNum) {

        // 分别取得当前日期的年、月、日
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "31";
        if (tempMonth.equals("1") || tempMonth.equals("3") || tempMonth.equals("5") || tempMonth.equals("7")
                || tempMonth.equals("8") || tempMonth.equals("10") || tempMonth.equals("12")) {
            tempDay = "31";
        }
        if (tempMonth.equals("4") || tempMonth.equals("6") || tempMonth.equals("9") || tempMonth.equals("11")) {
            tempDay = "30";
        }
        if (tempMonth.equals("2")) {
            if (isLeapYear(yearNum)) {
                tempDay = "29";
            } else {
                tempDay = "28";
            }
        }

        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return tempDate;

    }

    /**
     * 根据参数，获取相对日期
     * @param date
     * @param flag
     * @param intervals
     * @return
     */
    public static Date getRelativeDate(Date date, char flag, int intervals) {
        Date currDate = null;
        if (date != null) {
            Calendar newDate;
            (newDate = Calendar.getInstance()).setTime(date);
            switch (flag) {
            case 'y':
                newDate.add(Calendar.YEAR, intervals);
                break;
            case 'M':
                newDate.add(Calendar.MONTH, intervals);
                break;
            case 'd':
                newDate.add(Calendar.DATE, intervals);
                break;
            case 'w':
                newDate.add(Calendar.WEEK_OF_YEAR, intervals);
                break;
            case 'h':
                newDate.add(Calendar.HOUR, intervals);
                break;
            case 'm':
                newDate.add(Calendar.MINUTE, intervals);
                break;
            case 's':
                newDate.add(Calendar.SECOND, intervals);
                break;
            case 'S':
                newDate.add(Calendar.MILLISECOND, intervals);
            }
            currDate = newDate.getTime();
        }
        return currDate;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static Date getCurrDay() {
        return new Date();
    }

    /**
     * 获取下一周的第一天
     * @return
     */
    public static String getAfterWeekFirst() {
        return getMonday(afterNDay(7));
    }

    /**
     * 获取下一月的第一天
     * @param date
     * @param afterNum
     * @return
     */
    public static String getAfterMonthFirst(String date, int afterNum) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(toDate(date));
        cal.add(Calendar.MONTH, afterNum);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
        return dateFormat.format(cal.getTime());
    }

    /**
     * 获取周日
     * @param date
     * @param afterNum
     * @return
     */
    public static String getSundayOfWeek(String date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(toDate(date));
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);
        return dateFormat.format(cal.getTime());
    }

    /**
     * 获取下一季度的第一天
     * @param date
     * @param afterNum
     * @return
     */
    public static String getAfterQuarterFirst(String date, int afterNum) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(toDate(date));
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
        if (currentMonth >= 1 && currentMonth <= 3)
            cal.set(Calendar.MONTH, 0);
        else if (currentMonth >= 4 && currentMonth <= 6)
            cal.set(Calendar.MONTH, 3);
        else if (currentMonth >= 7 && currentMonth <= 9)
            cal.set(Calendar.MONTH, 6);
        else if (currentMonth >= 10 && currentMonth <= 12)
            cal.set(Calendar.MONTH, 9);
        cal.add(Calendar.MONTH, afterNum * 3);
        return dateFormat.format(cal.getTime());
    }

    /**
     * 判断是否为同一天
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean isOneDay(String beginTime, String endTime) {
        try {
            Date beginDate = toDateTime(beginTime);
            Date endDate = toDateTime(endTime);
            String bymd = getYear(beginDate) + "-" + getMonth(beginDate) + "-"
                    + getDay(beginDate);
            String eymd = getYear(endDate) + "-" + getMonth(endDate) + "-" + getDay(endDate);
            if (bymd.equals(eymd))
                return true;
            else
                return false;
        } catch (Exception ex) {
        	ex.printStackTrace();
            return false;
        }
    }

    /**
     * 当月第一天
     * @return "2014-01-01 00:00:00"
     */
    public static String getYearMonthFirstDay() {
        //当前月的第一天            
        Calendar cal = Calendar.getInstance();
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        Date beginTime = cal.getTime();
        String beginTime1 = dateFormat.format(beginTime) + " 00:00:00";
        return beginTime1;
    }

    /**
     * 当月最后一天
     * @return "2014-01-31 23:59:59"
     */
    public static String getYearMonthEndDay() {
        //当前月的最后一天            
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        Date endTime = cal.getTime();
        String endTime1 = dateFormat.format(endTime) + " 23:59:59";
        return endTime1;
    }
    
    /**
     * 将格式为yyyymmddHHmmss的字符串转换为格式(yyyy-mm-dd HH:mm:ss)的Date类型
     * @param date
     * @return
     */
    public static Date speFormatToDate(String date){
    	Date d = null;
    	if(date == null){
    		return null;
    	}
    	date = StringUtils.trim(date);
        if (date.length() != 14) {
            return null;
        }
		try {
			d = toDate(toDatetimeString(speFormat.parse(date)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
    }
    
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    // 获取当前时间所在年的最大周数
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }
    
	public static void main(String[] args) {
//		System.out.println(toDatetimeString(1514875471000l));
//		System.out.println(toDatetimeString(new Date().getTime()));
//		System.out.println(getTimeString(1514875471000l, null));
//		System.out.println(getTimeString(new Date().getTime(), true));
//		System.out.println(getStringDate(1514864961000l));
//		System.out.println(getTimeString(-2208326743000l));
//		Integer startTime = getStartTime();
//		System.out.println(getStringTime(startTime));
		System.out.println(new Date().getTime());//1531368552695
		SimpleDateFormat timeFormat = new SimpleDateFormat(timePattern);
		try {
			System.out.println(timeFormat.format(timeFormat.parse("23:02:15")));
			System.out.println(timeFormat.parse("22:00:00").getTime());
			System.out.println(timeFormat.format(new Date(null)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		System.out.println(getMaxWeekNumOfYear(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()))));
//		
//		String format = new SimpleDateFormat("yyyy").format(new Date());
//		
//		System.out.println(getYearWeekFirstDay(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),29));
//		System.out.println(getYearWeekEndDay(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),29));
 	}
}
