package com.example.loginuse.log;

public class LogFormat {
	
	private static String separator = "|";
	
	public static String getLog(String property,String value)
	{
		return property + separator + value + ";";
	}
	public static String getLog(String property,int value)
	{
		return property + separator + value + ";";
	}
	public static String getLog(String property,boolean value)
	{
		return property + separator + ((value)?"1":"0") + ";";
	}
}
