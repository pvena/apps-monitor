package com.example.loginuse.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.example.loginuse.log.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;

/**
 * Class used to listen and save the user location
 *
 */
public class PassiveLocationChangedListener implements LocationListener{
	
	private static String lastLog; 
	
	public void onLocationChanged(Location location) {
		
		String newLog = LogFormat.getLog(LogConstants.LATITUDE,location.getLatitude())
				+ LogFormat.getLog(LogConstants.LONGITUDE,location.getLongitude()) 
				+ LogFormat.getLog(LogConstants.ALTITUDE, location.getAltitude());
		if(!newLog.equals(lastLog))
		{
			LogLine l = new LogLine(newLog, LogConstants.LOCATION_STATE_TAG);
			LogSave.getInstance().saveData(l);
			lastLog = newLog;
		}
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
}