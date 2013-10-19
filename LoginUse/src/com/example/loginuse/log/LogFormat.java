package com.example.loginuse.log;

import com.example.loginuse.R;

import android.widget.CheckBox;
import android.widget.TextView;


public class LogFormat {
	
	public static String getLog(String property,String value)
	{
		return property + LogConstants.SEP  + value + LogConstants.PSEP;
	}
	public static String getLog(String property,int value)
	{
		return property + LogConstants.SEP + value + LogConstants.PSEP;
	}
	public static String getLog(String property,double value)
	{
		return property + LogConstants.SEP + value + LogConstants.PSEP;
	}
	public static String getLog(String property,boolean value)
	{
		return property + LogConstants.SEP + ((value)?"1":"0") + LogConstants.PSEP;
	}	
	public static String getCategory(String value)
	{
		return LogConstants.CATSEP1 + value + LogConstants.CATSEP2 + LogConstants.CATSEP;
	}
	public static String getLogCategories(String value)	
	{
		String exc = LogConstants.PSEP + LogConstants.CATSEP2 + LogConstants.CATSEP; 
		return value.replace(exc, LogConstants.CATSEP2);
	}
	public static int getTextViewInt(TextView tv){
		return Integer.parseInt(tv.getText().toString());
	}
	public static boolean getCheckBoxBoolean(CheckBox cbx){
		return cbx.isChecked();
	}
	
}
