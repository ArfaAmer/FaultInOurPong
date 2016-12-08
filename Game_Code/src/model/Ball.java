package model;

/**
 * @file Ball.java
 * @title Ball
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class represents a ball on the pong game
 * @details This class saves the information of a ball, including its position,
 *          size and the speed.
 */

public class Ball {

	/**
	 * The X and Y position of a ball on the screen
	 */
	private int positionX;
	private int positionY;
	/**
	 * The size of a ball
	 */
	private final int SIZE = 20;

	/**
	 * @brief Constructor for Ball
	 * @details Constructor accepts the x and y position of the ball
	 */
	public Ball() {
		positionX = 0;
		positionY = 0;
	}

	/**
	 * @brief sets the x position of the ball
	 * @param x-position
	 *            of the ball
	 * @throws ArithmeticException ball x-position could not be set out of the game frame.
	 */
	public void setPositionX(int x) throws ArithmeticException {
		if(x<0) throw new ArithmeticException("You cannot set negative position.");
		else positionX = x;
		
	}

	/**
	 * @brief sets the y position of the ball
	 * @param y-position
	 *            of the ball
	 * @throws ArithmeticException ball y-position could not be set out of the game frame.
	 */
	public void setPositionY(int y) throws ArithmeticException {
		if(y<0) throw new ArithmeticException("You cannot set negative position.");
		positionY = y;
	}

	/**
	 * @brief gets the x-position of the ball
	 * @return positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @brief gets the y-position of the ball
	 * @return positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @brief gets the size of the ball
	 * @return SIZE
	 */
	public int getSize() {
		return SIZE;
	}

}
