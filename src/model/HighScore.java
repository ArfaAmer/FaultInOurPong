import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HighScore {

	ArrayList<String> arylist = new ArrayList<String>();

	static ArrayList<String> user = new ArrayList<String>();
	static ArrayList<String> score = new ArrayList<String>();
	static ArrayList<Integer> scoreInt = new ArrayList<Integer>();

	static int x;

	public static String[] readFrom() throws IOException {

		String fileName = "highScore.txt";

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
			String filename = "highScore.txt";
		
			
			FileWriter fw = new FileWriter(filename, true);
			PrintWriter pw = new PrintWriter(filename);
			pw.print(" ");
			
			for(int i =0; i < user.size();i++){
				fw.write(user.get(i)+ " " + scoreInt.get(i) + "\n");
			}
//			fw.write(a + "\n");
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
			scoreInt.add(Integer.parseInt(score.get(i)));

		}

//		for (int i = 0; i < score.size(); i++) {
//			System.out.println(scoreInt.get(i));
//
//		}
	}

	public int returnLowest() {
		this.x = scoreInt.get(scoreInt.size());
		return scoreInt.get(scoreInt.size());
	}

	public static boolean isHigh(int compare) {
		if (compare > x) {
			return true;
		} else {
			return false;
		}
	}

	public static void sortInt() {

		for (int i = 0; i < scoreInt.size(); i++) {
			for (int j = 0; j < scoreInt.size() - 1; j++) {

				if (scoreInt.get(j) < scoreInt.get(j + 1)) {

					int temp = 0;
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

		for (int i = 0; i < scoreInt.size(); i++) {
			System.out.println(user.get(i) + " " + scoreInt.get(i));
		}
	}

	public HighScore(String nameUser, int nameScore) throws IOException {

		readFrom();
		creatingArrays();
		sortInt();
		
		String name = nameUser;
		int nameScored = nameScore;
		if(isHigh(nameScored)){
		
			user.add(name);
			scoreInt.add(nameScored);
			sortInt();
			user.remove(user.size()-1);
			scoreInt.remove(scoreInt.size()-1);
			writeTo();

		}
		
		
		
		
		
	}

}
