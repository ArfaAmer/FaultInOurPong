package startGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.Timer;

import model.*;
import view.*;

public class GameController{
	
	private GameView v;
	private GameModel m;
	private Welcome w;
	private Mode mode;
	
	private HashSet<String> keys = new HashSet<String>();

	private JFrame gameFrame;
	private int frameWidth, frameHeight;
	private PongGameDisplay gameDisplay;
	
	private int velX=1, velY=1;
	private int padWidth = 80, padHeight = 10;
	private int bottomPadX, topPadX;
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
		
		//game = this.v.getGame();
		//game.addListener(new GameListener());
		
		gameFrame = this.v.getGameFrame();
		gameDisplay = this.v.getGame();
		//gameDisplay.addListener(new GameListener());
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
				
			} 
//			else if()
			
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
	
	class GameListener implements ActionListener, KeyListener{

		GameListener(){
			bottomPadX = gameDisplay.getBottom();
			frameWidth = v.getFrameWidth();
			frameHeight = v.getFrameHeight();
			
			t = new Timer(5,this);  
			t.setInitialDelay(1000);			// sets initial delay for the movement of the ball
			t.start();

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// X-direction
			if(ballX< 0 || ballX > frameWidth-ballSize){
				velX = -velX;
			}
			
			// Y-direction
			if(ballY < 0){
System.out.println("touch top border");
				velY = -velY;
				++scoreBottom;
			} else if(ballY+ballSize>frameHeight){
System.out.println("touch bottom border");
				velY = -velY;
				++scoreTop;
			} else if(ballY+ballSize>frameHeight-inset-padHeight && velY > 0 && ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padWidth){
System.out.println("touch bottom pad");
				velY = -velY;
			} else if(ballY+ballSize<=ballSize+inset+padHeight && velY < 0 && ballX + ballSize >= topPadX && ballX <= topPadX + padWidth){
System.out.println("touch top pad");
				velY = -velY;
			}
			
/*			
			// side walls
			if (ballX < 0 || ballX > frameWidth - ballSize) {	// making sure ball is horizontally within the frame (left&right)
				velX = -velX;								// reverse the ball position if trying to go out
			}
			// top / down walls
			if (ballY < 0) {								// similarly, for vertical position
				velY = -velY;								// reverse the ball position vertically 
				++ scoreBottom;
				System.out.println("bottom: "+scoreBottom);
				gameDisplay.setBottomScore(scoreBottom);
			}
			
			if (ballY + ballSize > frameHeight - ballSize) {	// similarly, for the vertical position of bottom paddle
				velY = -velY;
				++ scoreTop;				// points are incremented if the paddle is missed by opponent
System.out.println("top: "+scoreTop);
				gameDisplay.setTopScore(scoreTop);
			}
			// bottom pad
			if (ballY + ballSize>= frameHeight - padHeight - inset && velY > 0)	// similar to the previous parts
				if (ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padWidth)
					velY = -velY;

			// top pad
			if (ballY <= padHeight + inset && velY < 0)
				if (ballX + ballSize >= topPadX && ballX <= topPadX + padWidth)
					velY = -velY;
*/
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
	
	
	
	
}
