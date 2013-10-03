package com.dat255.ht13.grupp23.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.dat255.ht13.grupp23.MapsActivity;

public class MapsActivityTest
extends ActivityInstrumentationTestCase2<MapsActivity> {
	
	private MapsActivity mActivity;
	private TextView mView;
	
	@SuppressWarnings({ "deprecation" })
	public MapsActivityTest() {
		super(MapsActivity.class);
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