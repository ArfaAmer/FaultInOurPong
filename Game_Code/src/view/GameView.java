package view;

import javax.swing.*;

/**
 * @file GameView.java
 * @title GameView
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class is the main view model
 * @details This class import all different windows for display.
 */
public class GameView{
	
	/**
	 * Variable declarations to store different pages
	 * - welcome page
	 * - game mode page
	 * - tutorial page
	 * - game page
	 */
	private Welcome welcome;
	private Mode mode;
	private PongGameDisplay ponggame;
	private Tutorial tutorial;
	private JFrame gameFrame;
	
	/**
	 * Constant declarations for the view
	 */
	private final int FRAMEWIDTH = 700;
	private final int FRAMEHEIGHT = 500;
	
	/**
	 * @brief Constructor for the view
	 * @details declares all other windows
	 */
	public GameView(){
		/**
		 * - Pass in different windows to this view interface
		 * - Wait for further invocation
		 */
		welcome = new Welcome();
		mode = new Mode();
		ponggame = new PongGameDisplay();
		
		createGame();
	}
	
	/**
	 * @brief displays the welcome page.
	 * @details sets the visibility of the window to be true.
	 */
	public void display(){
		welcome.setVisible(true);
	}
	
	/**
	 * @brief gets welcome page window
	 * @return welcome page object
	 */
	public Welcome getWelcome(){
		return welcome;
	}
	
	/**
	 * @brief gets game mode page window
	 * @return game mode page object
	 */
	public Mode getmode(){
		return mode;
	}
	
	/**
	 * @brief gets game window
	 * @return game window object
	 */
	public PongGameDisplay getGame(){
		return ponggame;
	}
	
	/**
	 * @brief gets tutorial page window
	 * @return tutorial page object
	 */
	public Tutorial getTutorial(){
		return tutorial;
	}
	
	
	//TODO: ADD PANEL FOR OPTIONS IN THE GAME	
	/**
	 * @brief create the game for display
	 * @details create a frame under set dimension for the game
	 */
	public void createGame(){
		gameFrame = new JFrame("FaultInOurPong");
		gameFrame.setContentPane(ponggame);	
		gameFrame.setSize(FRAMEWIDTH,FRAMEHEIGHT);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * @brief gets game object
	 * @return game object
	 */
	public JFrame getGameFrame(){
		return gameFrame;
	}
	
	// TODO: display a dialogue after successfully saving game records (high score)
	/**
	 * @brief display message for error loading game record
	 * @details create a frame for display
	 */
	public void noFileAvailMessage(){
		JFrame errorFrame = new JFrame("Error");
		JOptionPane.showMessageDialog(errorFrame, "No record available!");
	}
	
	/**
	 * @brief display message for error loading game 
	 * @details create a frame for display
	 */
	public void cannotLoadMessage(){
		JFrame errorFrame = new JFrame("Error");
		JOptionPane.showMessageDialog(errorFrame, "The record is either damaged or not available, please start a new game!");
	}
	
	/**
	 * @brief display message for game over
	 * @param whichplayer is the indicator for the player
	 */
	public void gameOver(int whichplayer){
		
		/**
		 * - If the computer wins, display winning message for the computer
		 * - If the player wins, display winning message for the player
		 */
		if(whichplayer==0){
			JFrame overFrame = new JFrame("Game Over");
			JOptionPane.showMessageDialog(overFrame, "The game is over! The computer wins!");
		}
		else{
			JFrame overFrame = new JFrame("Game Over");
			JOptionPane.showMessageDialog(overFrame, "The game is over! The player wins!");
		}
		gameFrame.setVisible(false);
		welcome.setVisible(true);
		
	}
	
	/**
	 * @brief create tutorial page
	 * @param img is the image for display
	 */
	public void tutorialPage(ImageIcon img){
		tutorial = new Tutorial(img);
	}
	
	/**
	 * @brief gets width of the window
	 * @return FRAMEWIDTH
	 */
	public int getFrameWidth(){
		return FRAMEWIDTH;
	}
	
	/**
	 * @brief gets height of the window
	 * @return FRAMEHEIGHT
	 */
	public int getFrameHeight(){
		return FRAMEHEIGHT;
	}
}