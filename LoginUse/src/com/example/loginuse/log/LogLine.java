package com.example.loginuse.log;

//import android.annotation.SuppressLint;
import java.util.Date;

import com.example.loginuse.configuration.LogConstants;

import android.text.format.DateFormat;


public class LogLine {
	private String message;
	private String type;
		
	public LogLine (String mess, String t)
	{
		this.message = mess;
		this.type = t;
	}
	public String getMessage()
	{
		return this.message;
	}
	public String getType()
	{
		return this.type;
	}
	/**
	 * @return
	 */
	//@SuppressLint("SimpleDateFormat")
	public String getLog()
	{
		String date =  DateFormat.format(LogConstants.DATEFORMAT, new Date()).toString();
		return   LogFormat.getLogCategories(LogFormat.getCategory(date)
				.concat(LogFormat.getCategory(this.type))
				.concat(LogFormat.getCategory(this.message)))
				.concat("\r\n");
	}
}