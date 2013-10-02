package com.dat255.ht13.grupp23;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;

public class Controller implements Observer {
	private static Controller controllerinstance = null;
	private Model model;
	private MainActivity mainActivity;
	


	protected Controller() {
	}
	
	public static Controller getInstance(){
		if(controllerinstance == null){
			controllerinstance = new Controller();
		}
		
		return controllerinstance;
		
	}
	
	public void initiate(MainActivity activity){
		this.mainActivity = activity;
		model = new Model();
		mainActivity.startActivity(new Intent(mainActivity,MapsActivity.class));
	}
	
	



	@Override
	public void update(EventType eventType) {
		if(eventType == EventType.MarkerClick){
			System.out.println("Methods to do stuff when a marker is clicked");
			
		}


	}

}
