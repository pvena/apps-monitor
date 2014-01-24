package com.example.loginuse.location;

import com.google.android.gms.maps.model.LatLng;

public class LogLocationGroup {
	private float longitud;
	private float latitud;
	private int count;
	private String name;
	
	public void setName(String value){
		this.name = value;
	}
	public String getName(){
		return this.name;
	}
	public void setLongitud(float value){
		this.longitud = value;
	}
	public float getLongitud(){
		return this.longitud;
	}
	public void setLatitud(float value){
		this.latitud = value;
	}
	public float getLatitud(){
		return this.latitud;
	}
	public void setCount(int value){
		this.count = value;
	}
	public int getCount(){
		return this.count;
	}
	
	public LatLng getLatLng(){
		return new LatLng(this.latitud, this.longitud);
	}
}
