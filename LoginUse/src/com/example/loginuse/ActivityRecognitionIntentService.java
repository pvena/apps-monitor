package com.example.loginuse;

import android.app.IntentService;
import android.content.Intent;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

public class ActivityRecognitionIntentService extends IntentService {
	
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
			
			if(confidence > LogConfiguration.getInstance().getProperty(LogConfiguration.ACTIVITYMINCCONFIDENCE, 80))
			{
				Intent i = new Intent();
				i.putExtra(LogConstants.ACTIVITY,activityName);
				LogConfiguration.getInstance().getContext().startActivity(i);
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
