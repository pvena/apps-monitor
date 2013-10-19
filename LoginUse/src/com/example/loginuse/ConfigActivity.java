package com.example.loginuse;

import java.io.File;
import java.io.FilenameFilter;

import com.example.loginuse.R;
import com.example.loginuse.log.LogConfiguration;
import com.example.loginuse.log.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.util.Compress;

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
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);
        sendLog = (Button)findViewById(R.id.sendLog);
        sendLog.setOnClickListener(sendLogOnClickListener);
        saveConfig = (Button)findViewById(R.id.saveConfiguration);
        saveConfig.setOnClickListener(saveConfigOnClickListener);		
        
        this.inicConfig();
    }
	
	private void inicConfig(){
		boolean gpsEnabled = LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONGPSENABLED,false);				
		String locInterval = String.valueOf(LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONINTERVAL,9000));
		String locMinDist = String.valueOf(LogConfiguration.getInstance().getProperty(LogConfiguration.LOCATIONMINDISTANCE,200));
		String actConfiddence = String.valueOf(LogConfiguration.getInstance().getProperty(LogConfiguration.ACTIVITYMINCCONFIDENCE,80));
		
		((CheckBox)findViewById(R.id.chbEnableGpsLocation)).setChecked(gpsEnabled);
		((TextView)findViewById(R.id.txtIntervalLocation)).setText(locInterval);
		((TextView)findViewById(R.id.txtMinDistanceLocation)).setText(locMinDist);
		((TextView)findViewById(R.id.txtActivityConfidence)).setText(actConfiddence);
		
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
				/**
				 * TODO borrar el archivo luego de enviar
				 */
				Compress compress = new Compress(listFiles(LogConstants.LOG_FOLDER_NAME), LogConstants.ZIP_LOG_FILE_NAME);
				compress.zip();
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
				
				LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONGPSENABLED,gpsEnabled);
				LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONINTERVAL,locInterval);
				LogConfiguration.getInstance().setProperty(LogConfiguration.LOCATIONMINDISTANCE,locMinDist);
				LogConfiguration.getInstance().setProperty(LogConfiguration.ACTIVITYMINCCONFIDENCE,actConfiddence);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	};
}


