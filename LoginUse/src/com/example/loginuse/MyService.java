package com.example.loginuse;

import android.R.integer;
import android.app.Service;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.drm.DrmStore.Action;
import android.location.LocationManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private static final String TAG = "MyService";
	private IntentFilter filter;
	private SaveLog log;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {		
		this.log = new SaveLog(this);
		Log.d(TAG, "onCreate");
		this.filter = new IntentFilter();
		
		//-----------------------------WIFI----------------------------------
		this.filter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);		
		this.filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION); ///**********Importante*********
		
		//--------------------------Bloototh-----------------------------------		
		this.filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);// se conecto a algo!!!!
		this.filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED); //Esta ON o OFF BluetoothAdapter.STATE_OFF o BluetoothAdapter.STATE_ON
			
		//--------------------------Bateria-----------------------------
		this.filter.addAction(Intent.ACTION_POWER_CONNECTED);
		this.filter.addAction(Intent.ACTION_POWER_DISCONNECTED);		
		this.filter.addAction(Intent.ACTION_SHUTDOWN);
		this.filter.addAction(Intent.ACTION_REBOOT);
		this.filter.addAction(Intent.ACTION_SCREEN_ON);
		this.filter.addAction(Intent.ACTION_SCREEN_OFF);
		this.filter.addAction(Intent.ACTION_BOOT_COMPLETED);		
		this.filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		this.filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);				
		
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
		registerReceiver(myReceiver, filter);
	}
	
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {   
        @Override
        public void onReceive(Context context, Intent intent) {          
           String action = intent.getAction();
           log.saveData(new LsLog(action,"Default"));
           Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        }
    };
}