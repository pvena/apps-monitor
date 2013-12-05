package com.example.loginuse.receivers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.command.LogCommandManager;
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
		        	logText += LogFormat.getLog(LogConstants.NAME,device.getName());
		            logText += LogFormat.getLog(LogConstants.ADDRESS,device.getAddress());
		            logText += LogFormat.getLog(LogConstants.STATE, true);
		            logText += LogFormat.getLog(LogConstants.TSTATE, false);
		            
		            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.NAME, LogFormat.getValue(device.getName()));
		            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.ADDRESS, LogFormat.getValue(device.getAddress()));
		            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(true));
		            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.TSTATE, LogFormat.getValue(false));
		        }
		    }
			else 
				if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
					int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
					
					logText += LogFormat.getLog(LogConstants.NAME,"-");
		            logText += LogFormat.getLog(LogConstants.ADDRESS,"-");
		            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.NAME, LogFormat.getValue("-"));
		            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.ADDRESS, LogFormat.getValue("-"));
					
					switch (state) {
					case BluetoothAdapter.STATE_OFF:
						logText += LogFormat.getLog(LogConstants.STATE, false);
						logText += LogFormat.getLog(LogConstants.TSTATE, false);
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(false));
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.TSTATE, LogFormat.getValue(false));
						break;
					case BluetoothAdapter.STATE_TURNING_OFF:
						logText += LogFormat.getLog(LogConstants.STATE, true);
						logText += LogFormat.getLog(LogConstants.TSTATE, true);
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(true));
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.TSTATE, LogFormat.getValue(false));
						break;
					case BluetoothAdapter.STATE_ON:
						logText += LogFormat.getLog(LogConstants.STATE, true);
						logText += LogFormat.getLog(LogConstants.TSTATE, false);
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(true));
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.TSTATE, LogFormat.getValue(false));
						break;
					case BluetoothAdapter.STATE_TURNING_ON:
						logText += LogFormat.getLog(LogConstants.STATE, false);
						logText += LogFormat.getLog(LogConstants.TSTATE, true);
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(false));
						LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.TSTATE, LogFormat.getValue(true));
						break;
					}		            
				}
			
			if(logText.equals("")){
				logText += LogFormat.getLog(LogConstants.NAME,"-");
	            logText += LogFormat.getLog(LogConstants.ADDRESS,"-");
	            logText += LogFormat.getLog(LogConstants.STATE, false);
	            logText += LogFormat.getLog(LogConstants.TSTATE, false);

	            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.NAME, LogFormat.getValue("-"));
	            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.ADDRESS, LogFormat.getValue("-"));
	            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(false));
	            LogCommandManager.getInstance().newState(LogConstants.BLUETOOTH_STATE_TAG + "-" + LogConstants.TSTATE, LogFormat.getValue(false));
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
