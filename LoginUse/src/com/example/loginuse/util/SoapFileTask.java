package com.example.loginuse.util;

import java.io.File;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.loginuse.log.LogConfiguration;
import com.example.loginuse.log.LogFormat;

public class SoapFileTask extends AsyncTask<Void, Void, String>{

	public final String SOAP_ACTION = "http://tesis.org/UploadFile";
	public  final String OPERATION_NAME = "UploadFile"; 
	public  final String WSDL_TARGET_NAMESPACE = "http://tesis.org/";
	private String SOAP_ADDRESS = "";
	
	private File file;
	private String result;
	private Compress compress;
	
	public SoapFileTask(File file,Compress compress) 
	{ 
		this.file = file;
		this.compress = compress;
		this.SOAP_ADDRESS = LogConfiguration.getInstance().getProperty(LogConfiguration.WebServiceURL, "") + LogConfiguration.WebServiceName;
	}
	
	@Override
	protected String doInBackground(Void... v)
	{
		try
		{
			SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
			
			request.addProperty("data", LogFormat.getFileToBase64Encode(this.file));
			request.addProperty("phoneId", LogConfiguration.getInstance().getPhoneId());
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;	 
			envelope.setOutputSoapObject(request);	        		
			
	        HttpTransportSE httpTransport = new HttpTransportSE(this.SOAP_ADDRESS);			
				
			httpTransport.call(SOAP_ACTION, envelope);			
			
			SoapObject bodyIn = (SoapObject) envelope.bodyIn;			
			
			this.result = "Response " + bodyIn.getPropertyAsString(0);
			
			if(this.result.equals("Response OK"))			
				this.compress.deleteListFiles();
			else
				this.compress.deleteLastZipFile();
		}
		catch (Exception ex)
		{
			Log.v("Send File", ex.getMessage());
		}
		return this.result;
	}
	
	@Override
    protected void onPostExecute(String result) {
		if(this.result != null && !this.result.equals(""))
			Toast.makeText(LogConfiguration.getInstance().getContext(), this.result, Toast.LENGTH_SHORT).show();
		else
		{
			String error = "Problem to Send File.";
			Toast.makeText(LogConfiguration.getInstance().getContext(), error, Toast.LENGTH_SHORT).show();
		}
    }
}
