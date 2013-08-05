package com.example.loginuse;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.loginuse.receivers.BluetoothReciver;
import com.example.loginuse.receivers.ConnectionChangeReceiver;
import com.example.loginuse.receivers.WifiReceiver;

public class MyService extends Service {
	private static final String TAG = "MyService";
	
	public static final int id = 1234;
	
	private ConnectionChangeReceiver connectionChangeReceiver;
	private WifiReceiver wifiReceiver;
	private BluetoothReciver bluetooth;
		
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
		registerReceivers();
		
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