package com.example.loginuse;

import com.example.loginuse.Log.SaveLog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity /*implements OnClickListener*/ {
	//private Button btn;
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView helloTxt = (TextView)findViewById(R.id.fileText);
        helloTxt.setText(SaveLog.getInstance().readLogFile());
           
        startService(new Intent(this,  MyService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onPause() {         
    		super.onPause();
    }
    @Override
    protected void onResume() {
            super.onResume();            
    }     
    
}
