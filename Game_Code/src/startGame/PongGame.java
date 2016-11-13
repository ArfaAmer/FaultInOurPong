package startGame;
import model.*;
import view.*;

/**
@mainpage The main page for the game FaultInOurPong.
*/
/**
* @file PongGame.java
* @title PongGame
* @author Pongthusiastics
* @date 13/11/2016
* @brief This class starts the game. 
* @details This class instantiates a model, view, and controller using the MVC model, and starts the game. 	
* @code
*		GameView view = new GameView();
*		GameModel model = new GameModel();
*		GameController controller = new GameController(view, model);
*		controller.display();
* @endcode		
*/
public class PongGame {


	/**
	* @brief This is the main function for starting the program
	* @author Pongthusiastics
	* @param args is the input for the main function
	* @date 13/11/2016
	*/
	public static void main(String[] args){
	
		/**
		 * 
		 * Initialize the model, view, and controller for the game
		 */
		GameView view = new GameView();
		GameModel model = new GameModel();
		GameController controller = new GameController(view, model);
		
		/**
		 * Invoke the game display from the controller
		 */
		controller.display();

	}
}
