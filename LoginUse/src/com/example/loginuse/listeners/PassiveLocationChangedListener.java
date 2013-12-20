package com.example.loginuse.listeners;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;
import com.example.loginuse.receivers.GeneralLoggingReceiver;

/**
 * Class used to listen and save the user location
 *
 */
public class PassiveLocationChangedListener extends GeneralLoggingReceiver implements FusedLocationListener.LocationListener{
	
	private static String lastLog; 
	
	private FusedLocationListener locationListener;
	
	@Override
	public void initialize() {
		
		locationListener = FusedLocationListener.getInstance(LogConfiguration.getInstance().getContext(), this);             

	    locationListener.start();
	}

	@Override
	public void finalize() {
		locationListener.stop();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String, int, android.os.Bundle)
	 */
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
		
	/*
	 * (non-Javadoc)
	 * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	public void onProviderEnabled(String provider) {
	}
		
	/*
	 * (non-Javadoc)
	 * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onReceiveLocation(Location location) {
		String newLog = LogFormat.getLog(LogConstants.LATITUDE,location.getLatitude());
		newLog += LogFormat.getLog(LogConstants.LONGITUDE,location.getLongitude()); 
		newLog += LogFormat.getLog(LogConstants.ALTITUDE, location.getAltitude());
		
		LogCommandManager.getInstance().newState(LogConstants.LOCATION_STATE_TAG + "-" + LogConstants.LATITUDE, LogFormat.getValue(location.getLatitude()));
		LogCommandManager.getInstance().newState(LogConstants.LOCATION_STATE_TAG + "-" + LogConstants.LONGITUDE, LogFormat.getValue(location.getLongitude()));
		LogCommandManager.getInstance().newState(LogConstants.LOCATION_STATE_TAG + "-" + LogConstants.ALTITUDE, LogFormat.getValue(location.getAltitude()));
		
		if(!newLog.equals(lastLog)){
			LogLine l = new LogLine(newLog, LogConstants.LOCATION_STATE_TAG);
			LogSave.getInstance().saveData(l);
			lastLog = newLog;
		}
	}
	
	@Override
	public void logEvent(Context context, Intent intent) {
		
	}
	
}