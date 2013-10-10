package com.example.loginuse;

import android.app.IntentService;
import android.content.Intent;

import com.example.loginuse.log.LsLog;
import com.example.loginuse.log.SaveLog;
import com.example.loginuse.util.Constants;
import com.example.loginuse.util.LogFormat;
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

			String activityName = getNameFromType(activityType);
			
			String newLog = LogFormat.getLog(Constants.ACTIVITY,activityName);
				
			if(!newLog.equals(lastLog) && confidence > LogConfiguration.getInstance().getActivityMinConfidence())
			{
				LsLog l = new LsLog(newLog, Constants.CURRENTACTIVITY);
				SaveLog.getInstance().saveData(l);
				lastLog = newLog;
			}
		} else {

		}
	}

	private String getNameFromType(int activityType) {
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
			return "UNKNOWN";
		case DetectedActivity.TILTING:
			return "TILTING";
		}
		return "UNKNOWN";
	}
}
