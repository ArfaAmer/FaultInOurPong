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

	public static void sortInt() throws IOException {
		
		String[] temp;
		for (int i = 0; i < 20; i++) {
			if(Double.parseDouble(userData[19][1]) > Double.parseDouble(userData[i][1])) {
				temp = userData[i];
				userData[i] = userData[19];
				userData[19] = temp;
			}
		}
		for (int i=0; i<20; i++) {
			System.out.println(userData[i][0] + "  " + userData[i][1]);
		}
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
		tcm.getColumn(0).setPreferredWidth(10);
		tcm.getColumn(1).setPreferredWidth(130);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void checkHighScore(double nameScore, JFrame main) throws IOException {
		
		double nameScored = nameScore;
		if (isHigh(nameScored)) {

			JPanel panel = new JPanel(new BorderLayout(5, 5));

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		    label.add(new JLabel("User Name", SwingConstants.RIGHT));
		    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		    JTextField username = new JTextField();
		    controls.add(username);
		    panel.add(controls, BorderLayout.CENTER);

		    JOptionPane.showMessageDialog(frame, panel, "Highscores: Add name", JOptionPane.QUESTION_MESSAGE);

			userData[19][0] = username.getText();
			userData[19][1] = Double.toString(nameScored);
			
			sortInt();
			writeTo();
			highScorePage(main);

		}

	}
	
	public static void main(String[] args) throws HeadlessException, IOException{
		
		HighScore hs = new HighScore();
		hs.highScorePage(new JFrame());
		hs.checkHighScore(32., new JFrame());

	}
}
