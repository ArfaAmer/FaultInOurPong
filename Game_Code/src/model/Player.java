package model;

/**
 * @file Player.java
 * @title Player
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class represents a player for the game
 * @details This class contains the information for a player, including number of life and his/her current score.
 */
public class Player {

	/**
	 * Defines constant number of life of a player
	 * - the player has 3 lives in total
	 * - the player loses if the number of life is 0
	 */
	private final int LIFE = 3;
	private final int NOLIFE = 0;
	
	/**
	 * Defines the current number of life of the player.
	 */
	private int score;
	

	/**
	 * @brief Constructor for the player
	 * @details sets the current life is the full life (3).
	 */
	public Player(){
		score = LIFE;
	}
	
	/**
	 *  @brief loses score if the ball touches his/her border.
	 *  @details decreases the number of life by 1.
	 */
	public void decrementLife(){
		score--;
	}
	
	/**
	 * @brief gets the score of a player
	 * @return playerScore returns the score of the player .
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * @brief checks whether the player loses the game or not
	 * @return a boolean that is used to indicate whether the player is losing or not
	 */
	public boolean checkLoss(){
		if(score==NOLIFE){ return true;}
		else{ return false; }
	}
}
