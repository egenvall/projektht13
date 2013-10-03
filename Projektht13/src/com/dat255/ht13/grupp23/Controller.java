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
	public void update(EventType eventType, int id) {
		if(eventType == EventType.MarkerClick){
			System.out.println("Methods to do stuff when a marker is clicked");
			//model.AddMessageToMessagePoint(id, new Message("Text"));
		}
	}
	
	@Override
	public void update(EventType eventType, Point position) {
		
		if(eventType == EventType.AddMarker){
			System.out.println("A marker was added at: " + "X: " + position.getX() + "Y: "+ position.getY());
			model.AddMessagePoint(position);
		}
	}

}
