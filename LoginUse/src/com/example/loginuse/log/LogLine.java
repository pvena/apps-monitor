package com.example.loginuse.log;

//import android.annotation.SuppressLint;
import java.util.Date;

import com.example.loginuse.command.LogCommandManager;
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
	public LogLine (String t)
	{		
		this.message = "";
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
	
	public void addProperty(String property,String value){
		String val = LogFormat.getValue(value).replace("&", "");
		this.message += LogFormat.getLog(property, val);		
		LogCommandManager.getInstance().newState(this.type + "-" + property, val);
	}
	public void addProperty(String property,int value){
		String val = LogFormat.getValue(value);
		this.message += LogFormat.getLog(property, val);		
		LogCommandManager.getInstance().newState(this.type + "-" + property, val);
		
	}
	public void addProperty(String property,double value){
		String val = LogFormat.getValue(value);
		this.message += LogFormat.getLog(property, val);		
		LogCommandManager.getInstance().newState(this.type + "-" + property, val);
	}
	public void addProperty(String property,boolean value){
		String val = LogFormat.getValue(value);
		this.message += LogFormat.getLog(property, value);		
		LogCommandManager.getInstance().newState(this.type + "-" + property, val);
	}	
	
	public void addPropertyOnlyCommandManager(String property,String value){	
		
		LogCommandManager.getInstance().newState(this.type + "-" + property, LogFormat.getValue(value));
	}
}