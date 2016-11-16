package startGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import model.*;
import view.*;

/**
 * @file GameController.java
 * @title GameController
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class is the controller for the game.
 * @details This class cooperates with model and view and give direction to the game.
 */
public class GameController{

	/**
	 * Import model and view to the controller (this interface).
	 */
	private GameView v;
	private GameModel m;

	/**
	 * Variable declarations for storing the game view windows
	 * - welcome page
	 * - mode page for showing different modes
	 * - tutorial page for giving instructions to the students
	 */
	private Welcome w;
	private Mode mode;
	private Tutorial tut;

	/**
	 * Declare a variable for storing the key pressed records
	 */
	private HashSet<String> keys = new HashSet<String>();

	/**
	 * Variable declarations for the game
	 * - frame dimension
	 * - paddle information
	 * - ball information
	 * - bomb information
	 * - player information
	 */
	private JFrame gameFrame;
	private int frameWidth, frameHeight;
	private PongGameDisplay gameDisplay;

	private int velX=1, velY=1;
	private int padWidth, padHeight;
	private int bottomPadX, bottomPadY, topPadX, topPadY;
	private Ball b;
	private Paddle paddle_player, paddle_ai;
	private int ballX, ballY, ballSize;
	private int scoreTop, scoreBottom;
	private int inset;

	private final int SINGLE = 0;
	private final int ADVANCE = 1;
	private int gameMode;
	
	private Ball bomb;
	private int bombX, bombY, bombSize;
	
	private Player player;
	private Player ai;

	private Timer t;
	private long startTime;
	private long endTime;
	private double timeElapsed;

	public GameController(GameView v, GameModel m){
		this.v = v;
		this.m = m;
		gameMode = SINGLE;

		/**
		 * Obtain the window frame dimentions
		 */
		frameWidth = this.v.getFrameWidth();
		frameHeight = this.v.getFrameHeight();

		/**
		 * Setups for ball in the Model
		 */
		b = this.m.getBall();
		ballSize = b.getSize();
		ballX = frameWidth / 2 - ballSize / 2;	// setups for the ball positions - in the middle of the screen
		ballY = frameHeight / 2 - ballSize / 2;
		b.setPositionX(ballX);
		b.setPositionY(ballY);
		
		/**
		 * Setups for the bomb in the Model
		 */
		bomb = this.m.getBall();
		bombSize = bomb.getSize();
		bombX = frameWidth / 2 - bombSize / 2;	// setups for the ball positions - in the middle of the screen
		bombY = frameHeight / 2 - bombSize / 2;
		bomb.setPositionX(bombX);
		bomb.setPositionY(bombY);

		/**
		 * Setups for the paddles in the Model
		 * - obtain paddle dimensions
		 * - initialize paddle positions for the player paddle
		 * - initialize paddle positions for the ai paddle
		 */
		paddle_player = this.m.getPlayerPaddle();		// Paddle setup for the player
		padWidth = paddle_player.getWidth();			// Obtain paddle dimensions
		padHeight = paddle_player.getHeight();
		inset = paddle_player.getInset();
		bottomPadX = frameWidth / 2 - padWidth / 2;	
		topPadX = bottomPadX;	
		paddle_player.setPositionX(bottomPadX);
		paddle_player.setPositionY(bottomPadY);
		paddle_ai = this.m.getComputerPaddle();			// Paddle setup for the ai

		/**
		 * Setups for the players in the Model
		 * - initialize number of life for the player and the ai
		 */
		player = this.m.getPlayer();
		ai = this.m.getComputer();
		scoreBottom = player.getScore();
		scoreTop = ai.getScore();

		/**
		 * Setups for the View
		 * - obtain windows from the view
		 * - add action listener for different windows
		 */
		w = this.v.getWelcome();										// Welcome page
		w.addListener(new WelcomepageListener());
		mode = this.v.getmode();										// Game mode page
		mode.addListener(new ModeListener());
		ImageIcon image = new ImageIcon("./Resources/tutorial.png");	// Tutorial page
		v.tutorialPage(image);
		tut = v.getTutorial();
		tut.addListener(new TutorialListener());
		gameFrame = this.v.getGameFrame();								// Game page
		gameDisplay = this.v.getGame();
		
		gameDisplay.setTopScore(scoreTop);
		gameDisplay.setBottomScore(scoreBottom);
		gameDisplay.setBallSize(ballSize);
		gameDisplay.setPaddleHeight(padHeight);
		gameDisplay.setPaddleWidth(padWidth);
		gameDisplay.setInset(inset);
		
		gameDisplay.addKeyListener(new GameListener());
		gameDisplay.setFocusable(true);
		gameDisplay.setFocusTraversalKeysEnabled(false);

		/**
		 * Initialize the start time and end time for a player
		 */
		startTime = 0;
		endTime = 0;
		timeElapsed = 0;
		
	}

	/**
	 * @author Pongthusiastics
	 * @date 13/11/2016
	 * @brief action listener for the welcome page
	 * @details detects which button is pressed by the user and do the corresponding actions
	 */
	class WelcomepageListener implements ActionListener{

