package com.example.loginuse;

import android.app.IntentService;
import android.content.Intent;

import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

public class ActivityRecognitionIntentService extends IntentService {
	
	private static String lastLog;
	
	public ActivityRecognitionIntentService() {
		super("NoName");
		// TODO Auto-generated constructor stub
	}
	
	public ActivityRecognitionIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// If the incoming intent contains an update
		if (ActivityRecognitionResult.hasResult(intent)) {
			ActivityRecognitionResult result = ActivityRecognitionResult
					.extractResult(intent);
			// Get the most probable activity
			DetectedActivity mostProbableActivity = result
					.getMostProbableActivity();

			int confidence = mostProbableActivity.getConfidence();

			int activityType = mostProbableActivity.getType();

			String activityName = this.getCurrentActionInfo(activityType);
			
			String newLog = LogFormat.getLog(LogConstants.ACTIVITY,activityName);
			
			LogCommandManager.getInstance().newState(LogConstants.CURRENTACTIVITY_TAG + "-" + LogConstants.ACTIVITY , LogFormat.getValue(activityName));
				
			if(!newLog.equals(lastLog) && confidence > LogConfiguration.getInstance().getProperty(LogConfiguration.ACTIVITYMINCCONFIDENCE, 80))
			{
				LogLine l = new LogLine(newLog, LogConstants.CURRENTACTIVITY_TAG);
				LogSave.getInstance().saveData(l);
				lastLog = newLog;
			}
		} else {

		}
	}

	private String getCurrentActionInfo(int activityType) {
		switch (activityType) {
		case DetectedActivity.IN_VEHICLE:
			return "IN_VEHICLE";
		case DetectedActivity.ON_BICYCLE:
			return "ON_BICYCLE";
		case DetectedActivity.ON_FOOT:
			return "ON_FOOT";
		case DetectedActivity.STILL:
			return "STILL";
		case DetectedActivity.UNKNOWN:
			return "STILL";//"UNKNOWN";
		case DetectedActivity.TILTING:
			return "STILL";//"TILTING";
		}
		return "UNKNOWN";
	}
}
