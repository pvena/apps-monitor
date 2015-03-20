package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogLine;

public class HeadsetPlugReceiver extends GeneralLoggingReceiver{
	/**
	 * Creator
	 */
	public HeadsetPlugReceiver(){
		super.logType = LogConstants.HEADSET_STATE_TAG;
		super.filter = new IntentFilter();
		this.filter.addAction(Intent.ACTION_HEADSET_PLUG);
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
	public void logEvent(Context context, Intent intent, LogLine l) {
		try
		{
			if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
	            int state = intent.getIntExtra("state", -1);
	            switch (state) {
	            case 0:
	            	l.addProperty(LogConstants.STATE, false);
	                break;
	            case 1:
	            	l.addProperty(LogConstants.STATE, true);
	                break;
	            default:
	            	l.addProperty(LogConstants.STATE, "-");
	            }
	        }
		}
		catch(Exception e){ Log.e("ERROR", "HEADSET-LOG"); }
	}
	

}
