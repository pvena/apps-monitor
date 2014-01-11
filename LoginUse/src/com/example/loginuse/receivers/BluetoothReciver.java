package com.example.loginuse.receivers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
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
			LogLine l = new LogLine(LogConstants.BLUETOOTH_STATE_TAG);	
	        // When discovery finds a device
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		        // Get the BluetoothDevice object from the Intent
		        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        // If it is not paired, add it to the scanned list
		        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
		        	l.addProperty(LogConstants.NAME, device.getName());
		        	l.addProperty(LogConstants.ADDRESS,device.getAddress());
		        	l.addProperty(LogConstants.STATE, true);
		        	l.addProperty(LogConstants.TSTATE, false);		   
		        }
		    }
			else 
				if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
					int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
					
					l.addProperty(LogConstants.NAME,"-");
					l.addProperty(LogConstants.ADDRESS,"-");
					
					switch (state) {
					case BluetoothAdapter.STATE_OFF:
						l.addProperty(LogConstants.STATE, false);
						l.addProperty(LogConstants.TSTATE, false);
						break;
					case BluetoothAdapter.STATE_TURNING_OFF:
						l.addProperty(LogConstants.STATE, true);
						l.addProperty(LogConstants.TSTATE, true);
						break;
					case BluetoothAdapter.STATE_ON:
						l.addProperty(LogConstants.STATE, true);
						l.addProperty(LogConstants.TSTATE, false);
						break;
					case BluetoothAdapter.STATE_TURNING_ON:
						l.addProperty(LogConstants.STATE, false);
						l.addProperty(LogConstants.TSTATE, true);
						break;
					}		            
				}
			
			if(l.getMessage().equals("")){
				l.addProperty(LogConstants.NAME, "-");
				l.addProperty(LogConstants.ADDRESS, "-");
				l.addProperty(LogConstants.STATE, false);
				l.addProperty(LogConstants.TSTATE, false);
			}
			if(!l.getMessage().equals(lastLog))
			{
				LogSave.getInstance().saveData(l);
				lastLog = l.getMessage();
			}
		}
		catch(Exception e){ Log.e("ERROR", "Bluetooth-LOG"); }
	}
}
