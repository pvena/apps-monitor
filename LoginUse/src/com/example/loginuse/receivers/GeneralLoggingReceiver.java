package com.example.loginuse.receivers;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.BatteryStatusUtil;
import com.example.loginuse.util.Constants;

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
		LsLog  l = new LsLog(BatteryStatusUtil.getLog(context),Constants.BATTERY_STATE_TAG);
		if (!l.getLog().equals(lastLogBattery))
		{			
			SaveLog.getInstance().saveData(l);
			lastLogBattery = l.getLog();
		}
		logEvent(context, intent);
		Intent i = new Intent(PassiveLocationChangedReceiver.CHECK_LOCATION);
        context.sendBroadcast(i);
		
	}
	
	/**
	 * Save the incoming events in a log file
	 * @param context
	 * @param intent
	 */
	public abstract void logEvent(Context context, Intent intent);
	
	
}
