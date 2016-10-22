package redevelopedCode;

import java.io.*;

public class Pong_controller {

	public Pong_model model;
	public static Pong_view viewer;
	
	public Pong_controller(){
		model = new Pong_model();
		
	}
	
	public static void main(String[] args){
		Pong_controller controller = new Pong_controller();
		viewer = new Pong_view(controller);
	}
	
	
	public void loadGame(){
		
		try{
		FileReader fr = new FileReader("savedScore.txt");
		BufferedReader br = new BufferedReader(fr);
		
		int score1 = Integer.parseInt(br.readLine());
		int score2 = Integer.parseInt(br.readLine());
		
		
		
		// TODO
		
		br.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void saveGame(){
		
		try{
			FileWriter fw = new FileWriter("savedScore.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(model.player1.getScore());
			bw.write(model.player2.getScore());
			
			// TODO
			
			bw.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		
	}
	
	public int gameOver(){
		if(model.player1.getScore()==0){
			return 1;
		}
		else if(model.player2.getScore()==0){
			return 2;
		}
		else{
			return -1;
		}
	}
	
}
