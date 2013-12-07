package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;

public class HeadsetPlugReceiver extends GeneralLoggingReceiver{
	
private static String lastLog;
	
	/**
	 * Creator
	 */
	public HeadsetPlugReceiver(){
		
		filter = new IntentFilter();
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
	public void logEvent(Context context, Intent intent) {
		try
		{
			String logText = "";
			if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
	            int state = intent.getIntExtra("state", -1);
	            switch (state) {
	            case 0:
	            	logText += LogFormat.getLog(LogConstants.STATE, false);
					LogCommandManager.getInstance().newState(LogConstants.HEADSET_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(false));
	                break;
	            case 1:
	            	logText += LogFormat.getLog(LogConstants.STATE, true);
					LogCommandManager.getInstance().newState(LogConstants.HEADSET_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(true));
	                break;
	            default:
	                Log.d(LogConstants.HEADSET_STATE_TAG, "I have no idea what the headset state is");
	            }
	        }
			if(!logText.equals(lastLog)){
				LogLine l = new LogLine(logText, LogConstants.HEADSET_STATE_TAG);			
				LogSave.getInstance().saveData(l);
				lastLog = logText;
			}
		}
		catch(Exception e){ Log.e("ERROR", "HEADSET-LOG"); }
	}
	

}
