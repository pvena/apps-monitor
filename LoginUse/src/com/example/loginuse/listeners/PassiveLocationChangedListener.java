package com.example.loginuse.listeners;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;
import com.example.loginuse.receivers.GeneralLoggingReceiver;

/**
 * Class used to listen and save the user location
 *
 */
public class PassiveLocationChangedListener extends GeneralLoggingReceiver implements LocationListener{
	
	private static String lastLog; 
	private LocationManager locationManager;
	
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
		
	@Override
	public void logEvent(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		
		locationManager = (LocationManager)LogConfiguration.getInstance().getContext().getSystemService(Context.LOCATION_SERVICE);

		// Register the listener with the Location Manager to receive
		// location updates (minTime = 15 minutes, minDistance = 100 meters)

		String locationType= (LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGPSENABLED, false))?
					LocationManager.GPS_PROVIDER :
					LocationManager.NETWORK_PROVIDER;
		
		locationManager.requestLocationUpdates(locationType, 
				LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONINTERVAL, 900000),
				LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONMINDISTANCE, 200), 
				this);
	}

	@Override
	public void finalize() {
		locationManager.removeUpdates(this);
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