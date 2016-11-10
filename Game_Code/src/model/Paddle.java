package model;

public class Paddle {

	private int positionX;
	private int positionY;
	
	private final int HEIGHT = 10;
	private final int WIDTH = 40;
	private final int INSET = 10;
	
	//TODO 
	private int speed;
	
	
	public Paddle(){
		positionX = 0;
		positionY = 0;
	}
	
	/**
	 * @brief sets the x-position of the paddle
	 * @param x is the x position of the paddle
	 */
	public void setPositionX(int x){
		positionX = x;
	}
	
	/**
	 * @brief sets the y-position of the paddle
	 * @param y is the y position of the paddle
	 */
	public void setPositionY(int y){
		positionY = y;
	}

	/**
	 * @brief returns the x position of the paddle
	 * @return positionX
	 */
	public int getPositionX(){
		return positionX;
	}
	
	/**
	 * @brief returns the y position of the paddle
	 * @return positionY
	 */
	public int getPositionY(){
		return positionY;
	}
	
	/**
	 * @brief returns the width of the paddle
	 * @return WIDTH
	 */
	public int getWidth(){
		return WIDTH;
	}
	
	/**
	 * @brief returns the height of the paddle
	 * @return HEIGHT
	 */
	public int getHeight(){
		return HEIGHT;
	}
	
	/**
	 * @brief returns the inset between the paddle and the screen
	 * @return INSET
	 */
	public int getInset(){
		return INSET;
	}
}
