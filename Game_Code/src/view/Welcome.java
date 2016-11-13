package view;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @file Welcome.java
 * @title Welcome
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class creates the display for welcome page
 * @details This class defines buttons for options in the welcome page.
 */
public class Welcome extends JFrame {

	/**
	 * Variable declarations for the page
	 * - start a new game
	 * - load the previous game
	 * - display high score
	 * - tutorial
	 * - exit the game
	 */
	private JButton start = new JButton("Start New Game");
	private JButton load = new JButton("Load Game");
	private JButton highScores = new JButton("High Scores");
	private JButton tutorial = new JButton("Tutorial");
	private JButton exit = new JButton("Exit");
	
	/**
	 * Define a panel for the arrangement of buttons
	 */
	private JPanel buttonPanel;
	
	/**
	 * @brief Constructor for welcome page
	 * @details sets the header and size of window, and add buttons to it.
	 */
	public Welcome(){
		
		/**
		 * - Set the header of the window
		 * - Set the size of the window
		 */
		super("FaultInOurPong");
		this.setSize(700,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		/**
		 * Add buttons on the window 
		 */
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
		
		buttonPanel.add(Box.createVerticalGlue());
		addButton(start);
		addButton(load);
		addButton(highScores);
		addButton(tutorial);
		addButton(exit);
		buttonPanel.add(Box.createVerticalGlue());


		add(buttonPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/**
	 * @brief gets the start button
	 * @return start indicates to start a new game
	 */
	public JButton getStart(){
		return start;
	}
	
	/**
	 * @brief gets the load button
	 * @return load indicates to load a new game
	 */
	public JButton load(){
		return load;
	}
	
	/**
	 * @brief gets the button to display high score
	 * @return highScores
	 */
	public JButton highScores(){
		return highScores;
	}
	
	/**
	 * @brief gets the button to display instructions
	 * @return tutorial
	 */
	public JButton tutorial(){
		return tutorial;
	}
	
	/**
	 * @brief gets the button to exit the program
	 * @return exit
	 */
	public JButton exit(){
		return exit;
	}
	
	/**
	 * @brief adds buttons to a panel
	 * @details makes buttons align in the panel 
	 */
	public void addButton(JButton x) {
		x.setMaximumSize(start.getPreferredSize());
		x.setAlignmentY(CENTER_ALIGNMENT);
		x.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(x);
		buttonPanel.add(Box.createVerticalStrut(20));
	}
	
	/**
	 * @brief adds action listener to the buttons
	 * @param buttonListener is the action listener
	 */
	public void addListener(ActionListener buttonListener){
		start.addActionListener(buttonListener);
		load.addActionListener(buttonListener);
		highScores.addActionListener(buttonListener);
		tutorial.addActionListener(buttonListener);
		exit.addActionListener(buttonListener);
	}
}
