package com.tesis.appsmonitor;

import com.tesis.appsmonitor.util.BatteryStatusUtil;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Acquire a reference to the system Location Manager
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE) ;
		// Register the listener with the Location Manager to receive
		// location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	// Define a listener that responds to location updates
	LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			// Called when a new location is found by the network location
			// provider.
			Log.d("LOCATION CHANGE", "LATITUDE: " + location.getLatitude() + " " + "LONGITUDE: " + location.getLongitude()
					+ " BATTERY_STATUS " + BatteryStatusUtil.getBatteryPercentage(getApplicationContext())
					+ " " + BatteryStatusUtil.getBatteryStatus(getApplicationContext()));
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	};

}
