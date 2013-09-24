package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class Model {

	private ArrayList<MessagePoint> messagePoints;

	/**
	 * Returns a deep copy off the messagePoints list.
	 * @return copy of messagePoints.
	 */
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
	public Model() {
		messagePoints = new ArrayList<MessagePoint>();
	}

	public void AddMessagePoint(MessagePoint messagePoint) {
		messagePoints.add(messagePoint);
	}

	public void AddMessagePoint(Point point) {
		messagePoints.add(new MessagePoint(new ArrayList<Message>(), point));
	}

	public void RemoveMessagePoint(MessagePoint messagePoint) {

	}

	public void RemoveMessagePoint(Point point) {

	}

}
