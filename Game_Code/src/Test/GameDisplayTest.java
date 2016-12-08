package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import view.*;

/**
 * @file GameDisplayTest.java
 * @title GameDisplayTest
 * @author Pongthusiastics
 * @date 7/12/2016
 * @brief This Junit file tests the public methods in the game framework
 * @details This Junit file tests whether some variables are consistent when modified.
 */
public class GameDisplayTest {

	/**
	 * Variable declaration
	 */
	static PongGameDisplay p;
	
	/**
	 * @brief initializes a PongGameDisplay object
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p = new PongGameDisplay();
	}

	/**
	 * @brief tests if the ball positions are set correctly in the display.
	 * Test method for {@link view.PongGameDisplay#setBall(int, int)}.
	 */
	@Test
	public void testSetBall() {
		int expectedx = 10;
		int expectedy = 20;
		p.setBall(expectedx, expectedy);
		
		int actualx = p.getBallX();
		int actualy = p.getBallY();
		assertEquals(expectedx, actualx);
		assertEquals(expectedy, actualy);
	}

	/**
	 * @brief tests if the position of bottom paddle is set correctly.
	 * Test method for {@link view.PongGameDisplay#setBottomPaddle(int, int)}.
	 */
	@Test
	public void testSetBottomPaddle() {
		int expected = 10;
		p.setBottom(expected);
		
		int actual = p.getBottomX();
		assertEquals(expected, actual);
	}

	/**
	 * @brief tests if the positions of a bomb is set correctly.
	 * Test method for {@link view.PongGameDisplay#setBomb(int, int)}.
	 */
	@Test
	public void testSetBomb() {
		int expectedx = 10;
		int expectedy = 20;
		p.setBomb(expectedx, expectedy);
		
		int actualx = p.getBombX();
		int actualy = p.getBombY();
		assertEquals(expectedx, actualx);
		assertEquals(expectedy, actualy);
	}


	/**
	 * @brief tests if the state of the displaying bomb is set correctly
	 * Test method for {@link view.PongGameDisplay#bombTime()}.
	 */
	@Test
	public void testBombTime() {
		/**
		 * If the bomb is set to display, then display the bomb.
		 */
		boolean expectedBomb = true;
		p.timeForBomb();
		boolean actualBomb = p.bombTime();
		assertEquals(expectedBomb, actualBomb);
		
		/**
		 * If the bomb is not set for display, then make bomb invisible.
		 */
		expectedBomb = false;
		p.noBomb();
		actualBomb = p.bombTime();
		assertEquals(expectedBomb, actualBomb);
	}

	/**
	 * @brief tests if the size of ball is set correctly.
	 * Test method for {@link view.PongGameDisplay#setBallSize(int)}.
	 */
	@Test
	public void testSetBallSize() {
		int expected = 10;
		p.setBallSize(expected);
		
		int actual = p.getBallSize();
		assertEquals(expected, actual);
	}

	/**
	 * @brief tests if the score of AI is set correctly.
	 * Test method for {@link view.PongGameDisplay#setTopScore(int)}.
	 */
	@Test
	public void testSetTopScore() {
		int expected = 10;
		p.setTopScore(expected);
		
		int actual = p.getTopScore();
		assertEquals(expected, actual);
	}

	/**
	 * @brief tests if the score of the player is set correctly.
	 * Test method for {@link view.PongGameDisplay#setBottomScore(int)}.
	 */
	@Test
	public void testSetBottomScore() {
		int expected = 10;
		p.setBottomScore(expected);
		
		int actual = p.getBottomScore();
		assertEquals(expected, actual);
	}

	/**
	 * @brief tests if the mode of the game is set correctly.
	 * Test method for {@link view.PongGameDisplay#setAdvance()}.
	 */
	@Test
	public void testSetAdvance() {
		p.setAdvance();
		
		int expected = 1;
		int actual = p.getMode();
		assertEquals(expected, actual);
	}

}
