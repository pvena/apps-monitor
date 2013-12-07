package com.example.loginuse.util;


import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.log.LogFormat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Get info from current battery status
 *
 */
public class BatteryStatusUtil {	
	/**
	 * Return the current charge status
	 * @param context
	 * @return
	 */
	private static String getBatteryStatus(Context context){
		String bStatus = "";
		
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);
		
		// Are we charging / charged?
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
		                     status == BatteryManager.BATTERY_STATUS_FULL;
		if(isCharging){
			// How are we charging?
			int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
			boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
			boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
			
			bStatus = LogFormat.getLog(LogConstants.PLUGGED_USB,usbCharge);
			bStatus += LogFormat.getLog(LogConstants.PLUGGED_AC,acCharge);
			bStatus += LogFormat.getLog(LogConstants.DISCHARGING,false);
			
			LogCommandManager.getInstance().newState(LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.PLUGGED_USB, LogFormat.getValue(usbCharge));
			LogCommandManager.getInstance().newState(LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.PLUGGED_AC, LogFormat.getValue(usbCharge));
			LogCommandManager.getInstance().newState(LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.DISCHARGING, LogFormat.getValue(false));
			
		} else {
			bStatus = LogFormat.getLog(LogConstants.PLUGGED_USB,false);
			bStatus += LogFormat.getLog(LogConstants.PLUGGED_AC,false);
			bStatus = LogFormat.getLog(LogConstants.DISCHARGING,true);
			
			LogCommandManager.getInstance().newState(LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.PLUGGED_USB, LogFormat.getValue(false));
			LogCommandManager.getInstance().newState(LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.PLUGGED_AC, LogFormat.getValue(false));
			LogCommandManager.getInstance().newState(LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.DISCHARGING, LogFormat.getValue(true));
		}
		
		return bStatus;
	}
	
	/**
	 * Return the current battery level
	 * @param context
	 * @return
	 */
	private static String getBatteryPercentage(Context context){
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);
		
		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float)scale * 100;
		
		return LogFormat.getLog(LogConstants.PERCENTAGE,String.valueOf(batteryPct) +"%");
	}

	/**
	 * Return the String to log
	 * @param context
	 * @return
	 */
	public static String getLog(Context context)
	{
		return BatteryStatusUtil.getBatteryPercentage(context) + BatteryStatusUtil.getBatteryStatus(context);		
	}
}
