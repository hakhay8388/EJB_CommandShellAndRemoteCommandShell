package com.sanalstil.pUtils;



import java.text.ParseException;
import java.text.SimpleDateFormat;

public class cDate {
	public static String GetNow(String t_format) {
		SimpleDateFormat formatter = new SimpleDateFormat(t_format);
		return formatter.format(new java.util.Date());
	}
	public static java.util.Date ToDate(String t_date,String t_format) {
		SimpleDateFormat formatter = new SimpleDateFormat(t_format);
		java.util.Date date = null;
		try {
			date = formatter.parse(t_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public static String DateFormat(java.util.Date t_date,String t_format) {
		SimpleDateFormat formatter = new SimpleDateFormat(t_format);
		return formatter.format(t_date);
	}
	public static long GetTimestap(){
		return System.currentTimeMillis()/1000;
	}
	public static long GetTimestap(java.util.Date t_date){
		return t_date.getTime()/1000;
	}
}


