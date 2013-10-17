package com.example.loginuse.util;

import java.io.File;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;/*
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;*/
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * 
 * @author Polo
 *
 */
public class UploadFileTask extends AsyncTask<String, Void, String> {

	private Activity activity;

	/**
	 * 
	 * @param activity
	 */
	public UploadFileTask(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 
	 */
	protected String doInBackground(String... file) {
		String result = null;
		try {

			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

			HttpPost httppost = new HttpPost("http://192.168.1.100:80/nanoupload/upload.php");
			File newFile = new File(file[0]);

			/*MultipartEntity multipartEntity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntity.addPart("archivo", new FileBody(newFile));*/

			//httppost.setEntity(multipartEntity);
			result = httpclient.execute(httppost, new FileUploadResponseHandler());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 */
	protected void onPostExecute(String feed) {
		Toast.makeText(activity, feed, Toast.LENGTH_SHORT).show();
	}
}