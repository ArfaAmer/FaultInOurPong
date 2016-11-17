package model;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HighScore {

	ArrayList<String> arylist = new ArrayList<String>();

	static ArrayList<String> user = new ArrayList<String>();
	static ArrayList<String> score = new ArrayList<String>();
	static ArrayList<Double> scoreInt = new ArrayList<Double>();

	static double x;

	public static String[] readFrom() throws IOException {

		String fileName = "./Resources/highScore.txt";

		String content = null;
		File file = new File(fileName);

		FileReader reader = null;

		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		String[] words = content.split("\\s+");

		// for (int i = 0; i < words.length; i++) {
		// System.out.println(words[i]);
		// }

		return words;

	}

	public static void writeTo() {
		try {
			String filename = "./Resources/highScore.txt";

			FileWriter fw = new FileWriter(filename, true);
			PrintWriter pw = new PrintWriter(filename);
			pw.print(" ");

			for (int i = 0; i < user.size(); i++) {
				fw.write(user.get(i) + " " + scoreInt.get(i) + "\n");

			}
			// fw.write(a + "\n");
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}

	public static void creatingArrays() throws IOException {
		String[] newArr = readFrom();
		for (int i = 0; i < newArr.length; i++) {
			if (i % 2 == 0) {
				user.add(newArr[i]);
			}

			if (i % 2 == 1) {
				score.add(newArr[i]);
			}
		}

		// for(int i = 0; i < user.size(); i++){
		// System.out.println(user.get(i));
		//
		// }
		//
		// for(int i = 0; i < score.size(); i++){
		// System.out.println(score.get(i));
		//
		// }

		for (int i = 0; i < score.size(); i++) {
			scoreInt.add(Double.valueOf(score.get(i)));

		}

		// for (int i = 0; i < score.size(); i++) {
		// System.out.println(scoreInt.get(i));
		//
		// }
	}

	public Double returnLowest() {
		this.x = scoreInt.get(scoreInt.size());
		return scoreInt.get(scoreInt.size());
	}

	public static boolean isHigh(double nameScored) {
		if (nameScored > x) {
			return true;
		} else {
			return false;
		}
	}

	public static void sortInt() {

		for (int i = 0; i < scoreInt.size(); i++) {
			for (int j = 0; j < scoreInt.size() - 1; j++) {

				if (scoreInt.get(j) < scoreInt.get(j + 1)) {

					Double temp = 0.;
					String tempS = "";
					temp = scoreInt.get(j);
					tempS = user.get(j);

					scoreInt.set(j, scoreInt.get(j + 1));
					user.set(j, user.get(j + 1));
					scoreInt.set(j + 1, temp);
					user.set(j + 1, tempS);

				}
			}
		}
		// random text
	}
	
	public static void highScorePage(JFrame main) throws IOException {
		//readFrom();
		creatingArrays();
		sortInt();
		//writeTo();
		highScoreFrame(main);
	}

	public static void highScoreFrame(JFrame main) {

		String[] columnNames = { "User Name", "Time" };
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
		
		
		Object rowData[][] = {
				{ user.get(0),scoreInt.get(0)},
				{ user.get(1),scoreInt.get(1)},
				{ user.get(2),scoreInt.get(2)},
				{ user.get(3),scoreInt.get(3)},
				{ user.get(4),scoreInt.get(4)},
				{ user.get(5),scoreInt.get(5)},
				{ user.get(6),scoreInt.get(6)},
				{ user.get(7),scoreInt.get(7)},
				{ user.get(8),scoreInt.get(8)},
				{ user.get(9),scoreInt.get(9)},
				{ user.get(10),scoreInt.get(10)},
				{ user.get(11),scoreInt.get(11)},
				{ user.get(12),scoreInt.get(12)},
				{ user.get(13),scoreInt.get(13)},
				{ user.get(14),scoreInt.get(14)},
				{ user.get(15),scoreInt.get(15)},
				{ user.get(16),scoreInt.get(16)},
				{ user.get(17),scoreInt.get(17)},
				{ user.get(18),scoreInt.get(18)},
				{ user.get(19),scoreInt.get(19)}};
		JTable table = new JTable(rowData, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		user = new ArrayList<String>();
		score = new ArrayList<String>();
		scoreInt = new ArrayList<Double>();

	}

	public HighScore(double nameScore, JFrame main) throws IOException {

		readFrom();
		creatingArrays();
		sortInt();

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

			user.add(username.getText());
			scoreInt.add(nameScored);
			sortInt();
			user.remove(user.size() - 1);
			scoreInt.remove(scoreInt.size() - 1);
			writeTo();
			highScoreFrame(main);

		}

	}
}
