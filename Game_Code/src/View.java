
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
	
	public View(){
		super("FaultInOurPong");
		this.setSize(700,500);
		this.setResizable(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
		
		load.setMaximumSize(start.getPreferredSize());
		highScores.setMaximumSize(start.getPreferredSize());
		tutorial.setMaximumSize(start.getPreferredSize());
		exit.setMaximumSize(start.getPreferredSize());
		
		start.setAlignmentY(CENTER_ALIGNMENT);
		start.setAlignmentX(CENTER_ALIGNMENT);
		load.setAlignmentY(CENTER_ALIGNMENT);
		load.setAlignmentX(CENTER_ALIGNMENT);
		highScores.setAlignmentY(CENTER_ALIGNMENT);
		highScores.setAlignmentX(CENTER_ALIGNMENT);
		tutorial.setAlignmentY(CENTER_ALIGNMENT);
		tutorial.setAlignmentX(CENTER_ALIGNMENT);
		exit.setAlignmentY(CENTER_ALIGNMENT);
		exit.setAlignmentX(CENTER_ALIGNMENT);
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(start);
		buttonPanel.add(Box.createVerticalStrut(20));
		buttonPanel.add(load);
		buttonPanel.add(Box.createVerticalStrut(20));
		buttonPanel.add(highScores);
		buttonPanel.add(Box.createVerticalStrut(20));
		buttonPanel.add(tutorial);
		buttonPanel.add(Box.createVerticalStrut(20));
		buttonPanel.add(exit);
		buttonPanel.add(Box.createVerticalGlue());
		
		add(buttonPanel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void start() {
		//getContentPane().removeAll();
		//repaint();
		Pong_viewAndController view_controller = new Pong_viewAndController(this);
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		View menuPage = new View();
	}

}
