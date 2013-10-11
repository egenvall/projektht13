package com.dat255.ht13.grupp23;

import java.util.Date;

public class Message {

	private final String defaultName = "Anonymous";
	private final String defaultText = "";

	private String name;
	private String text;
	private Date date;

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	/**
	 * Return a deep copy off the date.
	 * 
	 * @return copy off the date.
	 */
	public Date getDate() {
		return new Date(date.getTime());
	}

	/**
	 * Default constructor.
	 */
	public Message() {
		name = defaultName;
		text = defaultText;
		date = new Date();
	}

	/**
	 * Constructor with specified message text.
	 * 
	 * @param text
	 *            the text of the Message.
	 */
	public Message(String text) {
		name = defaultName;
		this.text = text;
		date = new Date();
	}

	/**
	 * Constructor with specified message text and date. Is used when data from
	 * the database needs to be recreated as a Message object.
	 * 
	 * @param text
	 *            the text of the Message.
	 * @param date
	 *            the date of the Message.
	 */
	public Message(String text, Date date) {
		name = defaultName;
		this.text = text;
		this.date = date;
	}

	/**
	 * Constructor with specified message name and text.
	 * 
	 * @param name
	 *            the name of the author
	 * @param text
	 *            the text of the Message.
	 */
	public Message(String name, String text) {
		this.name = name;
		this.text = text;
		date = new Date();
	}

	/**
	 * Constructor with specified message name, text and date. Is used when data
	 * from the database needs to be recreated as a Message object.
	 * 
	 * @param name
	 *            the name of the author
	 * @param text
	 *            the text of the Message.
	 * @param date
	 *            the date of the Message.
	 */
	public Message(String name, String text, Date date) {
		this.name = name;
		this.text = text;
		this.date = date;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param message
	 *            the Message object to be copied.
	 */
	public Message(Message message) {
		this.name = message.getName();
		this.text = message.getText();
		this.date = message.getDate();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof Message) {
			Message other = (Message) object;
			if (this.name.equals(other.getName())
					&& this.text.equals(other.getText())
					&& this.date.equals(other.getDate())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

}

