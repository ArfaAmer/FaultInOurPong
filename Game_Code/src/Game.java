
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;


public class Game extends JPanel implements KeyListener, ActionListener {
	
	private int height, width; 				// the height and width of the screen 
	private Timer t = new Timer(1, this);   // the 't' variable makes sure that there is a initial delay before the same starts off
	private boolean first; 					//game state (starting/playing)
	
	private HashSet<String> keys = new HashSet<String>();
	
	// pad
	private final int SPEED = 1;			//the speed of the paddles
	private int padH = 10, padW = 40;		// paddle width/height
	private int bottomPadX, topPadX;		// these represent the top and the bottom paddles in the game
	private int inset = 10;					// this helps determine the distance between the paddle 
											// - and the top and bottom screen boundaries 
	
	// ball
	private double ballX, ballY, velX = 1, velY = 1, ballSize = 20;		//ball position, ball velocity, ball size
	
	// score
	private int scoreTop, scoreBottom;		// keep track of game record
	
	public Game() {
		addKeyListener(this); 			// it helps to read the commands given through the keyboard
		setFocusable(true);				// keylisterer knows that it needs to look for the movement through keys
		setFocusTraversalKeysEnabled(false);	// since the argument is set to false, it moves the focus away from tab and shift keys
		first = true;					// sets the game state to true to start playing
		t.setInitialDelay(100);			// sets initial delay for the movement of the ball
		t.start();						// set the delay for every movement of the ball
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	// create an object for graphics (rectangles)
		height = getHeight();				// get game frame/screen size
		width = getWidth();

		// initial positioning
		if (first) {
			bottomPadX = width / 2 - padW / 2;	// setups for the paddles positions - in the middle of the screen
			topPadX = bottomPadX;
			ballX = width / 2 - ballSize / 2;	// setups for the ball positions - in the middle of the screen
			ballY = height / 2 - ballSize / 2;
			first = false;						// setup completed
		}
		
		// bottom pad
		Rectangle2D bottomPad = new Rectangle(bottomPadX, height - padH - inset, padW, padH); // creating the object for bottom paddle
		g2d.fill(bottomPad);
		
		// top pad
		Rectangle2D topPad = new Rectangle(topPadX, inset, padW, padH); // creating paddle object for the top paddle
		g2d.fill(topPad);
		
		// ball
		Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, ballSize, ballSize);  // creating the ball object for the game
		g2d.fill(ball);
		
		// scores
		String scoreB = "Bottom: " + new Integer(scoreBottom).toString(); 	// saving the score of the bottom paddle
		String scoreT = "Top: " + new Integer(scoreTop).toString();			// saving the score of the top paddle 
		g2d.drawString(scoreB, 10, height / 2);						// printing the score of the bottom paddle in the screen
		g2d.drawString(scoreT, width - 50, height / 2);			// printing the score of the top paddle in the screen
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// side walls
		if (ballX < 0 || ballX > width - ballSize) {	// making sure ball is horizontally within the frame (left&right)
			velX = -velX;								// reverse the ball position if trying to go out
		}
		// top / down walls
		if (ballY < 0) {								// similarly, for vertical position
			velY = -velY;								// reverse the ball position vertically 
			++ scoreBottom;
		}
		
		if (ballY + ballSize > height) {	// similarly, for the vertical position of bottom paddle
			velY = -velY;
			++ scoreTop;				// points are incremented if the paddle is missed by opponent
		}
		// bottom pad
		if (ballY + ballSize >= height - padH - inset && velY > 0)	// similar to the previous parts
			if (ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padW)
				velY = -velY;

		// top pad
		if (ballY <= padH + inset && velY < 0)
			if (ballX + ballSize >= topPadX && ballX <= topPadX + padW)
				velY = -velY;

		ballX += velX;
		ballY += velY;
		
		// pressed keys
		if (keys.size() == 1) {
			if (keys.contains("LEFT")) {							// left key is pressed
				bottomPadX -= (bottomPadX > 0) ? SPEED : 0;			// move the bottom paddle to the left
			}
			else if (keys.contains("RIGHT")) {						// right key is pressed
				bottomPadX += (bottomPadX < width - padW) ? SPEED : 0;
			}
		}
		
		// AI
		double delta = ballX - topPadX;
		if (delta > 0) {											// move right if ball is to the right of the paddle
			topPadX += (topPadX < width - padW) ? SPEED : 0;
		}
		else if (delta < 0) {										// move left if ball is to the left of the paddle
			topPadX -= (topPadX > 0) ? SPEED : 0;
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
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
}
