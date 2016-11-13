package view;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.*;
import view.*;

/**
 * @file Mode.java
 * @title Mode
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class create the game mode window
 * @details This class create a frame and buttons for different game level
 */
public class Mode extends JFrame{

	/**
	 * Variable declarations for the buttons
	 * - easy single mode
	 * - single mode with obstacles
	 * - a panel that contains the buttons 
	 */
	private JButton single = new JButton("Single Player Mode");
	private JButton sObstacle = new JButton("Advanced Single Player Mode");
	private JPanel buttonPanel;
	
	
	/**
	 * @brief Constructor for the player
	 * @details sets the size and header for the window, and adds buttons to the window
	 */
	public Mode(){
		/**
		 * Setups for the frame
		 */
		super("FaultInOurPong");
		this.setSize(700,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		 
		/**
		 * Setups for the buttons on the panel
		 */
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
		
		buttonPanel.add(Box.createVerticalGlue());
		addButton(single);
		addButton(sObstacle);
		buttonPanel.add(Box.createVerticalGlue());


		/**
		 * Add the panel to the frame/window for display
		 */
		add(buttonPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * @brief adds buttons to a panel
	 * @details makes buttons align in the panel 
	 */
	public void addButton(JButton x) {
		x.setMaximumSize(sObstacle.getPreferredSize());
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
		single.addActionListener(buttonListener);
		sObstacle.addActionListener(buttonListener);
	}
	
	/**
	 * @brief gets the button for single mode
	 * @return single
	 */
	public JButton getSingle(){
		return single;
	}
	
}
