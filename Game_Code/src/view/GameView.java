package view;

import javax.swing.*;

public class GameView{
	
	private Welcome welcome;
	private Mode mode;
	private PongGameDisplay ponggame;
	private Tutorial tutorial;
	
	private final int FRAMEWIDTH = 700;
	private final int FRAMEHEIGHT = 500;
	
	private JFrame gameFrame;
	
	
	public GameView(){
		/*
		 * Pass in different windows to this view interface
		 * - will wait for further invocation
		 */
		welcome = new Welcome();
		mode = new Mode();
		ponggame = new PongGameDisplay();
		
		createGame();
	}
	
	public void display(){
		welcome.setVisible(true);
	}
	
	
	public Welcome getWelcome(){
		return welcome;
	}
	
	public Mode getmode(){
		return mode;
	}
	
	public PongGameDisplay getGame(){
		return ponggame;
	}
	
	public Tutorial getTutorial(){
		return tutorial;
	}
	
	
	//TODO: ADD PANEL FOR OPTIONS IN THE GAME	
	public void createGame(){
		gameFrame = new JFrame("FaultInOurPong");
		gameFrame.setContentPane(ponggame);	
		gameFrame.setSize(FRAMEWIDTH,FRAMEHEIGHT);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JFrame getGameFrame(){
		return gameFrame;
	}
	
	// TODO: display a dialogue after successfully saving game records (high score)
	
	public void noFileAvailMessage(){
		JFrame errorFrame = new JFrame("Error");
		JOptionPane.showMessageDialog(errorFrame, "No record available!");
	}
	
	public void cannotLoadMessage(){
		JFrame errorFrame = new JFrame("Error");
		JOptionPane.showMessageDialog(errorFrame, "The record is either damaged or not available, please start a new game!");
	}
	
	public void tutorialPage(ImageIcon img){
		tutorial = new Tutorial(img);
	}
	
	public int getFrameWidth(){
		return FRAMEWIDTH;
	}
	
	public int getFrameHeight(){
		return FRAMEHEIGHT;
	}
}