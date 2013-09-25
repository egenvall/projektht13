package com.dat255.ht13.grupp23;

import android.app.Dialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.location.LocationListener;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class View {
	GoogleMap googleMap;
	FragmentActivity mainActivity;

	/*
	 * Show MessagePoints on map etc.
	 */
	public View(FragmentActivity mainActivity) {
		this.mainActivity = mainActivity;

	}




	public void startLocation(){
		// Getting Google Play availability status
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mainActivity.getBaseContext());

		// Showing status
		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
			// not available

			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this.mainActivity,
					requestCode);
			dialog.show();

		} else { // Google Play Services are available

			// Getting reference to the SupportMapFragment of activity_main.xml
			SupportMapFragment fm = (SupportMapFragment) this.mainActivity.getSupportFragmentManager()
					.findFragmentById(R.id.map);

			// Getting GoogleMap object from the fragment
			googleMap = fm.getMap();

			// Enabling MyLocation Layer of Google Map
			googleMap.setMyLocationEnabled(true);

			// Getting LocationManager object from System Service
			// LOCATION_SERVICE
			LocationManager locationManager = (LocationManager)this.mainActivity.getSystemService(Context.LOCATION_SERVICE);


			// Creating a criteria object to retrieve provider
			Criteria criteria = new Criteria();

			// Getting the name of the best provider
			String provider = locationManager.getBestProvider(criteria, true);

			// Getting Current Location
			Location location = locationManager.getLastKnownLocation(provider);

			if (location != null) {
				

				try{
					if(this.mainActivity instanceof LocationListener){
						((LocationListener) mainActivity).onLocationChanged(location);
						locationManager.requestLocationUpdates(provider, 20000, 0, (LocationListener)mainActivity);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}

			}

		}
	}










}
