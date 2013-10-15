package com.dat255.ht13.grupp23.test.model;
import java.util.Date;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;

import com.dat255.ht13.grupp23.model.Message;

public class MessageTest extends TestCase{

	Message NoParamCon;
	Message ParamCon;
	Message CopyCon;
	
	Message equalsSymmetric_1;
	Message equalsSymmetric_2;
	
	Message equalsSymmetric_3;	
	
	public MessageTest() {
	}

	
	@Before
	public void setUp() throws Exception {
		NoParamCon = new Message();
		ParamCon = new Message("ConWithParam");
		CopyCon = new Message(ParamCon);
		
		equalsSymmetric_1 = new Message();
		equalsSymmetric_2 = new Message();
		
		equalsSymmetric_3 = new Message("Foo Bar");
	}

	@Test
	public void testNoParamCon() {
		assertTrue(NoParamCon instanceof Message);
		assertEquals("", NoParamCon.getText());
	}
	@Test
	public void testParamCon() {
		assertTrue(ParamCon instanceof Message);
		assertEquals("ConWithParam", ParamCon.getText());
	}
	@Test
	public void testCopyCon() {
		assertEquals(ParamCon, CopyCon);
	}
	@Test
	public void testGetDate() {
		assertTrue(NoParamCon.getDate() instanceof Date);
	}
	@Test
	public void testGetText() {
		assertEquals("", NoParamCon.getText());
		assertEquals("ConWithParam", ParamCon.getText());
		assertEquals("ConWithParam", CopyCon.getText());
	}
	@Test
	public void testEquals_Symmetric(){ //Equals, hashcode check date and text values
		assertTrue(equalsSymmetric_1.equals(equalsSymmetric_2) && equalsSymmetric_2.equals(equalsSymmetric_2));
		assertTrue(equalsSymmetric_1.hashCode() == equalsSymmetric_2.hashCode());
		
		assertFalse(equalsSymmetric_1.equals(equalsSymmetric_3) && equalsSymmetric_2.equals(equalsSymmetric_3));
		assertNotSame(equalsSymmetric_1.hashCode(), equalsSymmetric_3.hashCode());
		
	}

}
