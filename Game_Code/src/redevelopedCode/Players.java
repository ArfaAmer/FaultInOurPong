package redevelopedCode;

public class Players {

	// Constant declaration for number of life
	private final int LIFE = 3;
	private final int NOLIFE = 0;
	
	// Variable declaration that store the player score
	private int playerScore;
	private int paddlePositionX;
	private int paddlePositionY;
	
	/**
	 *  Constructor for the player - set the score to be 0 initially
	 */
	public Players(){
		playerScore = LIFE;
		//TODO: POSITION X, POSITION Y
		
	}
	
	/**
	 *  Decrease number of life of the player
	 */
	public void decrementLife(){
		playerScore--;
	}
	
	/**
	 *  Increase score for a player
	 */
	public void incrementScore(){
		playerScore++;
	}
	
	/**
	 * Get the score of a player
	 * @return playerScore - return the score of the player 
	 */
	public int getScore(){
		return playerScore;
	}
	
	/**
	 * Save the paddle positions
	 * @param x - horizontal position of the paddle
	 * @param y - vertical position of the paddle
	 */
	public void setPosition(int x, int y){
		paddlePositionX = x;
		paddlePositionY = y;
	}
	
	/**
	 * Get the paddle positions
	 * @return - horizontal and vertical positions in an array
	 */
	public int[] getPosition(){
		int[] temp = {paddlePositionX,paddlePositionY};
		return temp;
	}
	
	/**
	 * Check whether the player loses the game or not
	 * @return - boolean indicating losing or not
	 */
	public boolean checkLoss(){
		if(playerScore==NOLIFE){ return true;}
		else{ return false; }
	}
	
}
