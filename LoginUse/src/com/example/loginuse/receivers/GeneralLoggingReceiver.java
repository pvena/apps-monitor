package com.example.loginuse.receivers;

import com.example.loginuse.log.LogConstants;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;
import com.example.loginuse.util.BatteryStatusUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * General class structure for logging receivers
 *
 */
public abstract class GeneralLoggingReceiver extends BroadcastReceiver {
	
	protected IntentFilter filter;
	private static String lastLogBattery;
	
	/**
	 * Creator
	 */
	public GeneralLoggingReceiver(){
		filter = new IntentFilter();
	}
	
	/**
	 * @return IntentFilter filter
	 */
	public IntentFilter getFilter(){
		return filter;
	}
	
	/**
	 * @param String action a new Intent action to match against.
	 */
	public void addAction(String action){
		filter.addAction(action);
	}

	/**
	 * Log the incoming events and call PassiveLocationChangedReceiver 
	 * trying to find and save the user location
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		String message = BatteryStatusUtil.getLog(context);		
		if (!message.equals(lastLogBattery))
		{
			LogLine  l = new LogLine(message,LogConstants.BATTERY_STATE_TAG);
			LogSave.getInstance().saveData(l);
			lastLogBattery = message;
		}
		logEvent(context, intent);
	}
	
	/**
	 * Save the incoming events in a log file
	 * @param context
	 * @param intent
	 */
	public abstract void logEvent(Context context, Intent intent);
	
	
}
