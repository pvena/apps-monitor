package com.example.loginuse.log;

import java.util.Date;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;

public class LogConfiguration {

	private static LogConfiguration instance;
	private Context context;

	
	public static String LOCATIONGPSENABLED = "LOCATIONGPSENABLED"; //false
	public static String LOCATIONINTERVAL = "LOCATIONINTERVAL"; //900000
	public static String LOCATIONMINDISTANCE = "LOCATIONMINDISTANCE"; //200
	
	public static String ACTIVITYMINCCONFIDENCE = "ACTIVITYMINCCONFIDENCE"; //80
	public static String ACTIVITYMILISECONDSPERSSECOND = "ACTIVITYMILISECONDSPERSSECOND"; //1000
	public static String ACTIVITYDETACTIONINTERVALSECONDS = "ACTIVITYDETACTIONINTERVALSECONDS"; //60

	private LogConfiguration(){
	}
	
	public static LogConfiguration getInstance()
	{			
		if(LogConfiguration.instance != null){			
			return LogConfiguration.instance;
		}
		LogConfiguration.instance = new LogConfiguration();
		return LogConfiguration.instance;
	}
	
	public void setContext(Context c){
		this.context = c;
	}
	public Context getContext(){
		return this.context;
	}	
	
	public int getProperty(String key,int defValue){
		if(this.context != null)
			return PreferenceManager.getDefaultSharedPreferences(this.context).getInt(key, defValue);
		return defValue;
	}
	public String getProperty(String key,String defValue){
		if(this.context != null)
			return PreferenceManager.getDefaultSharedPreferences(this.context).getString(key, defValue);
		return defValue;
	}
	public boolean getProperty(String key,boolean defValue){
		if(this.context != null)
			return PreferenceManager.getDefaultSharedPreferences(this.context).getBoolean(key, defValue);
		return defValue;
	}
	
	public void setProperty(String key,int value){
		Editor ed = PreferenceManager.getDefaultSharedPreferences(this.context).edit(); 
		ed.putInt(key, value);
		ed.commit();
	}
	public void setProperty(String key,String value){
		Editor ed = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
		ed.putString(key, value);
		ed.commit();
	}
	public void setProperty(String key,boolean value){
		Editor ed = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
		ed.putBoolean(key, value);
		ed.commit();
	}
	
	public String getFileLogName(){
		String date = DateFormat.format(LogConstants.FILEDATEFORMAT, new Date()).toString();
        String fileName = LogConstants.LOG_FILE_NAME.replace("@", date); 
        return fileName;
	}
	public String getFileLogName(Date d){
		String date = DateFormat.format(LogConstants.FILEDATEFORMAT, d).toString();
        String fileName = LogConstants.LOG_FILE_NAME.replace("@", date); 
        return fileName;
	}
}