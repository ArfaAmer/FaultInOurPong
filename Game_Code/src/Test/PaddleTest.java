package Test;

import model.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @file PaddleTest.java
 * @title PaddleTest
 * @author Pongthusiastics
 * @date 7/12/2016
 * @brief This Junit file tests the public methods in the paddle model
 * @details This Junit file tests whether some variables are consistent when modified.
 */
public class PaddleTest {
	
	/**
	 * Variable declaration
	 */
	Paddle test;

	/**
	 * @brief initializes a paddle object.
	 */
	@Before
	public void setUp() throws Exception {
		test = new Paddle();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @brief tests whether the x position of the paddle is set correctly.
	 */
	@Test
	public void testSetPositionX() {
		int exp = 20;
		test.setPositionX(exp);
		int res = test.getPositionX();

		assertEquals(exp, res);
	}

	/**
	 * @brief tests whether the y position of the paddle is set correctly.
	 */
	@Test
	public void testSetPositionY() {
		int exp = 20;
		test.setPositionY(exp);
		int res = test.getPositionY();

		assertEquals(exp, res);
	}

}
