package com.example.loginuse.log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import android.text.format.DateFormat;
import android.util.Base64;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.google.android.gms.maps.model.LatLng;


public class LogFormat {
	
	public static String getCurrentDate(){
		return DateFormat.format("yyyyMMdd", new Date()).toString();
	}
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
	public static String getValue(Date value ){
		return DateFormat.format(LogConstants.DATEFORMAT, value).toString();
	}
	public static String getValue(int value){
		return String.valueOf(value);
	}
	public static String getValue(double value){
		return String.valueOf(value);
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
	
	public static void setLocationGroups(String LocationData){
		String[] data = LocationData.split("&");
		LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGROUPCOUNT, data.length / 3);
		for(int i=0;i<(data.length);i+=3 ){
			LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGROUPBASE + i/3  , data[i]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.LATITUD + i/3  , Float.valueOf(data[i+1]));
			LogConfiguration.getInstance().setProperty(LogConfiguration.LONGITUD + i/3 , Float.valueOf(data[i+2]));
		}
	}
	
	public static Hashtable<String, LatLng> getLocationGroup(){
		int count = LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGROUPCOUNT, 0);
		Hashtable<String, LatLng> groups = new Hashtable<String, LatLng>();
		for(int i=0; i < count ; i++){
			String name = LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGROUPBASE + i, "Group" + i);
			double latitud = LogConfiguration.getInstance().getProperty(LogConfiguration.LATITUD + i, 0.0f);
			double longitud = LogConfiguration.getInstance().getProperty(LogConfiguration.LONGITUD + i, 0.0f);
			LatLng latLong = new LatLng(latitud, longitud);
			groups.put(name, latLong);
		}
		return groups;
	}
}
