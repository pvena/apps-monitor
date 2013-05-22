package com.example.loginuse;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.loginuse.receivers.BatteryStatusReceiver;
import com.example.loginuse.receivers.BluetoothReciver;
import com.example.loginuse.receivers.ConnectionChangeReceiver;
import com.example.loginuse.receivers.MyPhoneStateListener;
import com.example.loginuse.receivers.WifiReceiver;

public class MyService extends Service {
	private static final String TAG = "MyService";
		
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
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Log.d(TAG, "onStart");
		registerReceivers();
	}
	
	private void registerReceivers(){
		BatteryStatusReceiver batteryStatusReceiver = new BatteryStatusReceiver();
		registerReceiver(batteryStatusReceiver, batteryStatusReceiver.getFilter());
		
		ConnectionChangeReceiver connectionChangeReceiver = new ConnectionChangeReceiver();
		registerReceiver(connectionChangeReceiver, connectionChangeReceiver.getFilter());
		
		WifiReceiver wifiReceiver = new WifiReceiver();
		registerReceiver(wifiReceiver, wifiReceiver.getFilter());
		
		MyPhoneStateListener SignalLisener = new MyPhoneStateListener(this.getApplicationContext());
		registerReceiver(wifiReceiver, SignalLisener.getFilter());
		
		BluetoothReciver bluetooth = new BluetoothReciver();
		registerReceiver(bluetooth, bluetooth.getFilter());
		
	}
}