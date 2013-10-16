package com.example.loginuse.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.os.Environment;
import android.util.Log;

public class LogConfiguration implements Serializable{
	
	private static LogConfiguration instance;
	
	private boolean GPSEnabled = false;
	private int LocationInterval = 900000;
	private int LocationMinDistance = 200;
	private int MinConfidence = 80;
	private int MilisecondsPerSecond = 1000;
	private int DetactionIntervalSeconds = 60;

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
	
	public boolean getLocationGPSEnabled(){
		return this.GPSEnabled;
	}
	public void setLocationGPSEnabled(boolean value){
		this.GPSEnabled = value;
	}	
	public int getLocationInterval()
	{
		return this.LocationInterval;
	}
	public void setLocationInterval(int value){
		this.LocationInterval = value;
	}
	public int getLocationMinDistance()
	{
		return this.LocationMinDistance;
	}
	public void setLocationMinDistance(int value){
		this.LocationMinDistance = value;
	}
	public int getActivityMinConfidence()
	{
		return this.MinConfidence;
	}
	public void setActivityMinConfidence(int value){
		this.MinConfidence = value;
	}
	public int getActivityMilisecondsPerSecond()
	{
		return this.MilisecondsPerSecond;
	}
	public void setActivityMilisecondsPerSecond(int value){
		this.MilisecondsPerSecond = value;
	}
	public int getActivityDetactionIntervalSeconds()
	{
		return this.DetactionIntervalSeconds;
	}
	public void setActivityDetactionIntervalSeconds(int value){
		this.DetactionIntervalSeconds = value;
	}

	
	public static boolean serializeConfig()
	{
	    
	    File file;
	    File root = Environment.getExternalStorageDirectory();
	    FileOutputStream fileOutputStream = null;
	    ObjectOutputStream objectOutputStream = null;
        try {
        	
        	File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
        	directory.mkdirs();
         	file = new File(directory,LogConstants.LOG_CONFIG_FILE);
            if(!file.exists()) {	                        
              	file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(LogConfiguration.instance);
            objectOutputStream.close();
	        
	        return true;
	    } catch(IOException ioe) {
	        Log.e("serializeObject", "error", ioe);
	        return false;
	    }
	}
	public static void deserializeConfig(){
		File file;
	    File root = Environment.getExternalStorageDirectory();
	    try {
	    	File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
        	directory.mkdirs();
         	file = new File(directory,LogConstants.LOG_CONFIG_FILE);
         	
         	FileInputStream fileinputStrean = new FileInputStream(file);         	
	        ObjectInputStream in = new ObjectInputStream(fileinputStrean);
	        LogConfiguration.instance = (LogConfiguration)in.readObject();
	        in.close();
	        
	      } catch(ClassNotFoundException cnfe) {
	        Log.e("deserializeObject", "class not found error", cnfe);

	      } catch(IOException ioe) {
	        Log.e("deserializeObject", "io error", ioe);

	      }
	}
}