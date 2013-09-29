package com.example.loginuse.util;

public class LogFormat {
	
	public static String getLog(String property,String value)
	{
		return property + Constants.SEP  + value + Constants.PSEP;
	}
	public static String getLog(String property,int value)
	{
		return property + Constants.SEP + value + Constants.PSEP;
	}
	public static String getLog(String property,double value)
	{
		return property + Constants.SEP + value + Constants.PSEP;
	}
	public static String getLog(String property,boolean value)
	{
		return property + Constants.SEP + ((value)?"1":"0") + Constants.PSEP;
	}	
	public static String getCategory(String value)
	{
		return Constants.CATSEP1 + value + Constants.CATSEP2 + Constants.CATSEP;
	}
	public static String getLogCategories(String value)	
	{
		String exc = Constants.PSEP + Constants.CATSEP2 + Constants.CATSEP; 
		return value.replace(exc, Constants.CATSEP2);
	}
}
