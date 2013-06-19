package com.example.loginuse.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.Constants;

/**
 * Broadcast receiver used to find and save the user location
 *
 */
public class PassiveLocationChangedReceiver extends BroadcastReceiver{
	
	/**
	 * Action heard for this receiver 
	 */
	public static String CHECK_LOCATION = "com.example.loginuse.check_location";
	
	private LocationManager locationManager;
	
	private static Location lastLocation = null; 
	
	/**
	 * Creator
	 */
	public PassiveLocationChangedReceiver(){
	}

	/**
	 * 
	 * @param locationNew
	 * @return
	 */
	private boolean isLocationChange(Location locationNew){
		if(lastLocation != null){
			float accuracy = lastLocation.getAccuracy();
			float difference = lastLocation.distanceTo(locationNew);
			if(difference > accuracy){
				return true;
			}
			return false;
		}
		return true;
	}

	/**
	 *  Define a listener that responds to location updates
	 */
	LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			// Called when a new location is found by the network location
			// provider.
			if(isLocationChange(location)){
				lastLocation = location;
				LsLog log = new LsLog("Latitude:" + location.getLatitude()
						+ ", Longitude:" + location.getLongitude() 
						+ ", Altitude:" + location.getAltitude(),
						Constants.LOCATION_STATE_TAG);
				SaveLog.getInstance().saveData(log);
				if(locationManager != null){
					locationManager.removeUpdates(locationListener);
					locationManager = null;
				}
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
	};
	
	/*
	 * (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// Acquire a reference to the system Location Manager
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		// Register the listener with the Location Manager to receive
		// location updates
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
				0, locationListener);
		
	}
}