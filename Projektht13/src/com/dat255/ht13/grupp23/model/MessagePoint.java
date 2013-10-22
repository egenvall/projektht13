package com.dat255.ht13.grupp23.model;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * @copyright (c) 2013 Kim Egenvall, Carl Fredriksson, Jonas Ha, Johan Wallander, Timocin Zaynal,Josef Haddad
 * @license MIT
 *
 */
public class MessagePoint {

	private ArrayList<Message> messages;
	private Point position;
	private int id;

	/**
	 * Returns a deep copy of the messages list.
	 * 
	 * @return Copy of messages.
	 */
	public ArrayList<Message> getMessages() {
		ArrayList<Message> messagesCopy = new ArrayList<Message>();
		Iterator<Message> iterator = messages.iterator();
		while (iterator.hasNext()) {
			messagesCopy.add(new Message(iterator.next()));
		}
		return messagesCopy;
	}

	/**
	 * Returns a copy of the position.
	 * 
	 * @return copy of position.
	 */
	public Point getPosition() {
		return new Point(position);
	}

	public int getId() {
		return id;
	}

	public void AddMessage(Message message) {
		messages.add(message);
	}

	/**
	 * Constructor with id parameter.
	 * 
	 * @param id
	 *            the id of the MessagePoint.
	 */
	public MessagePoint(int id) {
		this.messages = new ArrayList<Message>();
		this.position = new Point(0, 0);
		this.id = id;
	}

	/**
	 * Constructor with Messages, position and id parameters.
	 * 
	 * @param messages
	 *            the Messages of the MessagePoint.
	 * @param position
	 *            the Position of the MessagePoint.
	 * @param id
	 *            the id of the MessagePoint.
	 */
	public MessagePoint(ArrayList<Message> messages, Point position, int id) {
		this.messages = messages;
		this.position = position;
		this.id = id;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param messagePoint
	 *            the MessagePoint object to be copied.
	 */
	public MessagePoint(MessagePoint messagePoint) {
		this.messages = messagePoint.getMessages();
		this.position = messagePoint.getPosition();
		this.id = messagePoint.getId();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof MessagePoint) {
			MessagePoint other = (MessagePoint) object;
			if (this.messages.equals(other.getMessages())
					&& this.position.equals(other.getPosition())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messages == null) ? 0 : messages.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

}
