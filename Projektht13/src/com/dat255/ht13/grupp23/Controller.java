package com.dat255.ht13.grupp23;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;

public class Controller implements Observer {

	private Model model;
	private MapsActivity mapsactivity;
	


	public Controller(FragmentActivity mainActivity) {
		model = new Model();
		mapsactivity = new MapsActivity();
		mainActivity.startActivity(new Intent(mainActivity,MapsActivity.class));
		mapsactivity.addObserver(this);





	}



	@Override
	public void update(EventType eventType) {
		if(eventType == EventType.MarkerClick){

			
		}


	}

}
