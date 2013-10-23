package com.example.loginuse.util;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import com.example.loginuse.log.LogConfiguration;

public class SoapFileTask {

	public final String SOAP_ACTION = "http://tempuri.org/UploadFile";
	public  final String OPERATION_NAME = "UploadFile"; 
	public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";	
	private String SOAP_ADDRESS = "";
	
	public SoapFileTask() 
	{ 
		this.SOAP_ADDRESS = LogConfiguration.getInstance().getProperty(LogConfiguration.WebServiceURL, "") + LogConfiguration.WebServiceName;
	}

	
	public String Execute(byte[] bytes,String fileName)
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		
		request.addProperty("bytes", bytes);
		request.addProperty("fileName", fileName);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		envelope.implicitTypes = true;
					
		HttpTransportSE httpTransport = new HttpTransportSE(this.SOAP_ADDRESS,10000);
		httpTransport.debug = true;
		Object response=null;
		try
		{		
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception)
		{
			response=exception.toString();
		}
		return response.toString();
	}
}
