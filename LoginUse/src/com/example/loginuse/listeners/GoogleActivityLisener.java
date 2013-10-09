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
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.loginuse.ActivityRecognitionIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.ActivityRecognitionClient;

public class GoogleActivityLisener extends FragmentActivity implements
		ConnectionCallbacks, OnConnectionFailedListener {

	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	// Constants that define the activity detection interval
	public static final int MILLISECONDS_PER_SECOND = 1000;
	public static final int DETECTION_INTERVAL_SECONDS = 20;
	public static final int DETECTION_INTERVAL_MILLISECONDS = MILLISECONDS_PER_SECOND
			* DETECTION_INTERVAL_SECONDS;
	private boolean mInProgress;

	public enum REQUEST_TYPE {
		START, STOP
	}

	private REQUEST_TYPE mRequestType;

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

	}

	public void startUpdates() {
		if (!servicesConnected()) {
			return;
		}
		if (!mInProgress) {
			mInProgress = true;
			mActivityRecognitionClient.connect();
		} else {

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

	private boolean servicesConnected() {
		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			Toast toast = Toast.makeText(this, "GooglePlayAvailable", 2000);
			toast.setGravity(Gravity.TOP, -30, 50);
			toast.show();
			return true;
			// Google Play services was not available for some reason
		} else {
			// Get the error dialog from Google Play services
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
					resultCode, this, CONNECTION_FAILURE_RESOLUTION_REQUEST);

			if (errorDialog != null) {
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
				errorFragment.setDialog(errorDialog);
				errorFragment.show(getSupportFragmentManager(),
						"Activity Recognition");
			}
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
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
					errorCode, this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
			if (errorDialog != null) {
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
				errorFragment.setDialog(errorDialog);
				errorFragment.show(getSupportFragmentManager(),
						"Activity Recognition");
			}
		}
	}

	@Override
	public void onConnected(Bundle dataBundle) {
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