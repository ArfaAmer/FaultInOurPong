package view;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.*;
import view.*;

public class Mode extends JFrame{

	private JButton single = new JButton("Single Player Mode");
	private JButton sObstacle = new JButton("Advanced Single Player Mode");
	
	private JPanel buttonPanel;
	
	public Mode(){
		super("FaultInOurPong");
		this.setSize(700,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
		
		buttonPanel.add(Box.createVerticalGlue());
		addButton(single);
		addButton(sObstacle);
		buttonPanel.add(Box.createVerticalGlue());


		add(buttonPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void addButton(JButton x) {
		x.setMaximumSize(sObstacle.getPreferredSize());
		x.setAlignmentY(CENTER_ALIGNMENT);
		x.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(x);
		buttonPanel.add(Box.createVerticalStrut(20));
	}
	
	public void addListener(ActionListener buttonListener){
		single.addActionListener(buttonListener);
		sObstacle.addActionListener(buttonListener);
	}
	
	public JButton getSingle(){
		return single;
	}
	
}
