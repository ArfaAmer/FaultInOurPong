package view;

import java.awt.event.ActionListener;

import javax.swing.*;

public class Welcome extends JFrame {

	private JButton start = new JButton("Start New Game");
	private JButton load = new JButton("Load Game");
	private JButton highScores = new JButton("High Scores");
	private JButton tutorial = new JButton("Tutorial");
	private JButton exit = new JButton("Exit");
	
	private JPanel buttonPanel;
	
	public Welcome(){
		
		super("FaultInOurPong");
		this.setSize(700,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
		
		buttonPanel.add(Box.createVerticalGlue());
		addButton(start);
		addButton(load);
		addButton(highScores);
		addButton(tutorial);
		addButton(exit);
		buttonPanel.add(Box.createVerticalGlue());


		add(buttonPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public JButton getStart(){
		return start;
	}
	
	public void addButton(JButton x) {
		x.setMaximumSize(start.getPreferredSize());
		x.setAlignmentY(CENTER_ALIGNMENT);
		x.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(x);
		buttonPanel.add(Box.createVerticalStrut(20));
	}
	
	public void addListener(ActionListener buttonListener){
		start.addActionListener(buttonListener);
		load.addActionListener(buttonListener);
		highScores.addActionListener(buttonListener);
		tutorial.addActionListener(buttonListener);
		exit.addActionListener(buttonListener);
	}
}
