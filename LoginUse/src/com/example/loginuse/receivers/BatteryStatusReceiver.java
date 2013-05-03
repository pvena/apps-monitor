package com.example.loginuse.receivers;

import com.example.loginuse.Log.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BatteryStatusReceiver extends BroadcastReceiver implements IReceiver{
	
	private IntentFilter filter;
	
	public BatteryStatusReceiver(){
		
		this.filter.addAction(Intent.ACTION_POWER_CONNECTED);
		this.filter.addAction(Intent.ACTION_POWER_DISCONNECTED);	
		
		this.filter.addAction(Intent.ACTION_SHUTDOWN);
		
		this.filter.addAction(Intent.ACTION_BOOT_COMPLETED);
		this.filter.addAction(Intent.ACTION_REBOOT);
		
		this.filter.addAction(Intent.ACTION_SCREEN_ON);
		this.filter.addAction(Intent.ACTION_SCREEN_OFF);
				
		this.filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		this.filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
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
		 SaveLog.getInstance().saveData(new LsLog(intent.getAction(),"Default"));
	}

}
