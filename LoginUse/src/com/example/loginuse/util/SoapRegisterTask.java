package com.example.loginuse.util;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.loginuse.log.LogConfiguration;


public class SoapRegisterTask extends AsyncTask<Void, Void, String>{

	public final String SOAP_ACTION = "http://tesis.org/RegisterUser";
	public  final String OPERATION_NAME = "RegisterUser"; 
	public  final String WSDL_TARGET_NAMESPACE = "http://tesis.org/";
	private String SOAP_ADDRESS = "";
	
	private String userName;
	private String result;
	
	public SoapRegisterTask(String userName) 
	{
		this.userName = userName;
		this.SOAP_ADDRESS = LogConfiguration.getInstance().getProperty(LogConfiguration.WebServiceURL, "") + LogConfiguration.WebServiceName;
	}
	
	@Override
	protected String doInBackground(Void... v)
	{
		try
		{
			SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		
			PropertyInfo pi = new PropertyInfo();
			pi.setName("name");
			pi.setValue(this.userName);
			pi.setType(String.class);
			request.addProperty(pi);
			
			pi = new PropertyInfo();
			pi.setName("phoneId");
			pi.setValue(LogConfiguration.getInstance().getPhoneId());
			pi.setType(String.class);
			request.addProperty(pi);
			
			pi = new PropertyInfo();
			pi.setName("version");
			pi.setValue(LogConfiguration.getInstance().getAndroidVersion());
			pi.setType(String.class);
			request.addProperty(pi);
			
			pi = new PropertyInfo();
			pi.setName("phoneModel");
			pi.setValue(LogConfiguration.getInstance().getDeviceName());
			pi.setType(String.class);
			request.addProperty(pi);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;	 
			envelope.setOutputSoapObject(request);	        		
			
	        HttpTransportSE httpTransport = new HttpTransportSE(this.SOAP_ADDRESS);		
				
			httpTransport.call(SOAP_ACTION, envelope);
			
			SoapObject bodyIn = (SoapObject) envelope.bodyIn;			
		
			this.result = "Register " + bodyIn.getPropertyAsString(0);
		}
		catch (Exception exception)		{		
			
			return "Fail.";
		}		
		return "OK";
	}
	
	@Override
    protected void onPostExecute(String result) { 
			Toast.makeText(LogConfiguration.getInstance().getContext(), this.result, Toast.LENGTH_SHORT).show();
    }
}

