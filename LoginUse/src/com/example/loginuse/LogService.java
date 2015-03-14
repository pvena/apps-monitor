package com.example.loginuse;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.listeners.PassiveLocationChangedListener;
import com.example.loginuse.receivers.ActivityReceiver;
import com.example.loginuse.receivers.BatteryPowerReceiver;
import com.example.loginuse.receivers.BluetoothReciver;
import com.example.loginuse.receivers.ConnectionChangeReceiver;
import com.example.loginuse.receivers.GpsChangeReceiver;
import com.example.loginuse.receivers.HeadsetPlugReceiver;
import com.example.loginuse.receivers.RingerReceiver;
import com.example.loginuse.receivers.WifiReceiver;

public class LogService extends Service  {
	private static final String TAG = "LogService";
		
	public static final int id = 1234;
	
	private ConnectionChangeReceiver connectionChangeReceiver;
	private WifiReceiver wifiReceiver;
	private BluetoothReciver bluetooth;	
	private PassiveLocationChangedListener locationListener;
	private ActivityReceiver activityReceiver;
	private GpsChangeReceiver gpsChangeReceiver;
	private RingerReceiver ringerReceiver;
	private HeadsetPlugReceiver headsetPlugReceiver;
	private BatteryPowerReceiver batteryPowerReceiver;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {		
		LogConfiguration.getInstance().setContext(this);
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
		this.connectionChangeReceiver = new ConnectionChangeReceiver();
		this.wifiReceiver = new WifiReceiver();
		this.bluetooth = new BluetoothReciver();
		this.locationListener = new PassiveLocationChangedListener();
		this.activityReceiver = new ActivityReceiver();
		this.gpsChangeReceiver = new GpsChangeReceiver();
		this.ringerReceiver = new RingerReceiver();
		this.headsetPlugReceiver = new HeadsetPlugReceiver();
		this.batteryPowerReceiver = new BatteryPowerReceiver();
		
		//Register all receivers
		registerReceivers();
		
		
		//The intent to launch when the user clicks the expanded notification
		Intent intenta = new Intent(this, LogService.class);
		intenta.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intenta, 0);

		//This constructor is deprecated. Use Notification.Builder instead
		Notification notice = new Notification(R.drawable.ic_launcher1, "Login Use", System.currentTimeMillis());

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
		this.connectionChangeReceiver.initialize();
		this.bluetooth.initialize();
		this.wifiReceiver.initialize();
		this.locationListener.initialize();
		this.activityReceiver.initialize();
		this.gpsChangeReceiver.initialize();
		this.ringerReceiver.initialize();
		this.headsetPlugReceiver.initialize();
		this.batteryPowerReceiver.initialize();
	}
	
	/**
	 * Unregister receivers
	 */
	private void unRegisterReceivers(){
		this.connectionChangeReceiver.finalize();
		this.bluetooth.finalize();
		this.wifiReceiver.finalize();
		this.locationListener.finalize();		
		this.activityReceiver.finalize();
		this.gpsChangeReceiver.finalize();
		this.ringerReceiver.finalize();
		this.headsetPlugReceiver.finalize();
		this.batteryPowerReceiver.finalize();
	}
}