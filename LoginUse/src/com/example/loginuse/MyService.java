package com.example.loginuse;

import com.example.loginuse.Log.*;
import com.example.loginuse.receivers.BatteryStatusReceiver;
import com.example.loginuse.receivers.ConnectionChangeReceiver;
import com.example.loginuse.receivers.WifiReceiver;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private static final String TAG = "MyService";
	private IntentFilter filter;
		
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {		
		Log.d(TAG, "onCreate");
		this.filter = new IntentFilter();
		
		//--------------------------Bloototh-----------------------------------		
		this.filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED); //Esta ON o OFF BluetoothAdapter.STATE_OFF o BluetoothAdapter.STATE_ON
				
		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");		
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		registerReceiver (myReceiver, filter);
		registerReceivers();
	}
	
	private void registerReceivers(){
		BatteryStatusReceiver batteryStatusReceiver = new BatteryStatusReceiver();
		registerReceiver(batteryStatusReceiver, batteryStatusReceiver.getFilter());
		
		ConnectionChangeReceiver connectionChangeReceiver = new ConnectionChangeReceiver();
		registerReceiver(connectionChangeReceiver, connectionChangeReceiver.getFilter());
		
		WifiReceiver wifiReceiver = new WifiReceiver();
		registerReceiver(wifiReceiver, wifiReceiver.getFilter());
	}
	
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {   
        @Override
        public void onReceive(Context context, Intent intent) {          
           String action = intent.getAction();
           SaveLog.getInstance().saveData(new LsLog(action,"Default"));
           Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        }
    };
}