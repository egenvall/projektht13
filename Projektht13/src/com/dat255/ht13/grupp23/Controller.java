package com.dat255.ht13.grupp23;

import android.support.v4.app.FragmentActivity;

public class Controller implements Observer {

	private Model model;
	private View view;

	public Controller(FragmentActivity mainActivity) {
		model = new Model();
		view = new View(mainActivity);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
