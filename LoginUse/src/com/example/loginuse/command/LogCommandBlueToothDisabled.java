package com.example.loginuse.command;

import android.bluetooth.BluetoothAdapter;

public class LogCommandBlueToothDisabled extends LogCommand {

	@Override
	public boolean internalCondition() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean internalExecute() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter.isDiscovering()) {
			mBluetoothAdapter.enable();
		}
		return false;
	}
}