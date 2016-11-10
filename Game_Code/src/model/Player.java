package model;

public class Player {

	// Constant declaration for number of life
	private final int LIFE = 3;
	private final int NOLIFE = 0;
	
	// Variable declaration for storing the player score
	private int score;
	

	public Player(){
		score = LIFE;
	}
	
	/**
	 *  @brief decreases number of life of the player
	 */
	public void decrementLife(){
		score--;
	}
	
	/**
	 *  @brief increases score for a player
	 */
	public void incrementScore(){
		score++;
	}
	
	/**
	 * @brief gets the score of a player
	 * @return playerScore - return the score of the player 
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * @brief checks whether the player loses the game or not
	 * @return - boolean indicating losing or not
	 */
	public boolean checkLoss(){
		if(score==NOLIFE){ return true;}
		else{ return false; }
	}
}
