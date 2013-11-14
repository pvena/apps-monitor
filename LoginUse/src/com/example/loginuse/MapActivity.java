package com.example.loginuse;

import com.google.android.gms.maps.CameraUpdateFactory;
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

        LatLng casaDePolo = new LatLng(-37.323997,-59.129154);
        LatLng casaDePablo = new LatLng(-37.327657,-59.134357);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(casaDePolo, 13));

        map.addMarker(new MarkerOptions()
                .title("Casa de Polo")
                .snippet("Aca vive el Polo.")
                .position(casaDePolo));
        
        map.addMarker(new MarkerOptions()
        .title("Casa de Pablo")
        .snippet("Aca vive el Pablo.")
        .position(casaDePablo));
    }
}
