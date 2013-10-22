package com.dat255.ht13.grupp23.test.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import android.os.Parcel;

import com.dat255.ht13.grupp23.model.Message;
import com.dat255.ht13.grupp23.model.ParcelableMessage;

public class ParcelableMessageTest extends TestCase {

	Message message;
	ParcelableMessage pMessage;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testParcelableMessage(){
		message = new Message();
		pMessage = new ParcelableMessage(message);

		Parcel parcel = Parcel.obtain();
		pMessage.writeToParcel(parcel, 0);
		//done writing, now reset parcel for reading
		parcel.setDataPosition(0);
		//finish round trip
		ParcelableMessage createFromParcel = ParcelableMessage.CREATOR.createFromParcel(parcel);
		assertEquals(message.getText(), createFromParcel.getText());
	}
}
