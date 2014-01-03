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

import com.example.loginuse.command.LogCommandRule;
import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;


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
				LogFormat.setLocationGroups(data);
			}
			if (type.equals("Rules")){
				LogFormat.setRules(data);
			}	
		}
	}
	
	private static void setLocationGroups(String LocationData){
		String[] data = LocationData.split("&");
		LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGROUPCOUNT, data.length / 4);
		for(int i=0;i<(data.length);i+=4 ){
			LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGROUPBASE + i/4  , data[i]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.LATITUD + i/4  , Float.valueOf(data[i+1]));
			LogConfiguration.getInstance().setProperty(LogConfiguration.LONGITUD + i/4 , Float.valueOf(data[i+2]));
			LogConfiguration.getInstance().setProperty(LogConfiguration.COUNT + i/4 , Integer.valueOf(data[i+3]));
		}
	}
	
	public static Hashtable<String, LogLocationGroup> getLocationGroup(){
		int count = LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGROUPCOUNT, 0);
		Hashtable<String, LogLocationGroup> groups = new Hashtable<String, LogLocationGroup>();
		for(int i=0; i < count ; i++){
			LogLocationGroup lg = new LogLocationGroup();
			lg.setName(LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGROUPBASE + i, "Group" + i));
			lg.setLatitud(LogConfiguration.getInstance().getProperty(LogConfiguration.LATITUD + i, 0.0f));
			lg.setLongitud(LogConfiguration.getInstance().getProperty(LogConfiguration.LONGITUD + i, 0.0f));
			lg.setCount(LogConfiguration.getInstance().getProperty(LogConfiguration.COUNT + i, 0));			
			groups.put(lg.getName(), lg);
		}
		return groups;
	}
	
	private static void setRules(String RulesData){
		String[] data = RulesData.split("&");
		LogConfiguration.getInstance().setProperty(LogConfiguration.RULECOUNT, data.length / 4);
		for(int i=0;i<(data.length);i+=4){
			LogConfiguration.getInstance().setProperty(LogConfiguration.IDRULE + i/4  , data[i]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.COMMANDKEY + i/4  , data[i+1]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.PROPERTYKEY + i/4 , data[i+2]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.PROPERTYVALUE + i/4 , data[i+3]);
		}
	}	
	
	public static Hashtable<String,LogCommandRule> getRules(){
		int count = LogConfiguration.getInstance().getProperty(LogConfiguration.RULECOUNT, 0);
		Hashtable<String, LogCommandRule> rules = new Hashtable<String, LogCommandRule>();
		String id = "";
		String commandKey = "";
		String prop = "";
		String val = "";
		LogCommandRule rule = null;
		for(int i=0; i < count ; i++){
			id = LogConfiguration.getInstance().getProperty(LogConfiguration.IDRULE + i, "-1");
			commandKey = LogConfiguration.getInstance().getProperty(LogConfiguration.COMMANDKEY + i, "Command" + i);
			prop = LogConfiguration.getInstance().getProperty(LogConfiguration.PROPERTYKEY + i, "-");
			val = LogConfiguration.getInstance().getProperty(LogConfiguration.PROPERTYVALUE + i, "-");			
 			if(rules.containsKey(id)){
				rule = rules.get(id);
			}
			else{
				rule = new LogCommandRule(commandKey);
				rules.put(id, rule);
			}
			rule.addCondition(prop, val);
		}
		return rules;
	} 
}
