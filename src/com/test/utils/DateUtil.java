package com.test.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {  
	 
	
    public static String format(Date value) {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
         String date = formatter.format(value);
         return date;
    }  
    
    public static String formatTime(Date value) {
    	  SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
          String date = formatter.format(value);
          return date;
    }
  
    public static Date StringToDate(String date) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
    	Date dat = null;
    	try {
			 dat = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return dat;
    }
    
    /**
     * 比较两个时间的大小
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");  
	       try {
	           Date dt1 = df.parse(DATE1);
	           Date dt2 = df.parse(DATE2);
	           if (dt1.getTime() > dt2.getTime()) {	             
	               return 1;
	           } else if (dt1.getTime() < dt2.getTime()) {
	              return -1;
	          } else {
	              return 0;
	          }
	      } catch (Exception exception) {
	          exception.printStackTrace();
	      }
	  return 0;
	  }

    /**
     * 计算两个时间之间相差的小时
     * @param startTime
     * @param endTime
     * @return
     */
    public static double betweenHours(String startTime, String endTime) {
    	DecimalFormat    df   = new DecimalFormat("#.00");   //将结果保留两位小数
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
    	Calendar cal = Calendar.getInstance();    
    	long time1 = 0;
    	long time2 = 0;
	    try {
	    	 cal.setTime(sdf.parse(startTime));   
             time1 = cal.getTimeInMillis();    
             cal.setTime(sdf.parse(endTime)); 
             time2 = cal.getTimeInMillis();  
	      } catch (Exception exception) {
	          exception.printStackTrace();
	      }
	    String between_hours =df.format(((time2-time1)/(double)(1000*60*60)));  
	   return Double.parseDouble(between_hours);
	  }
}  