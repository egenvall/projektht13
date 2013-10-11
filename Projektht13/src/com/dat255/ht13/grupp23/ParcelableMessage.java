package com.dat255.ht13.grupp23;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class for sending Messages between Android Activities. This class is not to
 * be used in the model, to prevent coupling between the model and the
 * framework.
 * 
 */
public class ParcelableMessage extends Message implements Parcelable {

	public static final Parcelable.Creator<ParcelableMessage> CREATOR = new Parcelable.Creator<ParcelableMessage>() {
		@Override
		public ParcelableMessage createFromParcel(Parcel in) {
			return new ParcelableMessage(in);
		}

		public ParcelableMessage[] newArray(int size) {
			return new ParcelableMessage[size];
		}

	};

	/**
	 * Constructor with a Parcel parameter for passing ParcelableMessages
	 * between Activities.
	 * 
	 * @param source
	 *            the Parcel that possess the data.
	 */
	public ParcelableMessage(Parcel source) {
		super(source.readString(), source.readString(), new Date(
				source.readLong()));
	}

	/**
	 * Constructor with a String parameter. Calls the super constructor.
	 * 
	 * @param name
	 *            the name of the Message.
	 * @param text
	 *            the text of the Message.
	 */
	public ParcelableMessage(String name, String text) {
		super(name, text);
	}

	/**
	 * Constructor with a Message parameter. Calls the super constructor. Used
	 * for quickly making a ParcelableMessage from Message.
	 * 
	 * @param message
	 *            the Message to make a ParcelableMessage from.
	 */
	public ParcelableMessage(Message message) {
		super(message);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getName());
		dest.writeString(getText());
		dest.writeLong(getDate().getTime());
	}

}
