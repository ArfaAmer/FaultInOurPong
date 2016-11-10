package startGame;
import model.*;
import view.*;

public class PongGame {

	public PongGame(){
		
		
	}	
	public static void main(String[] args){
	
		/*
		 * Initialize the model, view, and controller for the game
		 */
		GameView view = new GameView();
		GameModel model = new GameModel();
		GameController controller = new GameController(view, model);
		
		/*
		 * Invoke the game display from the controller
		 */
		controller.display();

	}
}
