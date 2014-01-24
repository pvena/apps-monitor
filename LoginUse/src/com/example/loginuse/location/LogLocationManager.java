package com.example.loginuse.location;

import java.util.ArrayList;
import java.util.Hashtable;

import android.location.Location;

import com.example.loginuse.configuration.LogConfiguration;


public class LogLocationManager {

	private static LogLocationManager instance;
	ArrayList<LogLocationGroup> groups;
	
	private LogLocationManager(){
		this.groups = new ArrayList<LogLocationGroup>();
		this.loadGroups();
	}
	
	public static LogLocationManager getInstance()
	{			
		if(LogLocationManager.instance != null){			
			return LogLocationManager.instance;
		}
		LogLocationManager.instance = new LogLocationManager();
		return LogLocationManager.instance;
	}
	
	/*
	 * Load Location Groups from configuration (Shared Preferences).
	 * */
	public void loadGroups(){
		int count = LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGROUPCOUNT, 0);
		for(int i=0; i < count ; i++){
			LogLocationGroup lg = new LogLocationGroup();
			lg.setName(LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGROUPBASE + i, "Group" + i));
			lg.setLatitud(LogConfiguration.getInstance().getProperty(LogConfiguration.LATITUD + i, 0.0f));
			lg.setLongitud(LogConfiguration.getInstance().getProperty(LogConfiguration.LONGITUD + i, 0.0f));
			lg.setCount(LogConfiguration.getInstance().getProperty(LogConfiguration.COUNT + i, 0));			
			this.groups.add(lg);
		}
	}
	
	/*
	 * Save LocationGroups in configuration (Shared Preferences). 
	 * */
	public void setLocationGroups(String LocationData){
		String[] data = LocationData.split("&");
		LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGROUPCOUNT, data.length / 4);
		for(int i=0;i<(data.length);i+=4 ){
			LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGROUPBASE + i/4  , data[i]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.LATITUD + i/4  , Float.valueOf(data[i+1]));
			LogConfiguration.getInstance().setProperty(LogConfiguration.LONGITUD + i/4 , Float.valueOf(data[i+2]));
			LogConfiguration.getInstance().setProperty(LogConfiguration.COUNT + i/4 , Integer.valueOf(data[i+3]));
		}
	}
	
	public Hashtable<String, LogLocationGroup> getLocationGroup(){	
		Hashtable<String, LogLocationGroup> groups = new Hashtable<String, LogLocationGroup>();
		LogLocationGroup lg = null;
		for(int i=0; i < this.groups.size() ; i++){
			lg = this.groups.get(i);			
			groups.put(lg.getName(), lg);
		}
		return groups;
	}
	
	private double getGroupDistence(double long1InDegrees, double lat1InDegrees, double long2InDegrees, double lat2InDegrees){
		double lats = (double)(lat1InDegrees - lat2InDegrees);
		double lngs = (double)(long1InDegrees - long2InDegrees);

        //Paso a metros
		double latm = lats * 60 * 1852; 
		double lngm = (lngs * Math.cos(lat1InDegrees * Math.PI / 180)) * 60 * 1852;
		double distInMeters = Math.hypot(latm, lngm);
        return distInMeters;
    }
	
	public LogLocationGroup getBoundingGroup(Location loc){
		LogLocationGroup gr = null;
		for(int i=0; i < this.groups.size(); i++)
			gr = this.groups.get(i);
			if(this.getGroupDistence(loc.getLongitude(), loc.getLatitude(), gr.getLongitud(), gr.getLatitud()) < 200){
				return gr;
			}
		return null;			
	}
}
