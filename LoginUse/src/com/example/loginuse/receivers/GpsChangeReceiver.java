package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.util.Log;

import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
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
			String logText = "";
			final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				logText += LogFormat.getLog(LogConstants.STATE, true);
				LogCommandManager.getInstance().newState(LogConstants.GPS_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(true));
			} else {
				logText += LogFormat.getLog(LogConstants.STATE, false);
				LogCommandManager.getInstance().newState(LogConstants.GPS_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(false));
			}
			if(!logText.equals(lastLog)){
				LogLine l = new LogLine(logText, LogConstants.GPS_STATE_TAG);			
				LogSave.getInstance().saveData(l);
				lastLog = logText;
			}
		}
		catch(Exception e){ Log.e("ERROR", "GPS-LOG"); }
	}
	

}
