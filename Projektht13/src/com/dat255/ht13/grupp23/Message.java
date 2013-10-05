package com.dat255.ht13.grupp23;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {

	private String text;
	private Date date;

	public static final Parcelable.Creator<Message> CREATOR
	= new Parcelable.Creator<Message>() {
		@Override
		public Message createFromParcel(Parcel in) {
			return new Message(in);
		}
		
		public Message[] newArray(int size) {
			return new Message[size];
		}

	};


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

	/*
	 * Default constructor.
	 */
	public Message() {
		text = "";
		date = new Date();
	}

	public Message(String text) {
		this.text = text;
		date = new Date();
	}

	public Message(String text, Date date) {
		this.text = text;
		this.date = date;
	}

	/*
	 * Copy constructor.
	 */
	public Message(Message message) {
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
			if (this.text.equals(other.getText())
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}


	public Message( Parcel source )
	{
		text = source.readString();
		date = new Date( source.readLong() );
	}
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(text);
		dest.writeLong(date.getTime());
	}

}
