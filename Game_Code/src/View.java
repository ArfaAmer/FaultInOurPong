
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame{

	private JButton start = new JButton("Start New Game");
	private JButton load = new JButton("Load Game");
	private JButton highScores = new JButton("High Scores");
	private JButton tutorial = new JButton("Tutorial");
	private JButton exit = new JButton("Exit");
	
	private JButton single = new JButton("Single Player Mode");
	private JButton sObstacle = new JButton("Advanced Single Player Mode");
	private JButton multi = new JButton("Multiplayer Mode");
	
	JPanel buttonPanel = new JPanel();
	
	public View(){
		super("FaultInOurPong");
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
				load();
			}
		});
		
		highScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highScores();
			}
		});
		
		tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tutorial();
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
				single();
			}
		});

	}
	
	public void single() {
		Pong_viewAndController view_controller = new Pong_viewAndController(this);
		this.setVisible(false);
	}
	
	public void load() {
		
	}
	
	public void highScores() {
		
	}
	
	public void tutorial() {
		
	}
	
	public static void main(String[] args) {
		View menuPage = new View();
	}

}
