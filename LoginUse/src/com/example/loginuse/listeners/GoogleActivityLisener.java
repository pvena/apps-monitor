package com.example.loginuse.listeners;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.example.loginuse.ActivityRecognitionIntentService;
import com.example.loginuse.log.LogConfiguration;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.ActivityRecognitionClient;

public class GoogleActivityLisener extends FragmentActivity implements
		ConnectionCallbacks, OnConnectionFailedListener {

	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	private boolean mInProgress;

	public enum REQUEST_TYPE {
		START, STOP
	}

	private PendingIntent mActivityRecognitionPendingIntent;
	// Store the current activity recognition client
	private ActivityRecognitionClient mActivityRecognitionClient;

	public static class ErrorDialogFragment extends DialogFragment {
		private Dialog mDialog;

		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}

		public void setDialog(Dialog dialog) {
			mDialog = dialog;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		Context mContext = getApplicationContext();

		mInProgress = false;
		
		mActivityRecognitionClient = new ActivityRecognitionClient(mContext, this, this);

		Intent intent = new Intent(mContext, ActivityRecognitionIntentService.class);

		mActivityRecognitionPendingIntent = PendingIntent.getService(mContext,
				0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		this.startUpdates();
		this.finish();
	}

	public void startUpdates() {
		if (servicesConnected() && !mInProgress) {
			mInProgress = true;
			mActivityRecognitionClient.connect();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case CONNECTION_FAILURE_RESOLUTION_REQUEST:
			switch (resultCode) {
			case Activity.RESULT_OK:
				break;
			}
		}
	}
	
	private void showErrorDialog(Dialog error){
		if (error != null) {
			ErrorDialogFragment errorFragment = new ErrorDialogFragment();
			errorFragment.setDialog(error);
			errorFragment.show(getSupportFragmentManager(),"Activity Recognition");
		}
	}

	private boolean servicesConnected() {
		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			// Google Play services was not available for some reason
			return true;			
		} else {
			// Get the error dialog from Google Play services
			this.showErrorDialog(GooglePlayServicesUtil.getErrorDialog(resultCode,this,CONNECTION_FAILURE_RESOLUTION_REQUEST));
			return false;
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		mInProgress = false;
		if (connectionResult.hasResolution()) {
			try {
				connectionResult.startResolutionForResult(this,
						CONNECTION_FAILURE_RESOLUTION_REQUEST);
			} catch (SendIntentException e) {
				e.printStackTrace();
			}
		} else {
			int errorCode = connectionResult.getErrorCode();
			this.showErrorDialog(GooglePlayServicesUtil.getErrorDialog(
					errorCode, this, CONNECTION_FAILURE_RESOLUTION_REQUEST));
		}
	}

	@Override
	public void onConnected(Bundle dataBundle) {
		int DETECTION_INTERVAL_MILLISECONDS = 	LogConfiguration.getInstance().getProperty(LogConfiguration.ACTIVITYMILISECONDSPERSSECOND, 1000) * 
												LogConfiguration.getInstance().getProperty(LogConfiguration.ACTIVITYDETACTIONINTERVALSECONDS, 60);
		
		mActivityRecognitionClient.requestActivityUpdates(
				DETECTION_INTERVAL_MILLISECONDS,
				mActivityRecognitionPendingIntent);
		mInProgress = false;
		mActivityRecognitionClient.disconnect();
	}

	@Override
	public void onDisconnected() {
		mInProgress = false;
		mActivityRecognitionClient = null;
	}
	
}