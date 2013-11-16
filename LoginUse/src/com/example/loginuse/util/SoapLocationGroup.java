package com.example.loginuse.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.loginuse.log.LogConfiguration;
import com.example.loginuse.log.LogFormat;

public class SoapLocationGroup extends AsyncTask<Void, Void, String>{

	public final String SOAP_ACTION = "http://tesis.org/getLocationGroups";
	public  final String OPERATION_NAME = "getLocationGroups"; 
	public  final String WSDL_TARGET_NAMESPACE = "http://tesis.org/";
	private String SOAP_ADDRESS = "";
	
	private String result;
	
	public SoapLocationGroup() 
	{ 
		this.SOAP_ADDRESS = LogConfiguration.getInstance().getProperty(LogConfiguration.WebServiceURL, "") + LogConfiguration.WebServiceName;
	}
	
	@Override
	protected String doInBackground(Void... v)
	{
		try
		{
			SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);			
			
			request.addProperty("phoneId", LogConfiguration.getInstance().getPhoneId());
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;	 
			envelope.setOutputSoapObject(request);	        		
			
	        HttpTransportSE httpTransport = new HttpTransportSE(this.SOAP_ADDRESS);
			
				
			httpTransport.call(SOAP_ACTION, envelope);
			
			SoapObject bodyIn = (SoapObject) envelope.bodyIn;
			
			LogFormat.setLocationGroups(bodyIn.getPropertyAsString(0));						
		}
		catch (Exception exception)
		{		
			this.result = "Refresh Locations Fail.";
			return "Fail.";
		}
		this.result = "Refresh Locations OK.";
		return "OK";
	}
	
	@Override
    protected void onPostExecute(String result) { 
			Toast.makeText(LogConfiguration.getInstance().getContext(), this.result, Toast.LENGTH_SHORT).show();
    }
}
