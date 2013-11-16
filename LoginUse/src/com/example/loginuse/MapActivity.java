package com.example.loginuse;

import java.util.Enumeration;
import java.util.Hashtable;

import com.example.loginuse.log.LogFormat;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MapActivity extends FragmentActivity  {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);                
        
        // Get a handle to the Map Fragment
        GoogleMap map = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng group = null;
        String key = null;
        
        Hashtable<String,LatLng> groups = LogFormat.getLocationGroup();
		Enumeration<String> enumKey = groups.keys();
		
		while(enumKey.hasMoreElements()) {
		    key = enumKey.nextElement();
		    group = groups.get(key);
		    map.addMarker(new MarkerOptions().title(key).snippet(key + "snippet").position(group));
		}        
        
        map.setMyLocationEnabled(true);
       
    }
}
