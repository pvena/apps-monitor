package com.example.loginuse.receivers;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
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
			LogLine l = new LogLine(LogConstants.RINGER_STATE_TAG);
			AudioManager am = (AudioManager)LogConfiguration.getInstance().getContext().getSystemService(Context.AUDIO_SERVICE);

			switch (am.getRingerMode()) {
				case AudioManager.RINGER_MODE_SILENT:
					l.addProperty(LogConstants.STATE, false);
					l.addProperty(LogConstants.MODE, "SILENT");
					break;
				case AudioManager.RINGER_MODE_VIBRATE:
					l.addProperty(LogConstants.STATE, true);
					l.addProperty(LogConstants.MODE, "VIBRATE");					
					break;
				case AudioManager.RINGER_MODE_NORMAL:
					l.addProperty(LogConstants.STATE, true);
					l.addProperty(LogConstants.MODE, "NORMAL");
					break;
			}			
			
			if(!l.getMessage().equals(lastLog)){							
				LogSave.getInstance().saveData(l);
				lastLog = l.getMessage();
			}
		}
		catch(Exception e){ Log.e("ERROR", "RINGER-LOG"); }
	}
}
