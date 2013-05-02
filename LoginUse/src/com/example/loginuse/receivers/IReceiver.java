package com.example.loginuse.receivers;

import android.content.IntentFilter;

public interface IReceiver {
	
	public IntentFilter getFilter();
	
	public void addAction(String action);

}
