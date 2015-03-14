package com.example.loginuse.listeners;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.location.LogLocationGroup;
import com.example.loginuse.location.LogLocationManager;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.receivers.GeneralLoggingReceiver;

/**
 * Class used to listen and save the user location
 *
 */
public class PassiveLocationChangedListener extends GeneralLoggingReceiver implements FusedLocationListener.LocationListener{
	
	private FusedLocationListener locationListener;
	
	@Override
	public void initialize() {
		super.logType = LogConstants.LOCATION_STATE_TAG;
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
		LogLine l = new LogLine(LogConstants.LOCATION_STATE_TAG);
		
		l.addProperty(LogConstants.LATITUDE,location.getLatitude());
		l.addProperty(LogConstants.LONGITUDE,location.getLongitude());
		l.addProperty(LogConstants.ALTITUDE, location.getAltitude());
		
		LogLocationGroup group = LogLocationManager.getInstance().getBoundingGroup(location);
		
		if(group != null)
			l.addPropertyOnlyCommandManager(LogConstants.LOCATIONGROUP, group.getName());
		else
			l.addPropertyOnlyCommandManager(LogConstants.LOCATIONGROUP, "-");
		
		super.save(l);
	}
	
	@Override
	public void logEvent(Context context, Intent intent,LogLine l) {
		
	}
	
}