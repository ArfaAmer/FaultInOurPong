package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BallTest {

	Ball test;
	
	@Before
	public void setUp() throws Exception {
		test = new Ball();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetPositionX() {
		int exp = 20;
		test.setPositionX(exp);
		int res = test.getPositionX();
		
		assertEquals(exp, res);
	}

	@Test
	public void testSetPositionY() {
		int exp = 20;
		test.setPositionY(exp);
		int res = test.getPositionY();

		assertEquals(exp, res);
	}

	@Test
	public void testGetSize() {
		int exp = 20;
		int res = test.getSize();

		assertEquals(exp, res);
	}

}
