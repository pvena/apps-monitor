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
        startService(new Intent(this,  MyService.class));
         
        TabHost tabHost = getTabHost();
         
        TabSpec config = tabHost.newTabSpec("Config.");        
        config.setIndicator("Config.", getResources().getDrawable(R.drawable.ic_config));
        Intent songsIntent = new Intent(this, ConfigActivity.class);
        config.setContent(songsIntent);
        
        TabSpec map = tabHost.newTabSpec("Map");        
        map.setIndicator("Map.", getResources().getDrawable(R.drawable.ic_map));
        Intent mapIntent = new Intent(this, MapActivity.class);
        map.setContent(mapIntent);	
         
        tabHost.addTab(config); 
        tabHost.addTab(map);
        
   }
}
