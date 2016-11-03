package model;

public class GameModel {

	private Ball b1;
	private Paddle p_player, p_computer;
	private Player player, computer;
	
	public GameModel(int ballX, int ballY, int playerX, int playerY, int compX, int compY){
		b1 = new Ball(ballX, ballY);
		p_player = new Paddle(playerX, playerY);
		p_computer = new Paddle(compX, compY);
		
		//TODO
		player = new Player();
		computer = new Player();
	}
	
	public Ball getBall(){
		return b1;
	}
	
	public Paddle getPlayerPaddle(){
		return p_player;
	}
	
	public Paddle getComputerPaddle(){
		return p_computer;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Player getComputer(){
		return computer;
	}
}
