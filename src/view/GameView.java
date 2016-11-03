package view;

import javax.swing.JFrame;

public class GameView{
	
	private Welcome welcome;
	private Mode mode;
	private PongGameDisplay ponggame;
	private Tutorial tutorial;
	
	private JFrame gameFrame;
	
	
	public GameView(){
		
		welcome = new Welcome();
		mode = new Mode();
		ponggame = new PongGameDisplay();
		tutorial = new Tutorial();
		
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
	
	public void createGame(){
		gameFrame = new JFrame("FaultInOurPong");
		gameFrame.setContentPane(ponggame);	
		gameFrame.setSize(700,500);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JFrame getGameFrame(){
		return gameFrame;
	}
	
	public int getFrameWidth(){
		return gameFrame.getWidth();
	}
	
	public int getFrameHeight(){
		return gameFrame.getHeight();
	}
	
	
}