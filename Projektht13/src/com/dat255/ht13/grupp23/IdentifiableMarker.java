package com.dat255.ht13.grupp23;

import com.google.android.gms.maps.model.Marker;

public class IdentifiableMarker {

	Marker marker;
	int id;
	
	public IdentifiableMarker(Marker marker, int id) {
		this.marker = marker;
		this.id = id;
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	public int getId() {
		return id;
	}
	
}
