package com.example.loginuse;

import java.io.File;
import java.io.FilenameFilter;
import com.example.loginuse.R;
import com.example.loginuse.log.LogConfiguration;
import com.example.loginuse.log.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.util.Compress;
import com.example.loginuse.util.SoapFileTask;
import com.example.loginuse.util.SoapRegisterTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ConfigActivity  extends Activity{
	
	private Button sendLog;
	private Button saveConfig;
	private Button registerUser;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);
        sendLog = (Button)findViewById(R.id.sendLog);
        sendLog.setOnClickListener(sendLogOnClickListener);
        saveConfig = (Button)findViewById(R.id.saveConfiguration);
        saveConfig.setOnClickListener(saveConfigOnClickListener);		
        registerUser = (Button)findViewById(R.id.btnRegister);
        registerUser.setOnClickListener(userNameRegisterClickLisener);
        this.inicConfig();
    }
	
	private void inicConfig(){
		boolean gpsEnabled = LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGPSENABLED,false);				
		String locInterval = String.valueOf(LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONINTERVAL,9000));
		String locMinDist = String.valueOf(LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONMINDISTANCE,200));
		String actConfiddence = String.valueOf(LogConfiguration.getInstance().getProperty(LogConfiguration.ACTIVITYMINCCONFIDENCE,80));
		String webServiceURL = LogConfiguration.getInstance().getProperty(LogConfiguration.WebServiceURL, "http://.../loginuse.asmx");
		String phoneId = LogConfiguration.getInstance().getPhoneId();
		String userName = LogConfiguration.getInstance().getProperty(LogConfiguration.UserName, "");
		
		((CheckBox)findViewById(R.id.chbEnableGpsLocation)).setChecked(gpsEnabled);
		((TextView)findViewById(R.id.txtIntervalLocation)).setText(locInterval);
		((TextView)findViewById(R.id.txtMinDistanceLocation)).setText(locMinDist);
		((TextView)findViewById(R.id.txtActivityConfidence)).setText(actConfiddence);
		((TextView)findViewById(R.id.txtWebServiceURL)).setText(webServiceURL);
		((TextView)findViewById(R.id.txvPhoneIdValue)).setText(phoneId);
		((TextView)findViewById(R.id.txtUserName)).setText(userName);
		
	}
	
	Button.OnClickListener sendLogOnClickListener = new Button.OnClickListener() {
		
		private String[] listFiles(String dir) {
			File root = Environment.getExternalStorageDirectory();
			
			File directory = new File(root, dir);

			if (!directory.isDirectory()) {
				System.out.println("No directory provided");
				return null;
			}

			// create a FilenameFilter and override its accept-method
			FilenameFilter filefilter = new FilenameFilter() {

				public boolean accept(File dir, String name) {
					// if the file extension is .txt return true, else false
					return name.endsWith(".txt");
				}
			};

			return directory.list(filefilter);
		}

		@Override
		public void onClick(View arg0) {
			try {
				Compress compress = new Compress(listFiles(LogConstants.LOG_FOLDER_NAME), LogConstants.ZIP_LOG_FILE_NAME);
				compress.zip();
				
				File root = Environment.getExternalStorageDirectory();
				File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
				File file = new File(directory,LogConstants.ZIP_LOG_FILE_NAME);
				
				new SoapFileTask(file,"Prueba.Zip").execute();			
				
				//TODO descomentar cuando tengamos el server corriendo
				//new UploadFileTask(ServiceActivity.this).execute(LogConstants.ZIP_LOG_FILE_NAME);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	};
	
	Button.OnClickListener saveConfigOnClickListener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				
				boolean gpsEnabled = LogFormat.getCheckBoxBoolean((CheckBox)findViewById(R.id.chbEnableGpsLocation));				
				int locInterval = LogFormat.getTextViewInt((TextView)findViewById(R.id.txtIntervalLocation));
				int locMinDist = LogFormat.getTextViewInt((TextView)findViewById(R.id.txtMinDistanceLocation));
				int actConfiddence = LogFormat.getTextViewInt((TextView)findViewById(R.id.txtActivityConfidence));
				String webServiceURL = ((TextView)findViewById(R.id.txtWebServiceURL)).getText().toString();
				
				LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGPSENABLED,gpsEnabled);
				LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONINTERVAL,locInterval);
				LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONMINDISTANCE,locMinDist);
				LogConfiguration.getInstance().setProperty(LogConfiguration.ACTIVITYMINCCONFIDENCE,actConfiddence);
				LogConfiguration.getInstance().setProperty(LogConfiguration.WebServiceURL,webServiceURL);
				
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
}


