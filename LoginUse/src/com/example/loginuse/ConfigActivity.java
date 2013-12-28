package com.example.loginuse;

import com.example.loginuse.R;
import com.example.loginuse.command.LogCommand;
import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.soap.SoapLocationGroup;
import com.example.loginuse.soap.SoapRegisterTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity  extends Activity{
	
	private Button sendLog;
	private Button saveConfig;
	private Button registerUser;
	private Button refreshLocationGroups;
	private Button restarService;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);
        restarService = (Button)findViewById(R.id.btnRestarSerice);
        restarService.setOnClickListener(restartServiceClickLisener);
        sendLog = (Button)findViewById(R.id.sendLog);
        sendLog.setOnClickListener(sendLogOnClickListener);
        saveConfig = (Button)findViewById(R.id.saveConfiguration);
        saveConfig.setOnClickListener(saveConfigOnClickListener);		
        registerUser = (Button)findViewById(R.id.btnRegister);
        registerUser.setOnClickListener(userNameRegisterClickLisener);
        refreshLocationGroups = (Button)findViewById(R.id.refreshLocationGroups);
        refreshLocationGroups.setOnClickListener(refreshLocationGroupClickLisener);
        this.inicConfig();
    }
	
	private void inicConfig(){
		String webServiceURL = LogConfiguration.getInstance().getProperty(LogConfiguration.WebServiceURL, "http://201.235.94.231:8080");
		String phoneId = LogConfiguration.getInstance().getPhoneId();
		String userName = LogConfiguration.getInstance().getProperty(LogConfiguration.UserName, "");
		
		((TextView)findViewById(R.id.txtWebServiceURL)).setText(webServiceURL);
		((TextView)findViewById(R.id.txvPhoneIdValue)).setText(phoneId);
		((TextView)findViewById(R.id.txtUserName)).setText(userName);
		
	}
	
	Button.OnClickListener saveConfigOnClickListener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {			
			try {	
				
				String webServiceURL = ((TextView)findViewById(R.id.txtWebServiceURL)).getText().toString();
				String userName = ((TextView)findViewById(R.id.txtUserName)).getText().toString();				
				
				LogConfiguration.getInstance().setProperty(LogConfiguration.WebServiceURL,webServiceURL);
				LogConfiguration.getInstance().setProperty(LogConfiguration.UserName, userName);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	};

	Button.OnClickListener userNameRegisterClickLisener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {			
				String userName = ((TextView)findViewById(R.id.txtUserName)).getText().toString();				
				LogConfiguration.getInstance().setProperty(LogConfiguration.UserName, userName);
				
				new SoapRegisterTask(userName).execute();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	};

	
	Button.OnClickListener sendLogOnClickListener = new Button.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			try {
				
				LogCommand synch = LogCommandManager.getInstance().getCommand("SynchLogFile");
				if(synch != null)
					if(!synch.execute())
						Toast.makeText(LogConfiguration.getInstance().getContext(), "No Files for send", Toast.LENGTH_SHORT).show();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	};
	
	
	Button.OnClickListener restartServiceClickLisener = new Button.OnClickListener() {
		@Override
	    public void onClick(View v) {
			restartServiceOnClick();
			inicConfig();
	    }
	};	
	
	Button.OnClickListener refreshLocationGroupClickLisener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {				
				new SoapLocationGroup().execute();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}	
	
	public void restartServiceOnClick(){
		startService(new Intent(this,  MyService.class));	
	}
}


