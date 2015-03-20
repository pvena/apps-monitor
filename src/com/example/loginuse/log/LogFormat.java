package com.example.loginuse.log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import android.text.format.DateFormat;
import android.util.Base64;
import android.widget.TextView;

import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.location.LogLocationManager;
import com.example.loginuse.rule.LogRuleManager;


public class LogFormat {
	
	public static String getCurrentDate(){
		return DateFormat.format("yyyyMMdd", new Date()).toString();
	}
	public static String getLog(String property,String value)
	{
		return LogConstants.PROPERTYFORMAT.replaceFirst("@P@", property).replaceFirst("@V@", value);		
	}
	public static String getLog(String property,int value)
	{
		return LogConstants.PROPERTYFORMAT.replaceFirst("@P@", property).replaceFirst("@V@", LogFormat.getValue(value));		
	}
	public static String getLog(String property,double value)
	{
		return LogConstants.PROPERTYFORMAT.replaceFirst("@P@", property).replaceFirst("@V@", LogFormat.getValue(value));
	}
	public static String getLog(String property,boolean value)
	{		
		return LogConstants.PROPERTYFORMAT.replaceFirst("@P@", property).replaceFirst("@V@", LogFormat.getValue(value));		
	}	
	public static String getCategory(String value)
	{		
		return LogConstants.CATEGORYFORMAT.replaceFirst("@V@", value);		
	}
	public static String getLogCategories(String value)	
	{
		return value.replace(LogConstants.EXC, LogConstants.END);
	}
	public static int getTextViewInt(TextView tv){
		return Integer.parseInt(tv.getText().toString());
	}
	
	public static String getValue(Date value ){
		return DateFormat.format(LogConstants.DATEFORMAT, value).toString();
	}
	public static String getValue(int value){
		return String.valueOf(value);
	}
	public static String getValue(double value){
		return String.valueOf(value).replace(",", ".");
	}
	public static String getValue(boolean value){
		return ((value)?"1":"0");
	}
	public static String getValue(String value){
		return value;
	}
	
	public static String getFileToBase64Encode(File file) throws IOException {
		int size = (int) file.length();
	    byte[] bytes = new byte[size];
	    try {
	        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
	        buf.read(bytes, 0, bytes.length);
	        buf.close();
	    } catch (IOException e) {}
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
	
	public static void setInfoReceived(String info)
	{
		String[] infoData = info.split("\\|");
		String type = "";
		String data = "";
		String[] split;
		for(int i=0;i<(infoData.length);i++ ){
			split = infoData[i].split(":"); 
			type = split[0];
			data = split[1];
			if (type.equals("LocationGroups")){
				LogLocationManager.getInstance().setLocationGroups(data);
			}
			if (type.equals("Rules")){
				LogRuleManager.getInstance().setRules(data);
			}	
		}
	}	
	
	
}
