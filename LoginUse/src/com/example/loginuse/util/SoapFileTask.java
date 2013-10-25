package com.example.loginuse.util;

import java.io.File;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;

import com.example.loginuse.log.LogConfiguration;
import com.example.loginuse.log.LogFormat;

public class SoapFileTask extends AsyncTask<Void, Void, String>{

	public final String SOAP_ACTION = "http://tempuri.org/UploadFile";
	public  final String OPERATION_NAME = "UploadFile"; 
	public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";	
	private String SOAP_ADDRESS = "";
	
	private File file;
	private String fileName;
	
	public SoapFileTask(File file,String fileName) 
	{ 
		this.file = file;
		this.fileName = fileName;
		this.SOAP_ADDRESS = LogConfiguration.getInstance().getProperty(LogConfiguration.WebServiceURL, "") + LogConfiguration.WebServiceName;
	}
	
	@Override
	protected String doInBackground(Void... v)
	{
		Object response=null;
		
		try
		{
			SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		
			request.addProperty("bytes", LogFormat.getFileToBytes(this.file));
			request.addProperty("fileName", this.fileName);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			new MarshalBase64().register(envelope); 
			envelope.bodyOut = request;
			envelope.dotNet = true;	 
			//envelope.encodingStyle = SoapSerializationEnvelope.XSD;			       
	        envelope.setOutputSoapObject(request);	        		
			
	        HttpTransportSE httpTransport = new HttpTransportSE(this.SOAP_ADDRESS);
			httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");		
			httpTransport.debug = true;
			
				
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception)
		{
			response=exception.toString();
		}
		return response.toString();
	}
	
	@Override
    protected void onPostExecute(String result) {        
    }
}
