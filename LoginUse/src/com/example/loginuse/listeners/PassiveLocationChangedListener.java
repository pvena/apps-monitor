package com.example.loginuse.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.example.loginuse.log.LsLog;
import com.example.loginuse.log.SaveLog;
import com.example.loginuse.util.Constants;
import com.example.loginuse.util.LogFormat;

/**
 * Class used to listen and save the user location
 *
 */
public class PassiveLocationChangedListener implements LocationListener{
	
	private static String lastLog; 
	
	public void onLocationChanged(Location location) {
		
		String newLog = LogFormat.getLog(Constants.LATITUDE,location.getLatitude())
				+ LogFormat.getLog(Constants.LONGITUDE,location.getLongitude()) 
				+ LogFormat.getLog(Constants.ALTITUDE, location.getAltitude());
		if(!newLog.equals(lastLog))
		{
			LsLog l = new LsLog(newLog, Constants.LOCATION_STATE_TAG);
			SaveLog.getInstance().saveData(l);
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