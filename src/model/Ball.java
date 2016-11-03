package model;

public class Ball {

	private int positionX;
	private int positionY;
	private final int SIZE = 20;
	
	//TODO
	private int speed;
	
	public Ball(int x, int y){
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
	
	public int getSize(){
		return SIZE;
	}
	
}
