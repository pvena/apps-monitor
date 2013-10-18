package com.example.loginuse.receivers;


import com.example.loginuse.log.LogConfiguration;

import android.content.Context;
import android.content.Intent;

public class ActivityReceiver extends GeneralLoggingReceiver {

	@Override
	public void logEvent(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		Intent googleActivity = new Intent("googleActivity.intent.action.Launch");
		googleActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		LogConfiguration.getInstance().getContext().startActivity(googleActivity);
	}

	@Override
	public void finalize() {
	}

}
