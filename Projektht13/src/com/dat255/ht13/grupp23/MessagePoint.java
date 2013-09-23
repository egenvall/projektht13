package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class MessagePoint {

	private ArrayList<Message> messages;
	private Point position;

	public ArrayList<Message> getMessages() {
		ArrayList<Message> messagesCopy = new ArrayList<Message>();
		Iterator<Message> iterator = messages.iterator();
		while (iterator.hasNext()) {
			messagesCopy.add(new Message(iterator.next()));
		}
		return messagesCopy;
	}

	public Point getPosition() {
		return new Point(position);
	}

	public void AddMessage(Message message) {
		messages.add(message);
	}

	/*
	 * Default constructor.
	 */
	public MessagePoint() {

	}

	/*
	 * Copy constructor.
	 */
	public MessagePoint(MessagePoint messagePoint) {
		this.messages = messagePoint.getMessages();
		this.position = messagePoint.getPosition();
	}

}
