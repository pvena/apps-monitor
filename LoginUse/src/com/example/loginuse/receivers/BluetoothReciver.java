package com.example.loginuse.receivers;

import com.example.loginuse.Log.LogTags;
import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.BatteryStatusUtil;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;

public class BluetoothReciver extends BroadcastReceiver implements IReceiver {
	private IntentFilter filter;
	
	public BluetoothReciver(){
		
		filter = new IntentFilter();
		
		this.filter.addAction(BluetoothDevice.ACTION_FOUND);	
	}
	
	@Override
	public IntentFilter getFilter(){
		return filter;
	}
	
	@Override
	public void addAction(String action){
		filter.addAction(action);
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		try
		{
			LsLog  l = new LsLog(BatteryStatusUtil.getLog(context),LogTags.Battery_Tag);
			SaveLog.getInstance().saveData(l);		
			
			String action = intent.getAction();
			String logText = "No Data";
	        // When discovery finds a device
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		        // Get the BluetoothDevice object from the Intent
		        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        // If it is not paired, add it to the scanned list
		        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
		        	logText += "Name: " + device.getName();
		            logText += "Address: " + device.getAddress();
		        }
		    }
			l = new LsLog(logText, LogTags.Bluetooth_Tag);
			SaveLog.getInstance().saveData(l);
		}
		catch(Exception e){ Log.e("ERROR", "WIFI-LOG"); }
	}
}
