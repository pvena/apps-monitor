package com.example.loginuse.command;

import com.example.loginuse.configuration.LogConfiguration;

public class LogCommandBrightnessDisable extends LogCommand{
	@Override
	public boolean internalCondition() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean internalExecute() {
		android.provider.Settings.System.putInt(LogConfiguration.getInstance().getContext().getContentResolver(),
		android.provider.Settings.System.SCREEN_BRIGHTNESS, 0);
		return true;
	}	
}
