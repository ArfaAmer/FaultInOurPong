import java.awt.*;
import javax.swing.*;
public class Pong_viewAndController extends JFrame{
	private JButton suspend = new JButton("Suspend");
	private JButton resume = new JButton("Resume");
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
		
		suspend.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(suspend);
		buttonPanel.add(Box.createVerticalStrut(10));
		resume.setMaximumSize(suspend.getPreferredSize());
		resume.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(resume);
		buttonPanel.add(Box.createVerticalStrut(10));
		save.setMaximumSize(suspend.getPreferredSize());
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(save);
		buttonPanel.add(Box.createVerticalStrut(10));
		exit.setMaximumSize(suspend.getPreferredSize());
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(exit);
		
		Game game = new Game();
		
		game_interface.add(buttonPanel);
		game_interface.add(game);
		add(game_interface);
		setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*public static void main(String[] args){
		Pong_viewAndController view_controller = new Pong_viewAndController();
	}*/
	
}