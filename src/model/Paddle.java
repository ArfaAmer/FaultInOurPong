package model;

public class Paddle {

	private int positionX;
	private int positionY;
	
	private final int HEIGHT = 10;
	private final int WIDTH = 40;
	private final int INSET = 10;
	
	//TODO 
	private int speed;
	
	public Paddle(int x, int y){
		positionX = x;
		positionY = y;
	}
	
	public void setPositionX(int x){
		positionX = x;
	}
	
	public void setPositionY(int y){
		positionY = y;
	}

	public int getPositionX(){
		return positionX;
	}
	
	public int getPositionY(){
		return positionY;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getSize(){
		return HEIGHT;
	}
	
	public int getInset(){
		return INSET;
	}
}
