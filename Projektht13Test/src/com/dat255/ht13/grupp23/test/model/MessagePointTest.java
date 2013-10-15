package com.dat255.ht13.grupp23.test.model;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.dat255.ht13.grupp23.model.Message;
import com.dat255.ht13.grupp23.model.MessagePoint;
import com.dat255.ht13.grupp23.model.Point;

import junit.framework.TestCase;

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
	
	public MessagePointTest() {
		
	}
	@Before
	public void setUp() throws Exception {
	
		message1 = new Message("text1");
		message2 = new Message("text1");
		position = new Point(1,1);
	
		messageTestAdd = new Message("add");
		messages = new ArrayList<Message>();
		
		NoParamCon = new MessagePoint(1);
		ParamCon = new MessagePoint(messages, position,1);
		CopyCon = new MessagePoint(ParamCon);
		
		equalsSymmetric_1 = new MessagePoint(1);
		equalsSymmetric_2 = new MessagePoint(1);
		
		equalsSymmetric_3 = new MessagePoint(messages, position, 2);
		
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