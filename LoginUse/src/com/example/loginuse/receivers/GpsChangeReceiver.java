package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.util.Log;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;

public class GpsChangeReceiver extends GeneralLoggingReceiver{
	
private static String lastLog;
	
	/**
	 * Creator
	 */
	public GpsChangeReceiver(){
		
		filter = new IntentFilter();
		this.filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);
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
			LogLine l = new LogLine(LogConstants.GPS_STATE_TAG);	
			final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				l.addProperty(LogConstants.STATE, true);
			} else {
				l.addProperty(LogConstants.STATE, false);
			}
			if(!l.getMessage().equals(lastLog)){
				LogSave.getInstance().saveData(l);
				lastLog = l.getMessage();
			}
		}
		catch(Exception e){ Log.e("ERROR", "GPS-LOG"); }
	}
	

}
