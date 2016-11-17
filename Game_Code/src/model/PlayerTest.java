package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	Player test;

	@Before
	public void setUp() throws Exception {
		test = new Player();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecrementLife() {
		test.decrementLife();
		int exp = 2;
		int res = test.getScore();
		
		assertEquals(exp, res);
	}

	@Test
	public void testSetScore() {
		int exp = 1;
		test.setScore(exp);
		int res = test.getScore();
		
		assertEquals(exp, res);
		
	}

	@Test
	public void testCheckLoss() {
		test.setScore(0);
		Boolean exp = true;
		Boolean res = test.checkLoss();

		assertEquals(exp, res);
	}

	@Test
	public void testResetScore() {
		test.resetScore();
		int exp = 3;
		int res = test.getScore();
		
		assertEquals(exp, res);
	}

}
