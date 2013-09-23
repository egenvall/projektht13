package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class Model {

	private ArrayList<MessagePoint> messagePoints;

	public ArrayList<MessagePoint> getMessagePoints() {
		ArrayList<MessagePoint> messagePointsCopy = new ArrayList<MessagePoint>();
		Iterator<MessagePoint> iterator = messagePoints.iterator();
		while (iterator.hasNext()) {
			messagePointsCopy.add(new MessagePoint(iterator.next()));
		}
		return messagePointsCopy;
	}

	/*
	 * Get MessagePoints from database etc.
	 */
	public void initiateModel() {

	}

	public void AddMessagePoint(MessagePoint messagePoint) {

	}

	public void AddMessagePoint(Point point) {

	}

	public void RemoveMessagePoint(MessagePoint messagePoint) {

	}

	public void RemoveMessagePoint(Point point) {

	}

}
