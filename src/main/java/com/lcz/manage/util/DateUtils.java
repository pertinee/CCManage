package com.lcz.manage.util;

import com.lcz.manage.util.exception.CCException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/** 年月日时分 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	/** 年月日时分秒 */
	public static final String YYYY_MM_DD_HH_MM_SS_ = "yyyy-MM-dd HH:mm:ss";
	/** 年月日时分秒 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";

	public static Date addYear(Date date,int amount){
		return add(date,Calendar.YEAR,amount);
	}
	
	public static Date addDate(Date date,int amount){
		return add(date,Calendar.DATE,amount);
	}
	
	public static Date addHour(Date date,int amount){
		return add(date,Calendar.HOUR,amount);
	}
	
	public static Date addMinute(Date date,int amount){
		return add(date,Calendar.MINUTE,amount);
	}
	
	public static Date addSecond(Date date,int amount){
		return add(date,Calendar.SECOND,amount);
	}
	
	public static Date addMonth(Date date,int amount){
		return add(date,Calendar.MONTH,amount);
	}
	
	public static Date add(Date date,Integer field,int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 *	将日期设置为00：00：00
	 * @param date
	 * @return
	 */
	public static Date startDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MINUTE,0);
		Date startDate = cal.getTime();
		return startDate;
	}
	
	/**
	 *	将日期设置为23：59：59
	 * @param date
	 * @return
	 */
	public static Date endDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.MINUTE,59);
		Date endDate = cal.getTime();
		return endDate;
	}
	
	/**
	 * 获取系统真实日期
	 * 
	 * @return
	 */
	public static Date getSystemDay() {
		return new Date();
	}

	/**
	 * 根据日期返回 yyyy-MM-dd hh:mm:ss格式
	 * 
	 * @return
	 */
	public static String getDateFormat(Date date){
		SimpleDateFormat sdmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdmt.format(date);
	}
	
	/**
	 * 返回指定的日期格式
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getDateFormat(Date date, String formatStr){
		SimpleDateFormat sdmt = new SimpleDateFormat(formatStr);
		return sdmt.format(date);
	}
	
	/**
	 * 返回yyyyMMddHHmmssSSS 格式的系统时间
	 * @return
	 */
	public static String getEnSysDay() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(getSystemDay());
	}
	
	/**
	 * 返回HHmmss 格式的营业日
	 * 
	 * @return
	 */
	public static Integer getCNBusiTimeFormat(Long orgId) {
		return new Integer(getSysTime());
	}

	public static int calculateHoure(Date now, Date oldDate) {
		Long ss = now.getTime() - oldDate.getTime();
		Long result = ss / 1000 / 60 / 60;
		return result.intValue();
	}

	/**
	 * date转化yyyyMMdd 格式
	 * 
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat sdmt = new SimpleDateFormat("yyyyMMdd");
		return sdmt.format(date);
	}
	
	public static String formatDate(Date date,String formatStr){
		SimpleDateFormat sdmt = new SimpleDateFormat(formatStr);
		return sdmt.format(date);
	}

	/**
	 * yyyyMMdd 转yyyy/MM/dd date转化yyyy/MM/dd 格式
	 * 
	 * @return
	 */
	public static String formatDate(Integer date) {
		String _date = String.valueOf(date);
		return _date.substring(0, 4) + "/" + _date.substring(4, 6) + "/"
				+ _date.substring(6, 8);
	}
	
	/**
	 * yyyyMMdd 转yyyy-MM-dd date转化yyyy-MM-dd 格式
	 * 
	 * @return
	 */
	public static String formatCNDate(Integer date){
		
		String _date = String.valueOf(date);
		return _date.substring(0, 4) + "-" + _date.substring(4, 6) + "-"
				+ _date.substring(6, 8);
	}
	public static String formatTimeToMinute(Integer time) {
		if(time.compareTo(0) ==0 ) {
			return "00";
		}
		String _time = "000000"+String.valueOf(time);
		
		_time =_time.substring(_time.length()-6,_time.length());
		return _time.substring(0, 2) + ":" + _time.substring(2, 4);
	}

	public static String formatTime(Integer time) {
		if(time.compareTo(0) ==0 ) {
			return "00";
		}
		String _time = "000000"+String.valueOf(time);
		
		_time =_time.substring(_time.length()-6,_time.length());
		return _time.substring(0, 2) + ":" + _time.substring(2, 4)+ ":"+_time.substring(4, 6);
	}

	/**
	 * 计算2个日期的间隔天数，算头不算尾算法
	 * @param bDate
	 * @param eDate
	 * @return
	 */
	public static int daysBetween(int bDate,int eDate){
		String smdate = formatDate(bDate);
		String bdate = formatDate(eDate);
		
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");  
        Calendar cal = Calendar.getInstance();    
        try {
			cal.setTime(sdf.parse(smdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}    
        long time1 = cal.getTimeInMillis();                 
        try {
			cal.setTime(sdf.parse(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
	}  


	/**
	 * 日期月份加N个月
	 *
	 * @param datetime 日期(201411)
	 * @param n 加N个月
	 * @return
	 */
	public static String dateAddNmonth(String datetime, int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, n);
		date = cl.getTime();
		return sdf.format(date);
	}

	/**
	 * 日期减一天
	 * 
	 * @param _date
	 * @return
	 */
	public static Integer checkOption(String _date, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		Date date = null;
		try {
			date = (Date) sdf.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cl.setTime(date);
		cl.add(Calendar.DAY_OF_MONTH, days);

		date = cl.getTime();
		return Integer.parseInt(sdf.format(date));
	}
	
	/**
	 * 日期加N天
	 * 
	 * @param _date
	 * @return
	 */
	public static Integer addDays(Integer _date, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		Date date = null;
		try {
			date = (Date) sdf.parse(_date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cl.setTime(date);
		cl.add(Calendar.DAY_OF_MONTH, days);

		date = cl.getTime();
		return Integer.parseInt(sdf.format(date));
	}
	
	public static Date addDaysToDate(Integer _date, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		Date date = null;
		try {
			date = (Date) sdf.parse(_date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cl.setTime(date);
		cl.add(Calendar.DAY_OF_MONTH, days);

		date = cl.getTime();
		return date;
	}
	
	/**
	 * 把Date转换成INT 数据格式为:20160421
	 * @param date
	 * @return
	 */
	public static Integer dateToInt(Date date){
		String d= DateUtils.formatDate(date);
		return Integer.parseInt(d);
	}

	//获取某月的最后一天
	public static Integer getLastDayByMonth(String date) throws ParseException{
		try{
		  String dateStr = date.substring(0, 6)+"01";
		  Date d1 = DateUtils.addMonth(DateUtils.strToDate(dateStr), 1);
		  Date d = DateUtils.addDate(d1, -1);
		  String dStr = DateUtils.formatDate(d);
		  return new Integer(dStr.substring(6, 8));
		}catch(Exception e){
			 throw new CCException("日期解析错误",e);
		}
	}

	/**
	 * 时间
	 * 
	 * @param inVal
	 * @return
	 */
	public static long fromDateStringToLong(String inVal) { // 此方法计算时间毫秒
		Date date = null; // 定义时间类型
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			date = inputFormat.parse(inVal);
		} catch (ParseException e) {
			throw new CCException("日期格式错误", e);
		} // 将字符型转换成日期型
		return date.getTime();
	}
	
	
	public static void main(String[] agrs){
		System.out.println(getMonth(20160512,20160611));
	}

	/***
	 * 计算两个日期之间的月份数 算头不算尾
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMonth(Integer startDate, Integer endDate) {
		String smdate = formatDate(startDate);
		String bdate = formatDate(endDate);
		
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        int monthday;
        try {
            Date startDate1 = f.parse(smdate);
            //开始时间与今天相比较
            Date endDate1 = f.parse(bdate);

            Calendar starCal = Calendar.getInstance();
            starCal.setTime(startDate1);

            int sYear = starCal.get(Calendar.YEAR);
            int sMonth = starCal.get(Calendar.MONTH);
            int sDay = starCal.get(Calendar.DATE);

            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate1);
            int eYear = endCal.get(Calendar.YEAR);
            int eMonth = endCal.get(Calendar.MONTH);
            int eDay = endCal.get(Calendar.DATE);

            monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

            if (sDay <= eDay) {
                monthday = monthday + 1;
            }
            return monthday;
        } catch (ParseException e) {
            monthday = 0;
        }
        return monthday;
    }
	
	
	/**
	 * 减一是为了处理算头不算尾
	 * @param begindate
	 * @param term
	 * @return
	 */
	public static int getDayCountByTerm(Integer begindate, Integer term) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		Date date = null;
		try {
			date = (Date) sdf.parse(String.valueOf(begindate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cl.setTime(date);
		long _begindate = cl.getTimeInMillis();
		cl.add(Calendar.MONTH, term);

		long _enddate = cl.getTimeInMillis();

		long times = (_enddate - _begindate) / 1000 / 60 / 60 / 24 - 1;
		return (int) times;
	}

	/**
	 * 获取随机验证码有效期
	 * @param amount
	 * @return
	 */
	public static Date getValidTime(int amount) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(getSystemDay());
		cl.add(Calendar.SECOND, amount);
		return cl.getTime();
	}

	/**
	 * @Title: dateDiff
	 * @Description: 比较两个时间的差值(以秒为单位)
	 * @param date1
	 * @param date2
	 * @return
	 * @throws
	 */
	public static long dateDiff(Date date1, Date date2) {
		return date2.getTime() / 1000 - date1.getTime() / 1000; // 用立即数，减少乘法计算的开销
	}

	/**
	 * 获取日期
	 * 
	 * @param formatStr
	 *            日期格式
	 * @return 日期字符串
	 */
	public static String getNewDate(String formatStr) {
		DateFormat df = new SimpleDateFormat(formatStr);
		Date date = getSystemDay();
		String str = df.format(date);
		return str;
	}
	
	
	/**
	 * 获取HHmmss时间格式
	 * @return
	 */
	public static String getSysTime() {
		DateFormat df = new SimpleDateFormat("HHmmss");
		return df.format(getSystemDay());
	}

	
	/**
	 * 返回系统24H小时
	 * @return
	 */
	public static String getSysHour() {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(getSystemDay());
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    if(hour == 0) {
			return "00";
		}
	    return Integer.toString(hour);
	}
	
	/**
	 * 返回系统分
	 * @return
	 */
	public static String getSysMinute() {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(getSystemDay());
	    int minute  = calendar.get(Calendar.MINUTE);
	    if(minute == 0) {
			return "00";
		}
	    
	    return Integer.toString(minute);
	}
	
	/**
	 * 返回当前日期和三个月之前的日期 
	 * @return
	 */
	public static Object[] currentIntervalThree(){
		Date currentDay = new Date();//当前时间
		Date beforeDay = new Date();//3个月前时间
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(currentDay);//把当前时间赋给日历
		calendar.add(Calendar.MONTH, -3); //设置为前3月
		beforeDay = calendar.getTime(); //得到前3月的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		String currentDayStr = sdf.format(beforeDay); //格式化前3月的时间
		String defaultEndDateStr = sdf.format(currentDay); //格式化当前时间
		return new Object[]{currentDayStr,defaultEndDateStr};
	}
	
	/**
	 * 字符串日期格式转化为date
	 * @return
	 * @throws ParseException 
	 */
	public static Date strToDate(String dateStr) throws ParseException {
		DateFormat fmt =new SimpleDateFormat("yyyyMMdd");            
        Date date = fmt.parse(dateStr);
        return date;
	}
	/**
	 * 获取结束日期
	 * @param startDate
	 * @param increment
	 * @param unit
	 * @return
	 */
	public static Integer getEndDate(Integer startDate,Integer increment,String unit){
		String datestr = formatDate(startDate);
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat f1 = new SimpleDateFormat("yyyyMMdd");
		Integer endDate=0;
		try {
			Date date=f.parse(datestr);
			Calendar starCal = Calendar.getInstance();
            starCal.setTime(date);
            if(ZERO.equals(unit)){
            	starCal.add(Calendar.DAY_OF_YEAR, increment);
            }
            if(ONE.equals(unit)){
            	starCal.add(Calendar.MONTH,increment);
            	starCal.add(Calendar.DAY_OF_YEAR,-1);
            }
            if(TWO.equals(unit)){
            	starCal.add(Calendar.MONTH,3*increment);
            	starCal.add(Calendar.DAY_OF_YEAR,-1);
            }
            if(THREE.equals(unit)){
            	starCal.add(Calendar.YEAR,increment);
            	starCal.add(Calendar.DAY_OF_YEAR,-1);
            }
            String dateRe=f1.format(starCal.getTime());
            endDate=Integer.parseInt(dateRe);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}
	/**
	 * 将日期格式转化为yyyy-MM-dd格式的字符串
	 * @return
	 */
	public static String dateToString(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static Integer CalToInteger(Calendar cal){
		String s = CalToString(cal,"yyyyMMdd");
		return Integer.parseInt(s);
	}
	
	public static String CalToString(Calendar cal,String pattern){
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(cal.getTime());
	}
	
	public static Date strToDate(String dateStr,String formatStr) throws ParseException{
		DateFormat df = new SimpleDateFormat(formatStr);
		return df.parse(dateStr);
	}

	/**
	 * 把Long类型数据转换成yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String getDateForLong(Long time){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = new Date(time);
		String sDateTime = sdf.format(dt);
		return sDateTime;
	}
	
	/**
	 * 日期字符转换日期格式
	 * @param source
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String source, String pattern) throws ParseException {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.parse(source);
	}
	/**
	 * 
	 * @Description: yyyyMMdd 转yyyy/MM/dd date转化yyyy-MM-dd 格式   
	 * @parameter：@param _date
	 * @parameter：@return
	 * @return：String        
	 * @throws
	 */
	public static String formatStrDate(String _date) {
		return _date.substring(0, 4) + "-" + _date.substring(4, 6) + "-"
				+ _date.substring(6, 8);
	}
	
	/**
	 * 
	 * @Description: HHmmss  转HH:mm:ss date转化HH:mm:ss 格式   
	 * @parameter：@param _time
	 * @parameter：@return
	 * @return：String        
	 * @throws
	 */
	public static String formatStrTime(String _time) {
		return _time.substring(0, 2) + ":" + _time.substring(2, 4) + ":"
				+ _time.substring(4, 6);
	}
}
