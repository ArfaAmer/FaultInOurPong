package Test;

import model.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @file PlayerTest.java
 * @title PlayerTest
 * @author Pongthusiastics
 * @date 7/12/2016
 * @brief This Junit file tests the public methods in the player model
 * @details This Junit file tests whether some variables are consistent when modified.
 */
public class PlayerTest {
	
	/**
	 * Variable declarations
	 */
	Player test;

	/**
	 * @brief initializes a player object.
	 */
	@Before
	public void setUp() throws Exception {
		test = new Player();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @brief tests whether this function can decrement life by 1 properly.
	 */
	@Test
	public void testDecrementLife() {
		test.decrementLife();
		int exp = 2;
		int res = test.getScore();
		
		assertEquals(exp, res);
	}

	/**
	 *@brief tests whether this function can set score correctly.
	 */
	@Test
	public void testSetScore() {
		int exp = 1;
		test.setScore(exp);
		int res = test.getScore();
		
		assertEquals(exp, res);
		
	}

	/**
	 *@brief tests whether it can check a player loses correctly.
	 */
	@Test
	public void testCheckLoss() {
		test.setScore(0);
		Boolean exp = true;
		Boolean res = test.checkLoss();

		assertEquals(exp, res);
	}

	/**
	 * @brief tests whether the function can reset the life back to full life correctly.
	 */
	@Test
	public void testResetScore() {
		test.resetScore();
		int exp = 3;
		int res = test.getScore();
		
		assertEquals(exp, res);
	}

}