		/** 
		 * @brief	detects the actions on the buttons and defines actions to do
		 * @details	- redirect to game mode page
		 * 			- redirect to load game
		 * 			- redirect to view score 
		 * 			- redirect to view tutorial
		 * 			- exit the program
		 * @param e is the action performed on the button
	     */
		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * Save the action performed into a variable
			 */
			Object source = e.getSource();
			
			/**
			 * - Check for the button pressed
			 * - Do actions depending on the action performed
			 */
			if(source==w.getStart()){					// If clicked the start button
				mode.setVisible(true);					// Open the game mode page
				w.setVisible(false);
			}else if(source==w.load()){					// If clicked the load button
				
				//TODO
				try{									// Read data from a saved record
					FileReader fr = new FileReader("./Resources/userData.txt");
					BufferedReader br = new BufferedReader(fr);

					System.out.println("can load data");

					br.close();
				}catch(Exception exp){
					v.cannotLoadMessage();
				}
			}else if(source==w.highScores()){			// If clicked the high score button

				//TODO
				try{									// Open and display the record
					FileReader fr = new FileReader("./Resources/gameScore.txt");
					BufferedReader br = new BufferedReader(fr);

					System.out.println("can display high score");

					br.close();
				}catch(Exception exp){
					v.noFileAvailMessage();
				}
			}else if(source==w.tutorial()){				// If clicked the tutorial button
				w.setVisible(false);					// Open the tutorial page
				tut.setVisible(true);

			}else if(source==w.exit()){					// If clicked the exit button
				System.exit(0);							// Terminate the program
			}

		}

	}

	/**
	 * @author Pongthusiastics
	 * @date 13/11/2016
	 * @brief action listener for the game mode page
	 * @details detects which button is pressed by the user and do the corresponding actions
	 */
	class ModeListener implements ActionListener{

		/** 
		 * @brief	detects the actions on the buttons and defines actions to do
		 * @details - redirect to game with easy level
		 * 			- redirect to game with obstacles
		 * @param e is the action performed on the button
	     */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			/**
			 * Save the action performed into a variable
			 */
			Object source = e.getSource();

			/**
			 * - Check for the button pressed
			 * - Do actions depending on the action performed
			 */
			if(source == mode.getSingle()){			// If clicked the basic single mode button
				mode.setVisible(false);				// Start the game with single mode
				gameFrame.setVisible(true);
				t.start();
				startTime = System.currentTimeMillis();
			} 
			//else if(source==mode.getAdvance()){
				//TODO: the obstacle mode
			//}
		}
	}

	
	/**
	 * @author Pongthusiastics
	 * @date 13/11/2016
	 * @brief action listener for the tutorial page
	 * @details detects which button is pressed by the user and do the corresponding actions
	 */
	class TutorialListener implements ActionListener{

		/** 
		 * @brief opens the tutorial window with game instruction displayed.
		 * @param e is the action performed on the button
	     */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			/**
			 * Save the action performed into a variable
			 */
			Object source = e.getSource();

			/**
			 * - Detect for the button pressed
			 * - Do actions depending on the action performed
			 */
			if(source == tut.getBack()){		// If clicked the back button
				tut.setVisible(false);			// Go back to the welcome page
				w.setVisible(true);
			}
		}

	}

	/**
	 * @author Pongthusiastics
	 * @date 13/11/2016
	 * @brief action listener for the game page
	 * @details detects direction key pressed on keyboard, pass the changes to the view for display, and check for winning/losing.
	 */
	class GameListener implements ActionListener, KeyListener{

		/** 
		 * @brief Constructor for the action listener class
		 * @details Set up a timer to start the game
	     */
		GameListener(){
			t = new Timer(5,this);  
			t.setInitialDelay(1000);
		}

		/** 
		 * @brief update the ball, paddles, and player information
		 * @details update the ball positions, paddle positions, key pressed actions, and player score.
		 * @param e is the action performed on the keyboard
	     */
		@Override
		public void actionPerformed(ActionEvent e) {

			/**
			 * Update the velocity/direction of the Ball
			 * - x direction
			 * - y direction
			 */
			if(ballX< 0 || ballX > frameWidth-1.5*ballSize){
				/**
				 * - X-direction
				 * - If the ball is trying to go beyond the left/right border of the frame, 
				 * reverse the direction.
				 */
				velX = -velX;			
			}
			if(ballY < 0){
				/**
				 * - Y-direction
				 * 
				 * If the ball is trying to go up above the frame, 
				 * - reverse the direction
				 * - user gets points because the ball hits the border of the computer side
				 * - check game over or not
				 */
				velY = -velY;
				--scoreTop;
				checkGameOver();

				/**
				 * Update model and view
				 */
				gameDisplay.setTopScore(scoreTop);
				player.decrementLife();

			} else if(ballY+2.5*ballSize>frameHeight){
				/**
				 * If the ball is trying to go down beyond the frame
				 * - reverse the direction
				 * - the computer gets points
				 * - check game over or not
				 */
				velY = -velY;
				--scoreBottom;
				checkGameOver();

				/**
				 * Update model and view
				 */
				gameDisplay.setBottomScore(scoreBottom);
				ai.decrementLife();

			} else if(ballY+2.5*ballSize>frameHeight-inset-2*padHeight && velY > 0 && ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padWidth){
				/**
				 * If the ball is touching the bottom paddle
				 * - reverse the direction
				 */
				velY = -velY;
			} else if(ballY<=inset+2*padHeight && velY < 0 && ballX + ballSize >= topPadX && ballX <= topPadX + padWidth){
				/**
				 * If the ball is touching the top paddle
				 * - reverse the direction
				 */
				velY = -velY;
			}
			
			/**
			 * Update the ball position by velocity 
			 */
			ballX += velX;
			ballY += velY;

			/**
			 * Update the view and model
			 */
			gameDisplay.setBall(ballX,ballY);
			b.setPositionX(ballX);
			b.setPositionY(ballY);

			/**
			 * Detect the key pressed by the user on the keyboard
			 */
			if (keys.size() == 1) {
				if (keys.contains("LEFT")) {						
					/**
					 * If the user presses LEFT
					 * - update the position of the user paddle
					 * - display the change on the screen
					 */
					if(bottomPadX>0) {
						//TODO: SPEED
						bottomPadX-=3;

						/**
						 * Update the view and model
						 */
						gameDisplay.setBottom(bottomPadX);
						paddle_player.setPositionX(bottomPadX);
					}
				}
				else if (keys.contains("RIGHT")) {	
					if(bottomPadX < frameWidth - padWidth){
						/**
						 * If the user presses RIGHT
						 * - update the position of the user paddle
						 * - display the change on the screen
						 */
						//TODO: SPEED
						bottomPadX+=3;

						/**
						 * Update the view and model
						 */
						gameDisplay.setBottom(bottomPadX);
						paddle_player.setPositionX(bottomPadX);
					} 
				}
			}

			/**
			 * Create actions for the AI paddles
			 */
			double delta = ballX - topPadX;
			if (delta > 0) {								// If the AI paddle is trying to reach the right wall
				if(topPadX < frameWidth - padWidth){
					/**
					 * - Move the paddle to the right
					 * - Display the movement on the screen
					 */
					topPadX +=1;

					/**
					 * Update the view and the model
					 */
					gameDisplay.setTop(topPadX);
					paddle_ai.setPositionX(topPadX);
				}
			}
			else if (delta < 0) {							// If the AI paddle is trying to reach the left wall
				if(topPadX>0){
					/**
					 * - Move the paddle to the left
					 * - Display the movement on the screen
					 */
					topPadX -=1;

					/**
					 * Update the view and the model
					 */
					gameDisplay.setTop(topPadX);
					paddle_ai.setPositionX(topPadX);
				}
			}

			/**
			 * Send message to the view to update view.
			 */
			gameDisplay.repaint();
		}

		/** 
		 * @brief detect which key is pressed on the keyboard
		 * @param e is the action performed on keyboard
	     */
		@Override
		public void keyPressed(KeyEvent e) {

			/**
			 * Declare a variable to store the mouse click event
			 */
			int code = e.getKeyCode();				
			
			/**
			 * - Detect which key is pressed and perform corresponding actions
			 * - Save the action into a hashString
			 */
			switch (code) {					// LEFT is pressed
			case KeyEvent.VK_LEFT:
				keys.add("LEFT");
				break;

			case KeyEvent.VK_RIGHT:			// RIGHT is pressed
				keys.add("RIGHT");
				break;
			}
		}

		/** 
		 * @brief detects which key is released
		 * @param e is the action performed on keyboard
	     */
		@Override
		public void keyReleased(KeyEvent e) {

			/**
			 * Declare a variable to store the mouse click event
			 */
			int code = e.getKeyCode();				
			
			/**
			 * - Detect which key is pressed and perform corresponding actions
			 * - Delete the action from the hashString
			 */
			switch (code) {					//  LEFT is pressed
			case KeyEvent.VK_LEFT:
				keys.remove("LEFT");
				break;
			case KeyEvent.VK_RIGHT:			// RIGHT is pressed
				keys.remove("RIGHT");
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}

	}

	/** 
	 * @brief sets the display
	 * @details opens a window
     */
	public void display(){
		v.display();
	}

	/** 
	 * @brief checks whether the game ends
	 * @details check the number of life for both the player and the ai is 0.
     */
	private void checkGameOver(){
		
		/**
		 * - If the number of life for the ai is 0, the player wins
		 * - If the number of life for the player is 0, the ai wins.
		 * - Calculate the time a player has played, if breaks the record, save the record.
		 */
		if(scoreBottom==0){
			getElapsedTime();
			v.gameOver(0);
			resetGame();
			
			
		} else if(scoreTop==0){			
			getElapsedTime();
			v.gameOver(1);
			resetGame();
			
			
		}
		//TODO: SAVE RECORD

	}
	
	private void getElapsedTime(){
		endTime = System.currentTimeMillis();
		timeElapsed = (endTime-startTime)/1000.0;
		
		System.out.println(timeElapsed);
	}
	
	private void resetGame(){
		scoreTop = 0;
		scoreBottom = 0;
		
		
		
		
	}
	
	

	

}
