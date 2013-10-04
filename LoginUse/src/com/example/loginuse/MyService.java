package com.example.loginuse;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

//import com.example.loginuse.listeners.GoogleActivityLisener;
import com.example.loginuse.listeners.PassiveLocationChangedListener;
import com.example.loginuse.receivers.BluetoothReciver;
import com.example.loginuse.receivers.ConnectionChangeReceiver;
import com.example.loginuse.receivers.WifiReceiver;

public class MyService extends Service  {
	private static final String TAG = "MyService";
		
	public static final int id = 1234;
	
	private ConnectionChangeReceiver connectionChangeReceiver;
	private WifiReceiver wifiReceiver;
	private BluetoothReciver bluetooth;
	private LocationManager locationManager;	
	private PassiveLocationChangedListener locationListener;
	//private GoogleActivityLisener activityLisener;
		
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {		
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		unRegisterReceivers();
		locationManager.removeUpdates(locationListener);
		stopForeground(true);
		//restart the service
		Intent serviceIntent = new Intent();
	    serviceIntent.setAction("com.example.loginuse.MyService");
	    startService(serviceIntent);

	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		//Initializing receivers
		connectionChangeReceiver = new ConnectionChangeReceiver();
		wifiReceiver = new WifiReceiver();
		bluetooth = new BluetoothReciver();
		
		//Register all receivers
		registerReceivers();
		//Register for location updates
		registerForLocationUpdates();
		//Register for ActivityLisener
		//registerActivityLisener();
		//The intent to launch when the user clicks the expanded notification
		Intent intenta = new Intent(this, MyService.class);
		intenta.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intenta, 0);

		//This constructor is deprecated. Use Notification.Builder instead
		Notification notice = new Notification(R.drawable.ic_launcher, "Login Use", System.currentTimeMillis());

		//This method is deprecated. Use Notification.Builder instead.
		notice.setLatestEventInfo(this, "Login Use", "Login use is executing", pendIntent);

		notice.flags |= Notification.FLAG_NO_CLEAR;
		startForeground(id, notice);
		return START_STICKY;
	}
	
	/**
	 * Register a new locationlistener for location updates
	 */
	private void registerForLocationUpdates(){
		locationListener = new PassiveLocationChangedListener();
		// Acquire a reference to the system Location Manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Register the listener with the Location Manager to receive
		// location updates (minTime = 15 minutes, minDistance = 100 meters)
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0/*900000*/,
				1, locationListener);
	}
	
	/**
	 * Register a new googleActivityLisener for Adtivity updates
	 */
	private void registerActivityLisener()
	{
		/*int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(new Activity());
		if(ConnectionResult.SUCCESS == resultCode)
		{
			
		}*/		
	}
	
	/**
	 * Register receivers
	 */
	private void registerReceivers(){
		registerReceiver(connectionChangeReceiver, connectionChangeReceiver.getFilter());
		registerReceiver(wifiReceiver, wifiReceiver.getFilter());
		registerReceiver(bluetooth, bluetooth.getFilter());
	}
	
	/**
	 * Unregister receivers
	 */
	private void unRegisterReceivers(){
		unregisterReceiver(connectionChangeReceiver);
		unregisterReceiver(wifiReceiver);
		unregisterReceiver(bluetooth);
		
	}
}