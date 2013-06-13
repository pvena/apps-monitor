package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.BatteryStatusUtil;
import com.example.loginuse.util.Constants;

/**
 * 
 * Broadcast receiver used to hear and log battery status changes
 *
 */
public class BatteryStatusReceiver extends GeneralLoggingReceiver{
	
	/**
	 * Creator
	 */
	public BatteryStatusReceiver(){
		
		super();
		
		super.addAction(Intent.ACTION_POWER_CONNECTED);
		super.addAction(Intent.ACTION_POWER_DISCONNECTED);	
		
		super.addAction(Intent.ACTION_SHUTDOWN);
		
		super.addAction(Intent.ACTION_BOOT_COMPLETED);
		super.addAction(Intent.ACTION_REBOOT);
		
		super.addAction(Intent.ACTION_SCREEN_ON);
		super.addAction(Intent.ACTION_SCREEN_OFF);
				
		super.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		super.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.example.loginuse.receivers.GeneralLoggingReceiver#logEvent(android.content.Context, android.content.Intent)
	 */
	@Override
	public void logEvent(Context context, Intent intent) {
		try
		{
			String batteryStatus = BatteryStatusUtil.getLog(context);			
			SaveLog.getInstance().saveData(new LsLog(batteryStatus, Constants.BATTERY_STATE_TAG));
		}
		catch(Exception e)
		{
			Log.e("ERROR", "BATTERY-LOG" + e.getMessage());
		}
	}
}
