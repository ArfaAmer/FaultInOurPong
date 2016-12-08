package model;


/**
 * @file GameModel.java
 * @title GameModel
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class represents a ball on the pong game
 * @details This class saves the information of a ball, including its position,
 *          size and the speed.
 */
public class GameModel {

	/**
	 * The ball object for the game
	 */
	private Ball b, bomb;
	/**
	 * The two paddle in the game, one for the player and the other for the computer
	 */
	private Paddle p_player, p_computer;
	/**
	 * The two players in the game, one for the user and the other for the computer
	 */
	private Player player, computer;
	
	/**
	 * @brief Constructor for the game Model
	 * @details Contains all the data and models for the game, including the player, paddle, and the ball.
	 */
	public GameModel(){
		/**
		 * Declara variables/instances for the model
		 * - regular ball
		 * - bomb
		 * - paddle for the player
		 * - paddle for the computer
		 * - score and life for the player
		 * - score and life for the ai
		 */
		b = new Ball();
		bomb = new Ball();
		p_player = new Paddle();
		p_computer = new Paddle();
	
		player = new Player();
		computer = new Player();
	}
	
	/**
	 * @brief sets the x and y positions of a ball
	 * @param x is the x position of the ball
	 * @param y is the y position of the ball
	 * @throws ArithmeticException ball position could not be set out of the game frame.
	 */
	public void setBall(int x, int y){
		if (x<0 || y<0) throw new ArithmeticException("Invalid ball size.");
		b.setPositionX(x);
		b.setPositionY(y);
	}
	
	/**
	 * @brief sets the x and y positions of a bomb
	 * @param x is the x position of the bomb
	 * @param y is the y position of the bomb
	 */
	public void setBomb(int x, int y){
		b.setPositionX(x);
		b.setPositionY(y);
	}
	
	
	/**
	 * @brief gets the Ball object
	 * @return b is the ball object
	 */
	public Ball getBall(){
		return b;
	}
	
	/**
	 * @brief gets the bomb object
	 * @return bomb is the bomb object
	 */
	public Ball getBomb(){
		return bomb;
	}
	
	/**
	 * @brief gets the user paddle object
	 * @return p_player
	 */
	public Paddle getPlayerPaddle(){
		return p_player;
	}
	
	/**
	 * @brief gets the computer paddle object
	 * @return p_computer
	 */
	public Paddle getComputerPaddle(){
		return p_computer;
	}
	
	/**
	 * @brief gets the player object
	 * @return player
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * @brief gets the computer object
	 * @return computer
	 */
	public Player getComputer(){
		return computer;
	}
}
