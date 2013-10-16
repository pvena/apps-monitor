package com.example.loginuse.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * 
 * @author Polo
 *
 */
public class FileUploadResponseHandler implements ResponseHandler<String> {
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
	 */
	@Override
	public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

		HttpEntity r_entity = response.getEntity();
		String responseString = EntityUtils.toString(r_entity);
		Log.d("UPLOAD", responseString);

		return responseString;
	}
}
