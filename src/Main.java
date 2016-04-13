import java.util.Scanner;

/**
 * 
 * @author ChrisParisi
 * 
 * The great game of pong. For this game there will
 * be a collection of balls that total an odd number.
 * The balls are created and stored in a linked list.
 * Each ball will be put into play individually then
 * awarded to the player who scores that ball on the 
 * opponent. Best of 'n' balls wins the game.
 * 
 * The main class will be used to create
 * a new game and call the Game class.
 * The main method will also print statements to 
 * the user regarding how the game will be
 * played and scored.
 **/

public class Main {

	static Scanner keyboard = new Scanner(System.in);
	public static int score;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("|||Game play instructions|||");
		System.out.println("================================== \n "
				+ "The object of the game is to deflect \n"
				+ "the ball past your opponent! You \n"
				+ "can move by using the UP and DOWN \n"
				+ "arrow keys on your keyboard. The player \n"
				+ "with the most points wins the game! \n\n"
				+ "To begin the game you must first \n"
				+ "enter an odd integer number of points \n"
				+ "to play to. For example: best of 3. \n"
				+ "After entering a number to play to \n"
				+ "you can begin the game by clicking the \n"
				+ "*Play* button. You can pause the game \n"
				+ "anytime by clicking the *Pause* button. \n"
				+ "After a point is scored, you can play \n"
				+ "the next point by clicking the \n"
				+ "*Next Point* button. \n"
				+ "Good luck and may the best player wein!! \n"
				+ "==================================");
		System.out.println();
		System.out.println("Play best of how many points?");
		int num = keyboard.nextInt();
		while(num%2 == 0 || num<1){
			System.out.println("Whoops, the number must be positive and odd! \n"
					+ "Try entering a new integer to play best of:");
			num = keyboard.nextInt();
		}
			
		score = num;
		
		Game main = new Game(score);
	}
	
	/**
	 * 
	 * Method to determine what the score to play
	 * to will be. This will be determined by
	 * the users integer input. This number will
	 * be passed to the Game constructor.
	 * 
	 * @return score
	 */
	public int setScore(){
		
		
		return 0;
		
	}

}
