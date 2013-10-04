package com.example.loginuse;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabsActivity extends TabActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        //startService(new Intent(this,  MyService.class));
         
        TabHost tabHost = getTabHost();
         
        // Tab for Photos
        TabSpec service = tabHost.newTabSpec("Service");
        // setting Title and Icon for the Tab
        service.setIndicator("Service", getResources().getDrawable(R.drawable.ic_login));
        Intent ServiceIntent = new Intent(this, ServiceActivity.class);
        service.setContent(ServiceIntent);
         
        // Tab for Songs
        TabSpec config = tabHost.newTabSpec("Config.");        
        config.setIndicator("Config.", getResources().getDrawable(R.drawable.ic_config));
        Intent songsIntent = new Intent(this, ConfigActivity.class);
        config.setContent(songsIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(service); // Adding photos tab
        tabHost.addTab(config); // Adding songs tab        
    }
}
