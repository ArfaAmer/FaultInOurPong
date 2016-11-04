package view;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tutorial extends JFrame{

	private JButton back;
	
	public Tutorial(ImageIcon img){
		super("FaultInOurPong - Tutorial");
		this.setSize(700,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.add(new JLabel(img));
		
		back = new JButton("Back");
		//this.add(back);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public JButton getBack(){
		return back;
	}
	
	public void addListener(ActionListener listener){
		back.addActionListener(listener);
	}
}
