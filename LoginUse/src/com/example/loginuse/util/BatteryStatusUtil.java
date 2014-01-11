package com.example.loginuse.util;

import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogLine;

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
	public static LogLine getBatteryStatusLine(Context context){
		LogLine l = new LogLine(LogConstants.BATTERY_STATE_TAG);
		
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);
		
		// Are we charging / charged?
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
		                     status == BatteryManager.BATTERY_STATUS_FULL;
		
		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float)scale * 100;
		
		l.addProperty(LogConstants.PERCENTAGE,String.valueOf(batteryPct) +"%");		
		
		if(isCharging){
			// How are we charging?
			int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
			boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
			boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
			
			l.addProperty(LogConstants.PLUGGED_USB,usbCharge);
			l.addProperty(LogConstants.PLUGGED_AC,acCharge);
			l.addProperty(LogConstants.DISCHARGING,false);
			
		} else {
			l.addProperty(LogConstants.PLUGGED_USB,false);
			l.addProperty(LogConstants.PLUGGED_AC,false);
			l.addProperty(LogConstants.DISCHARGING,true);
		}		
		return l;
	}
}
