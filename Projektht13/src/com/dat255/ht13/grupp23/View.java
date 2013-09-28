package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Dialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.location.LocationListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class View implements Subject {
	GoogleMap googleMap;
	FragmentActivity mainActivity;
	private ArrayList<Observer> observers;

	/*
	 * Show MessagePoints on map etc.
	 */
	public View(FragmentActivity mainActivity) {
		this.mainActivity = mainActivity;
		mainActivity.setContentView(R.layout.activity_maps);
		observers = new ArrayList<Observer>();
		
		
		
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext()) {
			iterator.next().update();
		}
	}

	
	
}
