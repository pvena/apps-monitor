package com.example.loginuse.receivers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.loginuse.log.LsLog;
import com.example.loginuse.log.SaveLog;
import com.example.loginuse.util.Constants;

/**
 * Broadcast receiver used to hear and log bluetooth status changes
 *
 */
public class BluetoothReciver extends GeneralLoggingReceiver {
	
	private static String lastLog;
	
	/**
	 * Creator
	 */
	public BluetoothReciver(){
		
		filter = new IntentFilter();
		this.filter.addAction(BluetoothDevice.ACTION_FOUND);
		this.filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
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
			String logText = "";
	        // When discovery finds a device
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		        // Get the BluetoothDevice object from the Intent
		        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        // If it is not paired, add it to the scanned list
		        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
		        	logText += "Name: " + device.getName();
		            logText += " Address: " + device.getAddress();
		        }
		    }else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
	            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
	                                                 BluetoothAdapter.ERROR);
	            switch (state) {
	            case BluetoothAdapter.STATE_OFF:
	            	logText += "Bluetooth off";
	                break;
	            case BluetoothAdapter.STATE_TURNING_OFF:
	            	logText += "Turning Bluetooth off";
	                break;
	            case BluetoothAdapter.STATE_ON:
	            	logText += "Bluetooth on";
	                break;
	            case BluetoothAdapter.STATE_TURNING_ON:
	            	logText += "Turning Bluetooth on";
	                break;
	            }
	        }
			
			if(logText.equals("")){
				logText = "No data";
			}
			if(!logText.equals(lastLog))
			{
				LsLog l = new LsLog(logText, Constants.BLUETOOTH_STATE_TAG);			
				SaveLog.getInstance().saveData(l);
				lastLog = logText;
			}
		}
		catch(Exception e){ Log.e("ERROR", "WIFI-LOG"); }
	}
}
