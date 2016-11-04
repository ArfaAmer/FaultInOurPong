package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongGameDisplay extends JPanel{

	//private JFrame gameFrame;
	private int frameWidth;
	private int frameHeight;
	
	private int scoreTop, scoreBottom;
	
	private int ballX, ballY;
	private int bottomPadX, bottomPadY;
	private int topPadX, topPadY;
	private boolean first;
	private int ballSize;
	private int padW, padH;
	private int inset;
	

	
	public PongGameDisplay(){
		first = true;
		ballSize = 20;
		padW = 80;
		padH = 10;
		inset = 10;
		
		scoreTop=0;
		scoreBottom=0;
		
	
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	// create an object for graphics (rectangles)
		frameHeight = getHeight();				// get game frame/screen size
		frameWidth = getWidth();

		// initial positioning
		if (first) {
			bottomPadX = frameWidth / 2 - padW / 2;	// setups for the paddles positions - in the middle of the screen
			topPadX = bottomPadX;
			ballX = frameWidth / 2 - ballSize / 2;	// setups for the ball positions - in the middle of the screen
			ballY = frameHeight / 2 - ballSize / 2;
			first = false;						// setup completed
		}
		
		// bottom pad
		Rectangle2D bottomPad = new Rectangle(bottomPadX, frameHeight - padH - inset, padW, padH); // creating the object for bottom paddle
		g2d.fill(bottomPad);
		
		// top pad
		Rectangle2D topPad = new Rectangle(topPadX, inset, padW, padH); // creating paddle object for the top paddle
		g2d.fill(topPad);
		
		// ball
		Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, 20, 20);  // creating the ball object for the game
		g2d.fill(ball);
		
		// scores
		String scoreB = "Bottom: " + scoreBottom; 	// saving the score of the bottom paddle
		String scoreT = "Top: " + scoreTop;			// saving the score of the top paddle 
		g2d.drawString(scoreB, 10, frameHeight / 2);						// printing the score of the bottom paddle in the screen
		g2d.drawString(scoreT, frameWidth - 50, frameHeight / 2);			// printing the score of the top paddle in the screen
	}
		
	public void setBall(int x, int y){
		ballX = x;
		ballY = y;
	}
	
	public void setBottom(int x){
		bottomPadX = x;
	}
	
	public void setTop(int x){
		topPadX = x;
	}
	
	public int getBottomX(){
		return bottomPadX;
	}
	
	public int getBottomY(){
		return bottomPadY;
	}
	
	public void setTopScore(int s){
		scoreTop=s;
	}
	
	public void setBottomScore(int s){
		scoreBottom = s;
	}
	
	public int getBallX(){
		return ballX;
	}
	
	public int getBallY(){
		return ballY;
	}
}
