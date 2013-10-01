package com.dat255.ht13.grupp23.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import android.test.InstrumentationTestRunner;
import android.test.suitebuilder.annotation.SmallTest;

import com.dat255.ht13.grupp23.Message;
import com.dat255.ht13.grupp23.MessagePoint;
import com.dat255.ht13.grupp23.Point;


public class MessagePointTest extends TestCase {
	
	ArrayList<Message> messages;
	Message message1;
	Message message2;
	Point position;
	
	Message messageTestAdd;
	
	MessagePoint NoParamCon;
	MessagePoint ParamCon;
	MessagePoint CopyCon;
	
	MessagePoint equalsSymmetric_1;
	MessagePoint equalsSymmetric_2;
	
	MessagePoint equalsSymmetric_3;
	
	public MessagePointTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		message1 = new Message("text1");
		message2 = new Message("text1");
		position = new Point(1,1);
	
		messageTestAdd = new Message("add");
		messages = new ArrayList<Message>();
		messages.add(message1);
		messages.add(message2);
		
		NoParamCon = new MessagePoint();
		ParamCon = new MessagePoint(messages, position);
		CopyCon = new MessagePoint(ParamCon);
		
		equalsSymmetric_1 = new MessagePoint();
		equalsSymmetric_2 = new MessagePoint();
		
		equalsSymmetric_3 = new MessagePoint(messages, position);
		
	}
	
	@Test
	public void testNoParamCon(){
		assertEquals(0, (NoParamCon.getMessages()).size());
		assertEquals(new Point(0,0), NoParamCon.getPosition());
		
	}@Test
	public void testParamCon(){
		assertEquals(messages, ParamCon.getMessages()); //Change on Message to also copy DATE 
		
	}@Test
	public void testCopyCon(){
		assertEquals(new Point(1,1), CopyCon.getPosition());
		
	}
	@Test
	public void testGetPosition(){
		assertEquals(new Point(1,1), ParamCon.getPosition());
	}

	@Test 
	public void testAddMessage(){
		NoParamCon.AddMessage(messageTestAdd);
		assertEquals(1, NoParamCon.getMessages().size());
		assertEquals(messageTestAdd, (NoParamCon.getMessages()).get(0));
	}
	@Test
	public void testEquals_Symmetric(){ //Equals, hashcode check messages and position values
		assertTrue(equalsSymmetric_1.equals(equalsSymmetric_2) && equalsSymmetric_2.equals(equalsSymmetric_2));
		assertTrue(equalsSymmetric_1.hashCode() == equalsSymmetric_2.hashCode());
		
		assertFalse(equalsSymmetric_1.equals(equalsSymmetric_3) && equalsSymmetric_2.equals(equalsSymmetric_3));
		assertNotSame(equalsSymmetric_1.hashCode(), equalsSymmetric_3.hashCode());
		
	}
	
}
