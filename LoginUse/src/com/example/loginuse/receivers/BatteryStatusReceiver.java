package com.example.loginuse.receivers;

import com.example.loginuse.Log.*;
import com.example.loginuse.util.BatteryStatusUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BatteryStatusReceiver extends BroadcastReceiver implements IReceiver{
	
	private IntentFilter filter;
	
	public BatteryStatusReceiver(){
		
		filter = new IntentFilter();
		
		filter.addAction(Intent.ACTION_POWER_CONNECTED);
		filter.addAction(Intent.ACTION_POWER_DISCONNECTED);	
		
		filter.addAction(Intent.ACTION_SHUTDOWN);
		
		filter.addAction(Intent.ACTION_BOOT_COMPLETED);
		filter.addAction(Intent.ACTION_REBOOT);
		
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
				
		filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
	}
	
	@Override
	public IntentFilter getFilter(){
		return filter;
	}
	
	@Override
	public void addAction(String action){
		filter.addAction(action);
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		try
		{
			String batteryStatus = BatteryStatusUtil.getLog(context);			
			SaveLog.getInstance().saveData(new LsLog(batteryStatus, LogTags.Battery_Tag));
		}
		catch(Exception e)
		{
			Log.e("ERROR", "BATTERY-LOG" + e.getMessage());
		}
	}
}
