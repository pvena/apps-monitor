package com.example.loginuse.receivers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.loginuse.log.LogConfiguration;
import com.example.loginuse.log.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;

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
	
	@Override
	public void initialize() {
		LogConfiguration.getInstance().getContext().registerReceiver(this, this.filter);
	}

	@Override
	public void finalize() {
		LogConfiguration.getInstance().getContext().unregisterReceiver(this);
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
		        	logText += LogFormat.getLog("NAME",device.getName());
		            logText += LogFormat.getLog("ADDRESS",device.getAddress());
		            logText += LogFormat.getLog("STATE", true);
		            logText += LogFormat.getLog("TSTATE", false);
		        }
		    }
			else 
				if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
					int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
					switch (state) {
					case BluetoothAdapter.STATE_OFF:
						logText += LogFormat.getLog("STATE", false);
						break;
					case BluetoothAdapter.STATE_TURNING_OFF:
						logText += LogFormat.getLog("TSTATE", false);
						break;
					case BluetoothAdapter.STATE_ON:
						logText += LogFormat.getLog("STATE", true);
						break;
					case BluetoothAdapter.STATE_TURNING_ON:
						logText += LogFormat.getLog("TSTATE", true);
						break;
					}
					logText += LogFormat.getLog("NAME","-");
		            logText += LogFormat.getLog("ADDRESS","-");
		            
				}
			
			if(logText.equals("")){
				logText = LogFormat.getLog("INFO","ND");
			}
			if(!logText.equals(lastLog))
			{
				LogLine l = new LogLine(logText, LogConstants.BLUETOOTH_STATE_TAG);			
				LogSave.getInstance().saveData(l);
				lastLog = logText;
			}
		}
		catch(Exception e){ Log.e("ERROR", "Bluetooth-LOG"); }
	}
}
