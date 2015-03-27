package com.example.loginuse;

import java.util.ArrayList;

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
import com.example.loginuse.receivers.GeneralLoggingReceiver;
import com.example.loginuse.receivers.GpsChangeReceiver;
import com.example.loginuse.receivers.HeadsetPlugReceiver;
import com.example.loginuse.receivers.RingerReceiver;
import com.example.loginuse.receivers.WifiReceiver;

public class LogService extends Service  {
	private static final String TAG = "LogService";
		
	public static final int id = 1234;
	
	private ArrayList<GeneralLoggingReceiver> receivers;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {		
		LogConfiguration.getInstance().setContext(this);
		this.receivers = new ArrayList<GeneralLoggingReceiver>();
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		
		unRegisterReceivers();
		
		stopForeground(true);
		//restart the service
		Intent serviceIntent = new Intent();
	    serviceIntent.setAction("com.example.loginuse.LogService");
	    startService(serviceIntent);

	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		
		//Initializing receivers
		this.receivers.add(new ConnectionChangeReceiver());
		this.receivers.add(new WifiReceiver());
		this.receivers.add(new BluetoothReciver());
		this.receivers.add(new PassiveLocationChangedListener());
		this.receivers.add(new ActivityReceiver());
		this.receivers.add(new GpsChangeReceiver());
		this.receivers.add(new RingerReceiver());
		this.receivers.add(new HeadsetPlugReceiver());
		this.receivers.add(new BatteryPowerReceiver());

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
		for(int i=0;i<this.receivers.size();i++)
			this.receivers.get(i).initialize();
	}
	
	/**
	 * Unregister receivers
	 */
	private void unRegisterReceivers(){
		for(int i=0;i<this.receivers.size();i++)
			this.receivers.get(i).initialize();
	}
}