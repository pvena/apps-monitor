package com.example.loginuse.receivers;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.Constants;

/**
 * Broadcast receiver used to hear and log bluetooth status changes
 *
 */
public class BluetoothReciver extends GeneralLoggingReceiver {
	
	/**
	 * Creator
	 */
	public BluetoothReciver(){
		
		filter = new IntentFilter();
		this.filter.addAction(BluetoothDevice.ACTION_FOUND);
		this.filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
		this.filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
		this.filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
		this.filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.example.loginuse.receivers.GeneralLoggingReceiver#logEvent(android.content.Context, android.content.Intent)
	 */
	@Override
	public void logEvent(Context context, Intent intent) {
		try
		{
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
			LsLog l = new LsLog(logText, Constants.BLUETOOTH_STATE_TAG);
			SaveLog.getInstance().saveData(l);
		}
		catch(Exception e){ Log.e("ERROR", "WIFI-LOG"); }
	}
}
