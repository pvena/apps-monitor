package com.example.loginuse.receivers;


import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogLine;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ActivityReceiver extends GeneralLoggingReceiver {

	public ActivityReceiver(){
		super.logType = LogConstants.CURRENTACTIVITY_TAG;
		this.filter = new IntentFilter();
		this.filter.addAction("RecognitionActivity");
	}
	
	@Override
	public void logEvent(Context context, Intent intent,LogLine l) {
		String key = LogConstants.ACTIVITY;
		String value = intent.getExtras().get(key).toString();
		
		l.addProperty(key, value);
	}

	@Override
	public void initialize() {
		Intent googleActivity = new Intent("googleActivity.intent.action.Launch");
		googleActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		LogConfiguration.getInstance().getContext().startActivity(googleActivity);		
		LogConfiguration.getInstance().getContext().registerReceiver(this, this.filter);
	}

	@Override
	public void finalize() {
	}

}
