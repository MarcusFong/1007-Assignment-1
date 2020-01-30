import java.util.Scanner;
/**
 * 
 * @author Marcus Fong
 *
 * This class welcomes the user into the game and start it. Depending on what he or she inputed earlier, 
 * the class then instantiates RPSLKGame to start the game. If the user inputed 1, the game will be 
 * human vs computer; if the user inputed 2, the game will be a simulated human vs the computer.  
 */


public class WelcomeClass {
	/**
	 * 
	 * @param inGameModeChoice represents which game mode the user chose. 1) for the actual human vs 
	 * computer game mode and 2) for the simulated human vs computer game mode.
	 * 
	 * Depending on what the user inputed, the game instantiates the game (RPSLKGame) and its 
	 * respective Player objects. If its human vs computer, the computer asks for the user's name 
	 * and starts the game as normal. If its sim human vs computer, the computer asks the user for 
	 * the amount of times he or she would like the sim to repeat each of its two strategies and the 
	 * number of rounds he or she wants the sim to test through. 
	 */
	public WelcomeClass(int inGameModeChoice) {
		int gameModeChoice = inGameModeChoice;
		Scanner input = new Scanner(System.in);
				
		GameHistory theHistoryOfMoves = new GameHistory();//data type that will record the history of moves from each Player
		Computer theComputer = new Computer(theHistoryOfMoves);
		
		switch (gameModeChoice) {
		
		case 1: //game mode for human vs computer
			
			System.out.println(WELCOME_MESSAGE);
			Scanner input2 = new Scanner(System.in);

			String humanName = input2.nextLine();

			System.out.println("\nHello, " + humanName + "! The game will now start."
							+ "\n---------------------------------------------------------------\n");
			
			Human theHuman = new Human(humanName, theHistoryOfMoves);
			
			
			new RPSLKGame(true, theHuman, theComputer, 1);
			
			break;
			
		case 2: //game mode for sim human vs computer
			
			System.out.println("How many times would you like the simulation to repeat each strategy?");
			int repeatStrategy = input.nextInt();
			
			System.out.println("How many times would you like this to run?");
			int numberOfRuns = input.nextInt();
			
			SimulatedHuman theSim = new SimulatedHuman(theHistoryOfMoves, repeatStrategy, numberOfRuns);
			
			new RPSLKGame(true, theSim, theComputer, 2);
			
			break;
			
	}
		
		
	}
		
	private final static String WELCOME_MESSAGE = "Welcome to the game rock, paper, scissosr, lizard, spock!"
				
												+ "\n\nIn this version of the game, you, the player, will be playing against "
												+ "\nthe computer, which will be using a random number generator and some"
												+ "\nartificial intelligence to dish out its moves."
												
												+ "\n\nWhen the console prompts you to enter your move, type 'r', for rock,"
												+ "\n'p', for paper, 's', for scissors, 'l', for lizard, and 'k', for spock."
												
												+ "\n\nOnce inputted, the computer will either one of those moves in "
												+ "\nresponse. Keep in mind the move the computer chooses won't always be off"
												+ "\na random basis!"
												
												+ "\n\nAfter each round, the game will continue to loop forever until you "
												+ "choose to stop by entering 'stop' into the console."
												
												+ "\n\nBut first, please enter your name: ";
	
	
}
