package com.example.projektht13;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		   map = ((SupportMapFragment) getSupportFragmentManager()
	                .findFragmentById(R.id.map)).getMap();
	        if (map == null) {
	            Toast.makeText(this, "Google Maps not available", 
	                Toast.LENGTH_LONG).show();
	        }
		
		/*Test*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
