package model;

/**
 * @file Paddle.java
 * @title Paddle
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class defines a paddle
 * @details This class saves the information of a paddle, including its position, height, width, and inset between the paddle and the screen.
 */
public class Paddle {

	/**
	 * The position of a paddle
	 * - horizontal position x
	 * - vertical position y
	 */
	private int positionX;
	private int positionY;
	
	/**
	 * The property of a paddle
	 * - the length of a paddle
	 * - the width of a paddle
	 * - the inset between a paddle and the screen frame
	 */
	private final int HEIGHT = 10;
	private final int WIDTH = 80;
	private final int INSET = 10;
	
	//TODO 
	private int speed;
	
	/**
	 * @brief Constructor for a paddle
	 * @details Constructor initialize the starting position of a paddle.
	 */
	public Paddle(){
		positionX = 0;
		positionY = 0;
	}
	
	/**
	 * @brief sets the x-position of the paddle.
	 * @param x is the x position of the paddle.
	 * @throws ArithmeticException x-position could not be set out of the game frame.
	 */
	public void setPositionX(int x){
		if (x<0) throw new ArithmeticException("Cannot set paddle position out of frame.");
		positionX = x;
	}
	
	/**
	 * @brief sets the y-position of the paddle.
	 * @param y is the y position of the paddle.
	 * @throws ArithmeticException y-position could not be set out of the game frame.
	 */
	public void setPositionY(int y){
		if (y<0) throw new ArithmeticException("Cannot set paddle position out of frame");
		positionY = y;
	}

	/**
	 * @brief returns the x position of the paddle.
	 * @return positionX
	 */
	public int getPositionX(){
		return positionX;
	}
	
	/**
	 * @brief returns the y position of the paddle.
	 * @return positionY
	 */
	public int getPositionY(){
		return positionY;
	}
	
	/**
	 * @brief returns the width of the paddle.
	 * @return WIDTH
	 */
	public int getWidth(){
		return WIDTH;
	}
	
	/**
	 * @brief returns the height of the paddle.
	 * @return HEIGHT
	 */
	public int getHeight(){
		return HEIGHT;
	}
	
	/**
	 * @brief returns the inset between the paddle and the screen.
	 * @return INSET
	 */
	public int getInset(){
		return INSET;
	}
}
