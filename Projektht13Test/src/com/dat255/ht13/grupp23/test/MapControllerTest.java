package com.dat255.ht13.grupp23.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.dat255.ht13.grupp23.MapController;

public class MapControllerTest
extends ActivityInstrumentationTestCase2<MapController> {
	
	private MapController mActivity;
	private TextView mView;
	
	public MapControllerTest() {
		super(MapController.class);
	}

	@Override
	protected void setUp() throws Exception {        
		super.setUp();        
		mActivity = this.getActivity();        
		mView = (TextView) mActivity.findViewById
                                        (com.dat255.ht13.grupp23.R.id.textView1);        
		
}
	public void testFragmentManager() {
		mActivity = getActivity();
		assertNotNull(mActivity.getSupportFragmentManager());
	}
	
	
	
	
	
}