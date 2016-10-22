package redevelopedCode;

public class Ball {
	
	private int positionX;
	private int positionY;
	
	public Ball(int x, int y){
		positionX = x;
		positionY = y;
	}
	
	public void setPosition(int x, int y){
		positionX = x;
		positionY = y;
	}

	public int[] getPosition(){
		int[] temp = {positionX,positionY};
		return temp;
	}
}
