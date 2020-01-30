import java.util.Scanner;

/**
 *
 * @author Marcus Fong
 *
 */

public class RPSLKDriver {
	/**
	 * This is the runner class of the game rock, paper, scissors, lizard, spock. Other than creating
	 * an instance of the two players of the game (the Human and the Computer), this class also introduces
	 * the user to the game – explaining the rules and such. In addition, the class also asks the user if 
	 * they would like to run either the real game (real human vs computer) or a simulation of it 
	 * (simulated human vs Computer – for testing purposes). 
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Hi! Please enter which game mode you would like to play:"
				+ "\n1) Human vs the computer"
				+ "\n2) Simulated human vs the computer");
		
		Scanner input = new Scanner(System.in);
		
		new WelcomeClass(input.nextInt());


		

			
			
		}
		
		
		
		

}
	



