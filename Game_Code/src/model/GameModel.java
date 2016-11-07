package model;


/**
 * @file GameModel.java
 * @title GameModel
 * @author Pongthusiastics
 * @date 4/11/2016
 * @brief This class represents a ball on the pong game
 * @details This class saves the information of a ball, including its position,
 *          size and the speed.
 */
public class GameModel {

	/**
	 * The ball object for the game
	 */
	private Ball b1;
	/**
	 * The two paddle in the game, one for the player and the other for the computer
	 */
	private Paddle p_player, p_computer;
	/**
	 * The two players in the game, one for the user and the other for the computer
	 */
	private Player player, computer;
	
	private int ballX, ballY;
	private int playerX, playerY;
	private int compX, compY;
	
	
	/**
	 * @brief Constructor for the game Model
	 * @details Contains all the data and models for the game, including the player, paddle, and the ball.
	 * @param ballX is the x-position of the ball
	 * @param ballY is the y-position of the ball
	 * @param playerX is the x-position of the user's paddle
	 * @param playerY is the y-position of the user's paddle
	 * @param compX is the x-position of the user's paddle
	 * @param compY is the y-position of the user's paddle
	 */
	public GameModel(int ballX, int ballY, int playerX, int playerY, int compX, int compY){
		b1 = new Ball(ballX, ballY);
		p_player = new Paddle(playerX, playerY);
		p_computer = new Paddle(compX, compY);
		
		//TODO
		player = new Player();
		computer = new Player();
	}
	
	/**
	 * @brief sets the x and y positions of a ball
	 * @param x is the x position of the ball
	 * @param y is the y position of the ball
	 */
	public void setBall(int x, int y){
		b1.setPositionX(x);
		b1.setPositionY(y);
	}
	
	
	/**
	 * @brief gets the Ball object
	 * @return b1
	 */
	public Ball getBall(){
		return b1;
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
