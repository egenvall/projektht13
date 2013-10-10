package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

import com.dat255.ht13.grupp23.database.MessagePointsTable;

public class MapModel {

	private ArrayList<MessagePoint> messagePoints;

	/**
	 * Returns a deep copy off the messagePoints list.
	 * 
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

	public MessagePoint getMessagePointById(int id) {
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if (msgp.getId() == id) {
				return msgp;
			}
		}
		return null;
	}

	public MapModel() {
		messagePoints = new ArrayList<MessagePoint>();
	}

	/**
	 * Add a MessagePoint to position.
	 * 
	 * @param position
	 *            the position of the MessagePoint.
	 */
	public void AddMessagePoint(Point position, int id) {
		messagePoints.add(new MessagePoint(new ArrayList<Message>(), position,
				id));
	}

	/**
	 * Remove the MessagePoint with the matching id.
	 * 
	 * @param id
	 *            the id of the MessagePoint to be removed.
	 */
	public void RemoveMessagePointById(int id) {
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if (msgp.getId() == id) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Add a Message to the MessagePoint with the correct id.
	 * 
	 * @param id
	 *            the id of the MessagePoint that the Message should be added
	 *            to.
	 * @param message
	 *            the Message to be added to the MessagePoint.
	 */
	public void AddMessageToMessagePoint(int id, Message message) {
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if (msgp.getId() == id) {
				msgp.AddMessage(message);
				break;
			}
		}
	}
}