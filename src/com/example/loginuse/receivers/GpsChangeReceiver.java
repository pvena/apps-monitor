package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.util.Log;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogLine;

public class GpsChangeReceiver extends GeneralLoggingReceiver{	
	/**
	 * Creator
	 */
	public GpsChangeReceiver(){
		super.logType = LogConstants.GPS_STATE_TAG;
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
	public void logEvent(Context context, Intent intent,LogLine l) {
		try
		{
			final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				l.addProperty(LogConstants.STATE, true);
			} else {
				l.addProperty(LogConstants.STATE, false);
			}
		}
		catch(Exception e){ Log.e("ERROR", "GPS-LOG"); }
	}
	

}
