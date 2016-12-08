package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * @file PongGameDisplay.java
 * @title PongGameDisplay
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class construct the view of the pong game
 * @details This class gets data from controller and display them on the screen
 */
public class PongGameDisplay extends JPanel{

	/**
	 * Variable declarations for the display
	 * - frame dimension
	 * - ball information
	 * - bomb information
	 * - player scores
	 * - paddle information
	 */
	private int frameWidth;
	private int frameHeight;
	
	private int scoreTop, scoreBottom;
	
	private int ballX, ballY;
	private int bombX, bombY;
	private int bottomPadX, bottomPadY;
	private int topPadX, topPadY;
	private boolean first;
	private int ballSize;
	private int padW, padH;
	private int inset;
	
	private int gameMode;
	private final int SINGLE=0;
	private final int ADVANCE=1;
	private boolean startBomb;
	
	/**
	 * @brief Constructor for PongGameDisplay
	 * @details Constructor by default set the game to single mode
	 */
	public PongGameDisplay(){
		first = true;
		gameMode = SINGLE;
		startBomb=false;
	}
	
	/**
	 * @brief draws shapes on the screen
	 * @details when the game is started, by default draws the ball and paddles in the middle, otherwise, draws objects by passed in values.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	// create an object for graphics (rectangles)
		frameHeight = getHeight();				// get game frame/screen size
		frameWidth = getWidth();

		/**
		 * Initial positioning
		 * - ball at the center of the screen
		 * - paddle in the middle of the frame width
		 */
		if (first) {
			bottomPadX = frameWidth / 2 - padW / 2;	// setups for the paddles positions - in the middle of the screen
			topPadX = bottomPadX;
			ballX = frameWidth / 2 - ballSize / 2;	// setups for the ball positions - in the middle of the screen
			ballY = frameHeight / 2 - ballSize / 2;
			first = false;						// setup completed
		}
		
		/**
		 * Draw rectangles by passed in values
		 */
		// bottom pad
		Rectangle2D bottomPad = new Rectangle(bottomPadX, frameHeight - padH - inset, padW, padH); // creating the object for bottom paddle
		g2d.fill(bottomPad);
		
		// top pad
		Rectangle2D topPad = new Rectangle(topPadX, inset, padW, padH); // creating paddle object for the top paddle
		g2d.fill(topPad);
		
		/**
		 * Draw the ball by passed in values
		 */
		Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, 20, 20);  // creating the ball object for the game
		g2d.fill(ball);
		
		/**
		 * Draw the bomb if the mode is the advance mode
		 */
		if(gameMode == ADVANCE && startBomb==true){
			
			Ellipse2D bomb = new Ellipse2D.Double(bombX, bombY, 20, 20); 
			g2d.setPaint(new Color(255,0,0));
			g2d.fill(bomb);
		}
		
		
		/**
		 * Draw scores on the screen by passed in values
		 */
		String scoreB = "Player: " + scoreBottom; 	// saving the score of the bottom paddle
		String scoreT = "Ai: " + scoreTop;			// saving the score of the top paddle 
		g2d.drawString(scoreB, 10, frameHeight / 2);						// printing the score of the bottom paddle in the screen
		g2d.drawString(scoreT, frameWidth - 50, frameHeight / 2);			// printing the score of the top paddle in the screen
	}
		
	/**
	 * @brief sets the positions of the ball
	 * @param x is the x-position of the ball
	 * @param y is the y-position of the ball
	 */
	public void setBall(int x, int y){
		ballX = x;
		ballY = y;
	}
	
	/**
	 * @brief sets the positions of the player paddle
	 * @param x is the x-position of the player paddle
	 * @param y is the y-position of the player paddle
	 */
	public void setBottomPaddle(int x, int y) {
		bottomPadX = x;
		bottomPadY = y;
	}
	
	/**
	 * @brief sets the positions of the AI paddle
	 * @param x is the x-position of the AI paddle
	 * @param y is the y-position of the AI paddle
	 */
	public void setTopPaddle(int x, int y) {
		topPadX = x;
		topPadY = y;
	}
	
	/**
	 * @brief sets the positions of the bomb
	 * @param x is the x-position of the bomb
	 * @param y is the y-position of the bomb
	 */
	public void setBomb(int x, int y){
		bombX = x;
		bombY = y;
	}
	
	/**
	 * @brief defines the game mode contains a bomb
	 * @details set the flag for the advance to be true
	 */
	public void timeForBomb(){
		startBomb = true;
	}
	
	/**
	 * @brief defines the game mode does not contain a bomb
	 * @details set the flag for the advance to be false
	 */
	public void noBomb(){
		startBomb = false;
	}
	
	/**
	 * @brief checks if it should start displaying the bomb
	 * @return startBomb
	 */
	public boolean bombTime(){
		return startBomb;
	}
	
	
	/**
	 * @brief sets the size of the ball
	 * @param s is the ball size
	 */
	public void setBallSize(int s){
		ballSize = s;
	}
	
	/**
	 * @brief gets the size of the ball
	 * @return ballSize
	 */
	public int getBallSize(){
		return ballSize;
	}
	
	/**
	 * @brief sets x-position for the player paddle
	 * @param s is the x-position
	 */
	public void setBottom(int x){
		bottomPadX = x;
	}
	
	/**
	 * @brief sets x-position for the ai paddle
	 * @param s is the x-position
	 */
	public void setTop(int x){
		topPadX = x;
	}
	
	/**
	 * @brief gets the x-position of the player paddle
	 * @return bottomPadX
	 */
	public int getBottomX(){
		return bottomPadX;
	}
	
	/**
	 * @brief gets the y-position of the player paddle
	 * @return bottomPadY
	 */
	public int getBottomY(){
		return bottomPadY;
	}
	
	/**
	 * @brief sets the score for ai
	 * @param s is the score
	 */
	public void setTopScore(int s){
		scoreTop=s;
	}
	
	/**
	 * @brief gets the score of AI
	 * @return scoreTop
	 */
	public int getTopScore(){
		return scoreTop;
	}
	
	
	/**
	 * @brief sets the score for player
	 * @param s is the score
	 */
	public void setBottomScore(int s){
		scoreBottom = s;
	}
	
	public int getBottomScore(){
		return scoreBottom;
	}
	
	/**
	 * @brief gets the x-position of the ball
	 * @return ballX
	 */
	public int getBallX(){
		return ballX;
	}
	
	/**
	 * @brief gets the y-position of the ball
	 * @return ballY
	 */
	public int getBallY(){
		return ballY;
	}
	
	/**
	 * @brief gets the x-position of the bomb
	 * @return bombX
	 */
	public int getBombX(){
		return bombX;
	}
	
	/**
	 * @brief gets the y-position of the bomb
	 * @return bombY
	 */
	public int getBombY(){
		return bombY;
	}
	
	/**
	 * @brief sets the width of the paddle
	 * @param w is the width
	 */
	public void setPaddleWidth(int w){
		padW = w;
	}
	
	/**
	 * @brief sets the height of the paddle
	 * @param h is the height
	 */
	public void setPaddleHeight(int h){
		padH = h;
	}
	
	/**
	 * @brief sets the distance between frame and the paddle
	 * @param i is the inset
	 */
	public void setInset(int i){
		inset = i;
	}
	
	/**
	 * @brief sets the game mode to be advanced
	 * @details set the flag to advance
	 */
	public void setAdvance(){
		gameMode = ADVANCE;
	}
	
	/**
	 * @brief gets the mode of the game
	 * @return gameMode
	 */
	public int getMode(){
		return gameMode;
	}
}
