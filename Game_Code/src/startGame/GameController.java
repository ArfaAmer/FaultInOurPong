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

public class GameController{
	
	private GameView v;
	private GameModel m;
	private Welcome w;
	private Mode mode;
	private Tutorial tut;
	
	private HashSet<String> keys = new HashSet<String>();

	private JFrame gameFrame;
	private int frameWidth, frameHeight;
	private PongGameDisplay gameDisplay;
	
	private int velX=1, velY=1;
	private int padWidth = 80, padHeight = 10;
	private int bottomPadX, bottomPadY, topPadX, topPadY;
	private int ballX, ballY, ballSize=20;
	private int scoreTop, scoreBottom;
	private int inset;

	private Timer t;
	
	public GameController(GameView v, GameModel m){
		this.v = v;
		this.m = m;
		
		/*
		 * Setups for the View
		 */
		w = this.v.getWelcome();
		w.addListener(new WelcomepageListener());
		
		mode = this.v.getmode();
		mode.addListener(new ModeListener());
		
		ImageIcon image = new ImageIcon("./Resources/tutorial.png");
		v.tutorialPage(image);
		tut = v.getTutorial();
		tut.addListener(new TutorialListener());
		
		gameFrame = this.v.getGameFrame();
		gameDisplay = this.v.getGame();
		gameDisplay.addKeyListener(new GameListener());
		gameDisplay.setFocusable(true);
		gameDisplay.setFocusTraversalKeysEnabled(false);
		
		/*
		 * Setups for the Model
		 */
		frameWidth = v.getFrameWidth();
		frameHeight = v.getFrameHeight();
		
		
		
		
	}
	
	/*
	 * Actionlistener for the welcome page
	 */
	class WelcomepageListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			
			// If clicked the start button
			if(source==w.getStart()){
				mode.setVisible(true);
				w.setVisible(false);
				
			}else if(source==w.load()){
			// If clicked the load button
				//TODO
				try{
					FileReader fr = new FileReader("./Resources/userData.txt");
					BufferedReader br = new BufferedReader(fr);
					
					System.out.println("can load data");
					
					br.close();
				}catch(Exception exp){
					v.cannotLoadMessage();
				}
			}else if(source==w.highScores()){
			// If clicked the high score button
				//TODO
				try{
					FileReader fr = new FileReader("./Resources/gameScore.txt");
					BufferedReader br = new BufferedReader(fr);
					
					System.out.println("can display high score");
					
					br.close();
				}catch(Exception exp){
					v.noFileAvailMessage();
				}
			}else if(source==w.tutorial()){
			// If clicked the tutorial button
				//TODO
				
				w.setVisible(false);
				tut.setVisible(true);
				
			}else if(source==w.exit()){
			// If clicked the exit button
				System.exit(0);
			}
			
		}
		
	}
	
	/*
	 * Actionlistener for the Single-Mode page
	 */
	class ModeListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			
			if(source == mode.getSingle()){
			// If clicked the basic single mode button
				mode.setVisible(false);
				gameFrame.setVisible(true);
				t.start();
				
			} 
		}
	}
	
	/*
	 * Actionlistener for the tutorial page
	 */
	class TutorialListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			
			if(source == tut.getBack()){
			// If clicked the back button
				tut.setVisible(false);
				w.setVisible(true);
			}
		}
		
	}
	
	class GameListener implements ActionListener, KeyListener{

		GameListener(){
			
			
			ballX = gameDisplay.getBallX();
			ballY = gameDisplay.getBallY();
			
			t = new Timer(5,this);  
			t.setInitialDelay(1000);			// sets initial delay for the movement of the ball

			bottomPadX = gameDisplay.getBottomX();
			bottomPadY = gameDisplay.getBottomY();

			//TODO: MODEL FOR THE PADDLE

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// X-direction
			if(ballX< 0 || ballX > frameWidth-1.5*ballSize){
				/*
				 * If the ball is trying to go beyond the left/right border of the frame, 
				 * reverse the direction.
				 */
				velX = -velX;			
			}
			
			// Y-direction
			if(ballY < 0){
				/*
				 * If the ball is trying to go up above the frame, 
				 * - reverse the direction
				 * - user gets points because the ball hits the border of the computer side
				 */
				velY = -velY;
				++scoreBottom;
				gameDisplay.setBottomScore(scoreBottom);
			} else if(ballY+2.5*ballSize>frameHeight){
				/*
				 * If the ball is trying to go down beyond the frame
				 * - reverse the direction
				 * - the computer gets points
				 */
				velY = -velY;
				++scoreTop;
				gameDisplay.setTopScore(scoreTop);
			} else if(ballY+2.5*ballSize>frameHeight-inset-2*padHeight && velY > 0 && ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padWidth){
				/*
				 * If the ball is touching the bottom paddle
				 * - reverse the direction
				 */
				velY = -velY;
			} else if(ballY<=inset+2*padHeight && velY < 0 && ballX + ballSize >= topPadX && ballX <= topPadX + padWidth){
				/*
				 * If the ball is touching the top paddle
				 * - reverse the direction
				 */
				velY = -velY;
			}
			
			/*
			 * Update the ball position by velocity 
			 */
			ballX += velX;
			ballY += velY;
			
			gameDisplay.setBall(ballX,ballY);
			
			/*
			 * Detect the key pressed by the user on the keyboard
			 */
			if (keys.size() == 1) {
				if (keys.contains("LEFT")) {						
					/*
					 * If the user presses LEFT
					 * - update the position of the user paddle
					 * - display the change on the screen
					 */
					if(bottomPadX>0) {
						//TODO: SPEED
						bottomPadX-=3;
						gameDisplay.setBottom(bottomPadX);
					}
				}
				else if (keys.contains("RIGHT")) {	
					if(bottomPadX < frameWidth - padWidth){
						/*
						 * If the user presses RIGHT
						 * - update the position of the user paddle
						 * - display the change on the screen
						 */
						//TODO: SPEED
						bottomPadX+=3;
						gameDisplay.setBottom(bottomPadX);
					} 
				}
			}
			
			/*
			 * Create actions for the AI paddles
			 */
			double delta = ballX - topPadX;
			if (delta > 0) {		
				
				if(topPadX < frameWidth - padWidth){
					/*
					 * If the AI paddle is trying to reach the right wall
					 * - move the paddle to the right
					 * - display the movement on the screen
					 */
					topPadX +=1;
					gameDisplay.setTop(topPadX);
				}
			}
			else if (delta < 0) {			
				
				if(topPadX>0){
					/*
					 * If the AI paddle is trying to reach the left wall
					 * - move the paddle to the left
					 * - display the movement on the screen
					 */
					topPadX -=1;
					gameDisplay.setTop(topPadX);
				}
			}
		
			gameDisplay.repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {

			// TODO Auto-generated method stub
			int code = e.getKeyCode();				// get which key is pressed
			switch (code) {
			case KeyEvent.VK_LEFT:
				keys.add("LEFT");
				break;
				
			case KeyEvent.VK_RIGHT:
				keys.add("RIGHT");
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

			// TODO Auto-generated method stub
			int code = e.getKeyCode();				// get which key is released
			switch (code) {
			case KeyEvent.VK_LEFT:
				keys.remove("LEFT");
				break;
			case KeyEvent.VK_RIGHT:
				keys.remove("RIGHT");
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
	}
	
	
	public void display(){
		v.display();
	}
	
}
