package com.example.loginuse.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.loginuse.Log.LogTags;
import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;

public class PassiveLocationChangedReceiver extends BroadcastReceiver implements IReceiver{

	private IntentFilter filter;

	public static String CHECK_LOCATION = "com.example.loginuse.check_location";
	
	private LocationManager locationManager;
	
	public PassiveLocationChangedReceiver(){
		filter = new IntentFilter(CHECK_LOCATION);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		LsLog log = new LsLog("Passive Location Called",LogTags.Location_Tag);
		SaveLog.getInstance().saveData(log);
		
		// Acquire a reference to the system Location Manager
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		// Register the listener with the Location Manager to receive
		// location updates
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
				0, locationListener);
	}

	@Override
	public IntentFilter getFilter() {
		return filter;
	}

	@Override
	public void addAction(String action) {
		filter.addAction(action);
		
	}
	
	// Define a listener that responds to location updates
	LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			// Called when a new location is found by the network location
			// provider.
			LsLog log = new LsLog("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude(),LogTags.Location_Tag);
			SaveLog.getInstance().saveData(log);
			locationManager.removeUpdates(locationListener);
			locationManager = null;
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	};
}