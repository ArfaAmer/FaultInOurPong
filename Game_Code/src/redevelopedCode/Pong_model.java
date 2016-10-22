package redevelopedCode;

public class Pong_model{
	

	
	private int score1;
	private int score2;
	public Players player1;
	public Players player2;
	
	public Pong_model(){
		
		player1 = new Players();
		player2 = new Players();
		score1 = player1.getScore();
		score2 = player2.getScore();
		
	}
	
	public void changeSpeed(int newSpeed){
		
	}

	public String readTutorial(){
		String content = "";
		
		return content;
	}

	public boolean createObstacle(boolean gameMode){
		if(gameMode==true){ return true;}
		else{ return false; }
	}


	
	
}