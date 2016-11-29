package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;

import startGame.GameController;
import view.GameView;

public class HighScore {

	static String[][] userData = new String[20][2];

	static double x;

	public HighScore() throws IOException {
		readFrom();
	}
	
	public static void readFrom() throws IOException {

		String fileName = "./Resources/highScore.txt";

		File file = new File(fileName);

		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			
			int i=0;
			while (line != null) {
				userData[i] = line.split("/");
				line = br.readLine();
				i++;
			}
			reader.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void writeTo() {
		try {
			String filename = "./Resources/highScore.txt";

			PrintWriter writer = new PrintWriter(filename);
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < 20; i++) {
				writer.println(userData[i][0] + "/" + userData[i][1]);
			}
			writer.close();
			fw.close();
			bw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}

	public static boolean isHigh(double nameScored) {
		if (nameScored > Double.valueOf(userData[19][1])) {
			return true;
		} else {
			return false;
		}
	}

	public static int findRank() throws IOException {
		
		String[] temp;
		Boolean x = false;
		int j=0;
		
		for (int i = 0; i < 20; i++) {
			if (x && Double.parseDouble(userData[19][1]) > Double.parseDouble(userData[i][1])) {
				temp = userData[i];
				userData[i] = userData[19];
				userData[19] = temp;
			}
			else if(Double.parseDouble(userData[19][1]) > Double.parseDouble(userData[i][1]) && !x) {
				temp = userData[i];
				userData[i] = userData[19];
				userData[19] = temp;
				j = i;
				x = true;
			}
			
		}
		return j;
	}
	
	public static void highScorePage(JFrame main) throws IOException {

		String[] columnNames = { "Rank #", "User Name", "Time" };
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JButton back = new JButton("Back");
		frame.add(back, BorderLayout.PAGE_START);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				main.setVisible(true);
				frame.setVisible(false);	
				}
			});
		
		String[][] data = new String[20][3];
		for (int i=0; i<20; i++) {
			data[i][0] = i+1 + ".";
			data[i][1] = userData[i][0];
			data[i][2] = userData[i][1];
		}
		
		JTable table = new JTable(data, columnNames);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		

		JScrollPane scrollPane = new JScrollPane(table);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(20);
		tcm.getColumn(1).setPreferredWidth(130);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void checkHighScore(double nameScore, JFrame main) throws IOException {
		
		double nameScored = nameScore;

			JPanel panel = new JPanel();

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			userData[19][1] = Double.toString(nameScored);
			userData[19][0] = "";
		    int i = findRank();
		    String title = "Congratulations! You made rank " + i+1;

		    String[] options = {"OK"};
		    JLabel lbl = new JLabel("Enter name");
		    JTextField username = new JTextField(15);
		    panel.add(lbl);
		    panel.add(username);		    
		    
		    int selOpt = JOptionPane.showOptionDialog(frame, panel, title, 
		    		JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		    
		    
		    if(selOpt == 0) {
		    	userData[i][0] = username.getText();
		    }
		    
		    else {
		    	userData[i][0] = "No name";
		    }

			writeTo();
			highScorePage(main);

	}
}
