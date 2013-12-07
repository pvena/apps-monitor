package com.example.loginuse.receivers;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;

public class RingerReceiver extends GeneralLoggingReceiver{

	private static String lastLog;
	
	public RingerReceiver (){
		filter = new IntentFilter();
		this.filter.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
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
			AudioManager am = (AudioManager)LogConfiguration.getInstance().getContext().getSystemService(Context.AUDIO_SERVICE);

			switch (am.getRingerMode()) {
				case AudioManager.RINGER_MODE_SILENT:
					logText += LogFormat.getLog(LogConstants.STATE, false);
					logText += LogFormat.getLog(LogConstants.MODE, "SILENT");
					
					LogCommandManager.getInstance().newState(LogConstants.RINGER_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(false));
					LogCommandManager.getInstance().newState(LogConstants.RINGER_STATE_TAG + "-" + LogConstants.MODE, LogFormat.getValue("SILENT"));
					break;
				case AudioManager.RINGER_MODE_VIBRATE:
					logText += LogFormat.getLog(LogConstants.STATE, true);
					logText += LogFormat.getLog(LogConstants.MODE, "VIBRATE");
					
					LogCommandManager.getInstance().newState(LogConstants.RINGER_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(true));
					LogCommandManager.getInstance().newState(LogConstants.RINGER_STATE_TAG + "-" + LogConstants.MODE, LogFormat.getValue("VIBRATE"));
					break;
				case AudioManager.RINGER_MODE_NORMAL:
					logText += LogFormat.getLog(LogConstants.STATE, true);
					logText += LogFormat.getLog(LogConstants.MODE, "NORMAL");
					
					LogCommandManager.getInstance().newState(LogConstants.RINGER_STATE_TAG + "-" + LogConstants.STATE, LogFormat.getValue(true));					
					LogCommandManager.getInstance().newState(LogConstants.RINGER_STATE_TAG + "-" + LogConstants.MODE, LogFormat.getValue("NORMAL"));
					break;
			}
			
			
			if(!logText.equals(lastLog)){
				LogLine l = new LogLine(logText, LogConstants.RINGER_STATE_TAG);			
				LogSave.getInstance().saveData(l);
				lastLog = logText;
			}
		}
		catch(Exception e){ Log.e("ERROR", "RINGER-LOG"); }
	}
}
