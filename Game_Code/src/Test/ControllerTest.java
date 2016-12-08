package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.*;
import startGame.*;
import view.*;

/**
 * @file ControllerTest.java
 * @title ControllerTest
 * @author Pongthusiastics
 * @date 7/12/2016
 * @brief This Junit file tests the public methods in the controller
 * @details This Junit file tests whether some variables are consistent between controller and model/view.
 */
public class ControllerTest {

	static GameModel m;
	static GameView v;
	static GameController c;
	
	
	/**
	 * @brief setups for the test cases
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		m=new GameModel();
		v=new GameView();
		c=new GameController(v,m);
		
	}		
	
	/**
	 * @brief tests the bomb velocity in the x direction is generated properly. 
	 */
	@Test
	public void testGetBombVelX(){
		int expected1 = 1;
		int expected2 = -1;
		int result = c.getbombVelX();
		
		if(result==expected1) assertEquals(expected1, result);
		else if(result==expected2) assertEquals(expected2,result);
		else fail();
	}
	
	/**
	 * @brief tests the bomb velocity in the y direction is generated properly. 
	 */
	@Test
	public void testGetBombVelY(){
		int expected1 = 1;
		int expected2 = -1;
		int result = c.getbombVelY();
		
		if(result==expected1) assertEquals(expected1, result);
		else if(result==expected2) assertEquals(expected2,result);
		else fail();
	}
	
	/**
	 * @brief tests the ball velocity in the x direction is generated properly.
	 */
	@Test
	public void testGetVelX(){
		int expected = 1;
		assertEquals(expected, c.getVelX());
	}
	
	/**
	 * @brief tests the ball velocity in the y direction is generated properly.
	 */
	@Test
	public void testGetVelY(){
		int expected = 1;
		assertEquals(expected, c.getVelY());
	}
	
}
