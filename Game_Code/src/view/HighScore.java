package view;

import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.table.TableColumnModel;


/**
 * @file HighScore.java
 * @title HighScore
 * @author Pongthusiastics
 * @date 7/12/2016
 * @brief This class displays score data.
 * @details This class reads, writes, and displays score.
 */
public class HighScore {

	/**
	 * Variable declaration
	 */
	static String[][] userData = new String[20][2];

	/**
	 * @brief Constructor for the HighScore
	 * @details calls the read method to read data
	 * @throws IOException
	 */
	public HighScore() throws IOException {
		readFrom();
	}


	/**
	 * @brief reads score data.
	 * @details reads from file "highScore.txt" and store them into an array.
	 */
	public static void readFrom() throws IOException {

		/**
		 * Declare a variable to store the file.
		 */
		String fileName = "./Resources/highScore.txt";
		File file = new File(fileName);

		/**
		 * - Read the file using BufferedReader
		 * - Organize and put user data into the array
		 */
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

	/**
	 * @brief writes the score onto the file
	 * @details opens the file "highScore.txt" and updates scores
	 */
	public static void writeTo() {
		try {
			/**
			 * Declare a variable to store the file name
			 */
			String filename = "./Resources/highScore.txt";

			/**
			 * Use FileWriter to write scores 
			 */
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

	/**
	 * @brief checks whether the score can be in top 20 list
	 * @return a boolean that indicate the score can go into the score list
	 */
	public static boolean isHigh(double nameScored) {
		if (nameScored > Double.valueOf(userData[19][1])) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @brief determines the rank of a score
	 * @return a rank of the score
	 */
	public static int findRank() throws IOException {

		/**
		 * Variable declaration
		 * - the current score list
		 * - an indicator for the check action
		 * - a counter for looping through the list
		 */
		String[] temp;
		Boolean x = false;
		int j=0;

		/**
		 * For each score/record, if the current score is greater than the previous one,
		 * set this score to have a higher rank and return the rank.
		 */
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


	/**
	 * @brief displays the high score
	 * @details creates a frame for the display after reading the score file
	 */
	public static void highScorePage(JFrame main) throws IOException {

		/**
		 * Create a JFrame for the display
		 */
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

		/**
		 * Read all the score data and put them into an array
		 */
		String[][] data = new String[20][3];
		for (int i=0; i<20; i++) {
			data[i][0] = i+1 + ".";
			data[i][1] = userData[i][0];
			data[i][2] = userData[i][1];
		}

		/**
		 * Put all the score data into a JTable for display
		 */
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

	/**
	 * @brief checks the high score and update the score file
	 * @details finds the rank for each score and lists them for display
	 */
	public void checkHighScore(double nameScore, JFrame main) throws IOException {

		/**
		 * Variable declaration
		 * - the score for each user
		 * - variables for the JPanel
		 */
		double nameScored = nameScore;
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Define ranks for each score/user
		 */
		userData[19][1] = Double.toString(nameScored);
		userData[19][0] = "";
		int i = findRank();
		String title = "Congratulations! You made rank " + i+1;

		/**
		 * - Prompt for the user to enter his/her username
		 * - Pop up a confirmation message after saving
		 */
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
		
		/**
		 * Display the score list
		 */
		highScorePage(main);

	}
}
