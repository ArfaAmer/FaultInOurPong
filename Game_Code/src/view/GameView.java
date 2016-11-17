package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

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
	private final int FRAMEWIDTH = 700; //700
	private final int FRAMEHEIGHT = 500; //500
	
	/**
	 * Set up buttons on the game panel
	 */
	private JButton pause;
	private JButton resume;
	private JButton save;
	private JButton exit;
	private JPanel gameOptions;
	

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
	
	/**
	 * @brief create the game for display
	 * @details create a frame under set dimension for the game
	 */
	public void createGame(){
		gameFrame = new JFrame("FaultInOurPong");
		gameFrame.setLayout(new BorderLayout());


		gameOptions = new JPanel();
		gameOptions.setLayout(new BoxLayout(gameOptions, BoxLayout.Y_AXIS));
		pause = new JButton("Pause Game");
		resume = new JButton("Resume Game");
		save = new JButton("Save Game");
		exit = new JButton("Exit to Main Page");

		gameOptions.add(Box.createVerticalGlue());
		addButton(gameOptions, pause, exit);
		addButton(gameOptions, resume, exit);
		addButton(gameOptions, save, exit);
		addButton(gameOptions, exit, exit);
		gameOptions.add(Box.createVerticalGlue());

		gameOptions.setFocusable(false);
		gameFrame.add(gameOptions, BorderLayout.LINE_START);
		
		ponggame.setBorder(BorderFactory.createLineBorder(Color.black));
		ponggame.setFocusable(true);
		gameFrame.add(ponggame, BorderLayout.CENTER);
		gameFrame.setSize(FRAMEWIDTH,FRAMEHEIGHT);
		gameFrame.setResizable(true);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JButton getPause(){
		return pause;
	}
	
	public JButton getResume(){
		return resume;
	}
	
	public JButton getSave(){
		return save;
	}
	
	public JButton getExit(){
		return exit;
	}
	
/*	
	public void addListener(ActionListener listener){
		pause.addActionListener(listener);
		resume.addActionListener(listener);
		save.addActionListener(listener);
		exit.addActionListener(listener);
	}
*/	
	public JPanel getGameOptionPanel(){
		return gameOptions;
	}
	

	public void addButton(JPanel panel, JButton button, JButton prefer) {
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentY(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(prefer.getPreferredSize());
System.out.println(prefer.getPreferredSize());
		panel.add(button);
		panel.add(Box.createVerticalStrut(20));
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
	public void gameOver(int whichplayer, double time){

		/**
		 * - If the computer wins, display winning message for the computer
		 * - If the player wins, display winning message for the player
		 */
		if(whichplayer==0){
			JFrame overFrame = new JFrame("Game Over");
			JOptionPane.showMessageDialog(overFrame, "The game is over! The computer wins!\n"
					+ "     Your time = " + time);
		}
		else{
			JFrame overFrame = new JFrame("Game Over");
			JOptionPane.showMessageDialog(overFrame, "The game is over! The player wins!\n"
					+ "     Your time = " + time);
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