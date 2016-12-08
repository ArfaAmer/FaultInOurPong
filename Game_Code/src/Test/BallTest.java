package Test;

import model.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @file BallTest.java
 * @title BallTest
 * @author Pongthusiastics
 * @date 7/12/2016
 * @brief This Junit file tests the public methods in the ball model
 * @details This Junit file tests whether some variables are consistent when modified.
 */
public class BallTest {

	/**
	 * Variable declaration
	 */
	Ball test;
	
	/**
	 * @brief initializes a ball object.
	 */
	@Before
	public void setUp() throws Exception {
		test = new Ball();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @brief tests whether the x position of the ball is set correctly.
	 */
	@Test
	public void testSetPositionX() {
		int exp = 20;
		test.setPositionX(exp);
		int res = test.getPositionX();
		
		assertEquals(exp, res);
	}

	/**
	 * @brief tests whether the y position of the ball is set correctly.
	 */
	@Test
	public void testSetPositionY() {
		int exp = 20;
		test.setPositionY(exp);
		int res = test.getPositionY();

		assertEquals(exp, res);
	}

	/**
	 * @brief tests whether the size of the ball is the constant correctly.
	 */
	@Test
	public void testGetSize() {
		int exp = 20;
		int res = test.getSize();

		assertEquals(exp, res);
	}
	

}
