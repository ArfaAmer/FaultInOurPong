package view;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @file Tutorial.java
 * @title Tutorial
 * @author Pongthusiastics
 * @date 13/11/2016
 * @brief This class create the tutorial window
 * @details This class display instruction for the game
 */
public class Tutorial extends JFrame{

	/**
	 * Variable declaration for the back button
	 */
	private JButton back;
	
	/**
	 * @brief Constructor for the tutorial page
	 * @param img is the image for display
	 */
	public Tutorial(ImageIcon img){
		/**
		 * Setups for the window 
		 */
		super("FaultInOurPong - Tutorial");
		this.setLayout(new GridBagLayout());
		this.setSize(700,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		/**
		 * Add the image to the window
		 */
		this.add(new JLabel(img));
		back = new JButton("Back");
		this.add(back);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * @brief gets the button to exit the page
	 * @return back is the button for going back to welcome page
	 */
	public JButton getBack(){
		return back;
	}
	
	/**
	 * @brief adds action listener to the button
	 * @param listener is the action listener
	 */
	public void addListener(ActionListener listener){
		back.addActionListener(listener);
	}
}
