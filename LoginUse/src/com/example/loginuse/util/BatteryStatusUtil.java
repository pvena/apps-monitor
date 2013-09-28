package com.example.loginuse.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Get info from current battery status
 *
 */
public class BatteryStatusUtil {
	
	private static final String PERCENTAGE = "PERCENTAGE";
	
	/**
	 * Return the current charge status
	 * @param context
	 * @return
	 */
	public static String getBatteryStatus(Context context){
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
			
			if(usbCharge){
				bStatus = "PLUGGED_USB|1";
			}else if(acCharge){
				bStatus = "PLUGGED_AC|1";
			}
		} else {
			bStatus = "DISCHARGING|1";
		}
		
		return bStatus;
	}
	
	/**
	 * Return the current battery level
	 * @param context
	 * @return
	 */
	public static String getBatteryPercentage(Context context){
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);
		
		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float)scale * 100;
		
		return PERCENTAGE + "|" + String.valueOf(batteryPct) +"%";
	}

	/**
	 * Return the String to log
	 * @param context
	 * @return
	 */
	public static String getLog(Context context)
	{
		String batteryStatus = BatteryStatusUtil.getBatteryPercentage(context)
				+ " " + BatteryStatusUtil.getBatteryStatus(context);
		return batteryStatus;
	}
}
