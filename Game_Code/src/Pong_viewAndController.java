

import java.awt.*;
import javax.swing.*;

public class Pong_viewAndController extends JFrame{
	private JButton start = new JButton("Start");
	private JButton suspend = new JButton("Suspend");
	private JButton tutorial = new JButton("Tutorial");
	private JButton save = new JButton("Save");
	private JButton exit = new JButton("Exit");

	public Pong_viewAndController(){
		super("FaultInOurPong");
		this.setSize(700,500);
		this.setResizable(false);
		
		JPanel game_interface = new JPanel();
		game_interface.setLayout(new BoxLayout(game_interface, BoxLayout.X_AXIS));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
		
		start.setMaximumSize(suspend.getPreferredSize());
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(start);
		buttonPanel.add(Box.createVerticalStrut(10));
		suspend.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(suspend);
		buttonPanel.add(Box.createVerticalStrut(10));
		tutorial.setMaximumSize(suspend.getPreferredSize());
		tutorial.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(tutorial);
		buttonPanel.add(Box.createVerticalStrut(10));
		save.setMaximumSize(suspend.getPreferredSize());
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(save);
		buttonPanel.add(Box.createVerticalStrut(10));
		exit.setMaximumSize(suspend.getPreferredSize());
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(exit);
		
		JPanel west = new JPanel(new GridBagLayout());
        west.add(buttonPanel);
		
		Game game = new Game();
		
		//game_interface.add(buttonPanel);
		game_interface.add(game);
		add(west,BorderLayout.WEST);
		add(game_interface);
		setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		Pong_viewAndController view_controller = new Pong_viewAndController();
	}
	
}
