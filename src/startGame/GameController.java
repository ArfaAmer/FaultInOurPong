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
		
		
		
	}
	
	class WelcomepageListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			
			if(source==w.getStart()){
				mode.setVisible(true);
				w.setVisible(false);
				
			}else if(source==w.load()){
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
				//TODO
				
				w.setVisible(false);
				tut.setVisible(true);
				
			}else if(source==w.exit()){
				System.exit(0);
			}
			
		}
		
	}
	
	class ModeListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			
			if(source == mode.getSingle()){
				mode.setVisible(false);
				gameFrame.setVisible(true);
				
			} 
		}
	}
	
	class TutorialListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			
			if(source == tut.getBack()){
				tut.setVisible(false);
				w.setVisible(true);
			}
		}
		
	}
	
	class GameListener implements ActionListener, KeyListener{

		GameListener(){

			frameWidth = v.getFrameWidth();
			frameHeight = v.getFrameHeight();
			
			ballX = gameDisplay.getBallX();
			ballY = gameDisplay.getBallY();
			
			t = new Timer(5,this);  
			t.setInitialDelay(1000);			// sets initial delay for the movement of the ball
			t.start();

			bottomPadX = gameDisplay.getBottomX();
			bottomPadY = gameDisplay.getBottomY();
//TODO: MODEL FOR THE PADDLE
System.out.println("x: "+bottomPadX);
System.out.println("y: "+bottomPadY);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// X-direction
			if(ballX< 0 || ballX > frameWidth-1.5*ballSize){
				velX = -velX;
			}
			
			// Y-direction
			if(ballY < 0){
				velY = -velY;
				++scoreBottom;
				gameDisplay.setBottomScore(scoreBottom);
			} else if(ballY+2.5*ballSize>frameHeight){
				velY = -velY;
				++scoreTop;
				gameDisplay.setTopScore(scoreTop);
			} else if(ballY+2.5*ballSize>frameHeight-inset-2*padHeight && velY > 0 && ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padWidth){
				velY = -velY;
			} else if(ballY<=inset+2*padHeight && velY < 0 && ballX + ballSize >= topPadX && ballX <= topPadX + padWidth){
				velY = -velY;
			}
			
			ballX += velX;
			ballY += velY;
			
			gameDisplay.setBall(ballX,ballY);
			
			// pressed keys
			if (keys.size() == 1) {
				if (keys.contains("LEFT")) {							// left key is pressed
					if(bottomPadX>0) {
						//TODO: SPEED
						bottomPadX-=3;
						gameDisplay.setBottom(bottomPadX);
					}
				}
				else if (keys.contains("RIGHT")) {						// right key is pressed
					if(bottomPadX < frameWidth - padWidth){
						//TODO: SPEED
						bottomPadX+=3;
						gameDisplay.setBottom(bottomPadX);
					} 
				}
			}
			
			// AI
			double delta = ballX - topPadX;
			if (delta > 0) {		
				
				if(topPadX < frameWidth - padWidth){
					topPadX +=1;
					gameDisplay.setTop(topPadX);
				}
			}
			else if (delta < 0) {			
				
				if(topPadX>0){
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
			//gameDisplay.repaint();
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
