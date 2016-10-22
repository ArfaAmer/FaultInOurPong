package redevelopedCode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

import javax.swing.*;


public class Pong_view extends JFrame{

	private Pong_controller controller;
	private JButton start = new JButton("Start New Game");
	private JButton load = new JButton("Load Game");
	private JButton highScores = new JButton("High Scores");
	private JButton tutorial = new JButton("Tutorial");
	private JButton exit = new JButton("Exit");
	private JButton single = new JButton("Single Player Mode");
	private JButton sObstacle = new JButton("Advanced Single Player Mode");
	private JButton multi = new JButton("Multiplayer Mode");

	private JPanel buttonPanel = new JPanel();

	public Pong_view(Pong_controller c){
		super("FaultInOurPong");
		controller = c;
		initializeFrame();
	}

	private void initializeFrame(){

		this.setSize(700,500);
		this.setResizable(false);

		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));

		buttonPanel.add(Box.createVerticalGlue());
		addButton(start);
		addButton(load);
		addButton(highScores);
		addButton(tutorial);
		addButton(exit);
		buttonPanel.add(Box.createVerticalGlue());

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});

		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO:
				//load();

			}
		});

		highScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO:
				//highScores();
			}
		});

		tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO:
				//tutorial();
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(buttonPanel);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void addButton(JButton x) {
		x.setMaximumSize(sObstacle.getPreferredSize());
		x.setAlignmentY(CENTER_ALIGNMENT);
		x.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(x);
		buttonPanel.add(Box.createVerticalStrut(20));
	}

	public void start() {
		buttonPanel.removeAll();
		this.getContentPane().removeAll();
		this.repaint();

		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));

		buttonPanel.add(Box.createVerticalGlue());
		addButton(single);
		addButton(sObstacle);
		buttonPanel.add(Box.createVerticalGlue());

		add(buttonPanel);
		this.setVisible(true);

		single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO:
				single();
			}
		});

	}


	private int height, width; 				// the height and width of the screen 
	//private Timer t = new Timer(1, this);   // the 't' variable makes sure that there is a initial delay before the same starts off
	private boolean first=true; 					//game state (starting/playing)
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

	public void single(){
		buttonPanel.removeAll();
		this.remove(buttonPanel);

		JPanel game_panel = new JPanel(){
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
		};

		this.add(game_panel);
		this.revalidate();
		this.repaint();
		
		
	}



}
