package com.example.loginuse.soap;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.loginuse.Configuration.LogConfiguration;


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
		
			request.addProperty("name", this.userName);
			request.addProperty("phoneId", LogConfiguration.getInstance().getPhoneId());
			request.addProperty("version", LogConfiguration.getInstance().getAndroidVersion());
			request.addProperty("phoneModel", LogConfiguration.getInstance().getDeviceName());
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;	 
			envelope.setOutputSoapObject(request);	        		
			
	        HttpTransportSE httpTransport = new HttpTransportSE(this.SOAP_ADDRESS);		
				
			httpTransport.call(SOAP_ACTION, envelope);
			
			SoapObject bodyIn = (SoapObject) envelope.bodyIn;			
		
			this.result = "Register " + bodyIn.getPropertyAsString(0);
		}
		catch (Exception exception){			
			return "Fail.";
		}		
		return "OK";
	}
	
	@Override
    protected void onPostExecute(String result) { 
		if(this.result != null && !this.result.equals(""))
			Toast.makeText(LogConfiguration.getInstance().getContext(), this.result, Toast.LENGTH_SHORT).show();
		else
		{
			String error = "Problem to Register.";
			Toast.makeText(LogConfiguration.getInstance().getContext(), error, Toast.LENGTH_SHORT).show();
		}
    }
}

