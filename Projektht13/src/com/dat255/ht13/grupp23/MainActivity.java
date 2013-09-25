package com.dat255.ht13.grupp23;

import com.dat255.ht13.grupp23.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements LocationListener {
	GoogleMap googleMap;
	Controller controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Getting Google Play availability status
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

		// Showing status
		if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
			dialog.show();

		}else { // Google Play Services are available

			// Getting reference to the SupportMapFragment of activity_main.xml
			SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

			// Getting GoogleMap object from the fragment
			googleMap = fm.getMap();

			// Enabling MyLocation Layer of Google Map
			googleMap.setMyLocationEnabled(true);
			googleMap.getUiSettings().setRotateGesturesEnabled(true);
			
			// Getting LocationManager object from System Service LOCATION_SERVICE
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			// Creating a criteria object to retrieve provider
			Criteria criteria = new Criteria();

			// Getting the name of the best provider
			String provider = locationManager.getBestProvider(criteria, true);

			// Getting Current Location
			Location location = locationManager.getLastKnownLocation(provider);

			if(location!=null){
				onLocationChanged(location);
			}
			locationManager.requestLocationUpdates(provider, 5000, 0, this);
		}


		/* Test */

		//controller = new Controller();
		//controller.initiate();
	}
	
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
	
	public void getLocation(){
		
	
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
    
    public boolean onCreateOptionsMenu(Menu menu) { 
    	getMenuInflater().inflate(R.menu.main, menu); 
    	return true; 
    
    }
    
    // This function is made for putting markers on the current location.
    // It sends a LatLng to .position in add marker options
    public LatLng getLocForMarker(){
    	
    	 Location myLocation = googleMap.getMyLocation();
         LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                 myLocation.getLongitude());
        
         return myLatLng;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        switch (item.getItemId()) {
 
        case R.id.menu_sethybrid:
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            break;
        
        case R.id.menu_zoomin:
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            break;
 
        case R.id.menu_zoomout:
            googleMap.animateCamera(CameraUpdateFactory.zoomOut());
            break;
        
        case R.id.menu_addmarker:
	        googleMap.addMarker(new MarkerOptions()
	        .position(getLocForMarker())
	        .icon(BitmapDescriptorFactory
            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	        break;
	    }
        return true;
    }
}