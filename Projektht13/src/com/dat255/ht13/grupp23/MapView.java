package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Dialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

/**
 * Class representing the View in the applications main MVC. Contains the
 * GoogleMap and draws items on it
 * 
 * @author kimegenvall
 * 
 */
public class MapView implements LocationListener, Subject {

	private GoogleMap googleMap;
	private ArrayList<Observer> observers;
	private ArrayList<IdentifiableMarker> markerList;

	public MapView(FragmentActivity fragmentActivity) {
		initiateMap(fragmentActivity);
		addMarkerClickListener();
		observers = new ArrayList<Observer>();
		markerList = new ArrayList<IdentifiableMarker>();

	}

	/**
	 * Draws all available markers on the map. Iterates over a list of
	 * messagePoints (i.e markers) and adds them as identifiable markers
	 * 
	 * 
	 * @param messagePoints
	 */
	public void updateMap(ArrayList<MessagePoint> messagePoints) {
		// markerList = new ArrayList<IdentifiableMarker>();
		Iterator<MessagePoint> iterator = messagePoints.iterator();
		while (iterator.hasNext()) {
			MessagePoint messagePoint = iterator.next();
			LatLng latLng = new LatLng(messagePoint.getPosition().getX(),
					messagePoint.getPosition().getY());
			Marker marker = googleMap.addMarker(new MarkerOptions().position(
					latLng).icon(
					BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			markerList
					.add(new IdentifiableMarker(marker, messagePoint.getId()));
		}
	}

	public void addMarker() {
		notifyObservers(EventType.AddMarker, new Point(
				getLocForMarker().latitude, getLocForMarker().longitude));

	}

	/**
	 * Handles the zoom and animation of the camera when users location has
	 * changed.
	 */
	@Override
	public void onLocationChanged(Location location) {

		// Getting latitude of the current location
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();

		// Creating a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);

		// Showing the current location in Google Map
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// Zoom in the Google Map
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
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
	public void notifyObservers(EventType eventType, Point point) {
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext()) {
			iterator.next().update(eventType, point);
		}
	}

	@Override
	public void notifyObservers(EventType eventType, int id) {
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext()) {
			iterator.next().update(eventType, id);
		}
	}

	/**
	 * Initiating the GoogleMap and all necessary items for the configuration
	 * 
	 * @param fragmentActivity
	 */
	private void initiateMap(FragmentActivity fragmentActivity) {
		// Getting Google Play availability status
		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(fragmentActivity
						.getBaseContext());

		// Showing status
		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
			// not available

			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
					fragmentActivity, requestCode);
			dialog.show();

		} else { // Google Play Services are available

			// Getting reference to the SupportMapFragment of activity_main.xml
			SupportMapFragment fm = (SupportMapFragment) fragmentActivity
					.getSupportFragmentManager().findFragmentById(R.id.map);

			// Getting GoogleMap object from the fragment
			googleMap = fm.getMap();

			// Enabling MyLocation Layer of Google Map
			googleMap.setMyLocationEnabled(true);

			// Getting LocationManager object from System Service
			// LOCATION_SERVICE
			LocationManager locationManager = (LocationManager) fragmentActivity
					.getSystemService(Context.LOCATION_SERVICE);

			// Creating a criteria object to retrieve provider
			Criteria criteria = new Criteria();

			// Getting the name of the best provider
			String provider = locationManager.getBestProvider(criteria, true);

			// Getting Current Location
			Location location = locationManager.getLastKnownLocation(provider);

			if (location != null) {
				onLocationChanged(location);
				locationManager
						.requestLocationUpdates(provider, 20000, 0, this);
			}
		}

	}

	/**
	 * Adds a ClickListener to all IdentifiableMarkers.
	 */

	private void addMarkerClickListener() {
		googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker arg0) {
				Iterator<IdentifiableMarker> iterator = markerList.iterator();
				while (iterator.hasNext()) {
					IdentifiableMarker identifiableMarker = iterator.next();
					if (identifiableMarker.getMarker().equals(arg0)) {
						notifyObservers(EventType.MarkerClick,
								identifiableMarker.getId());
					}
				}
				return false;
			}

		});
	}

	/**
	 * Returns users current location.
	 * 
	 * @return
	 */
	private LatLng getLocForMarker() {
		Location myLocation = googleMap.getMyLocation();
		LatLng myLatLng;
		if(myLocation != null ){
			myLatLng = new LatLng(myLocation.getLatitude(),
					myLocation.getLongitude());
		}else{
			Projection projection = googleMap.getProjection();
			VisibleRegion visibleRegion = projection.getVisibleRegion();
			LatLngBounds bounds = visibleRegion.latLngBounds;
			LatLng nearLeft=bounds.southwest;
			LatLng nearRight=bounds.northeast;
			myLatLng=new LatLng((nearLeft.latitude+nearRight.latitude)/2,(nearLeft.longitude+nearRight.longitude)/2);
		}
		
		return myLatLng;
	}

}