package com.dat255.ht13.grupp23.test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.dat255.ht13.grupp23.IdentifiableMarker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class IdentifiableMarkerTest extends TestCase {

	IdentifiableMarker IdMarker;
	Marker marker;
	
	public IdentifiableMarkerTest() {	
	}
	
	@Before
	public void setUp() throws Exception {
		
		marker = new Marker(null);
		IdMarker = new IdentifiableMarker(marker, 1);
	}

	@Test
	public void testGetId(){
		assertEquals(1, IdMarker.getId());
	}

}
