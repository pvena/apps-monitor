package com.example.loginuse;

import java.io.File;
import java.io.FilenameFilter;

import com.example.loginuse.R;
import com.example.loginuse.util.Compress;
import com.example.loginuse.util.LogConstants;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class ConfigActivity  extends Activity{
	
	private Button sendLog;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);
        sendLog = (Button)findViewById(R.id.sendLog);
        sendLog.setOnClickListener(sendLogOnClickListener);
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
}


