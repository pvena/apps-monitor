package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.log.LogLine;

public class BatteryPowerReceiver extends GeneralLoggingReceiver {
	/**
	 * Creator
	 */
	
	public BatteryPowerReceiver(){
		
		filter = new IntentFilter();
		
		this.filter.addAction(Intent.ACTION_POWER_CONNECTED);
		this.filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
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
	public void logEvent(Context context, Intent intent,LogLine l) {}

}
