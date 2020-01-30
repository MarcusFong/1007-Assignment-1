import java.util.Scanner;
/**
 * @author Marcus
 *`
 * This class is the main "hub" of the game. It continues to start new rounds of RPSLK until 
 * the user tells the game to stop or, if it is a simulated human, when the total number of rounds
 * match the amount of times the user wants the simulation to run. This class also continues to update
 * the state of the game (number of rounds elapsed, number of wins, etc.) for each round of the game. 
 * For each round of the game, the move thrown by each user is compared and determines if the round has 
 * a winner or if it is a draw. 
 *
 * I define 1 round of an RPSLK game by both Players playing their moves (rock, paper, scissors,
 * , or spock) and computing if the round ends with a winner or in a draw.
 * 
 * 
 * 
 * Step 5 Test Statistics: 
 * (Results shown are the mean winning distributions of each Player after 8 games of RPSLK where each game ran
 * 100 times. Each strategy repeats itself an x amount of times until swapping the to the other strategy.
 * The wins of each Player don't add up to 100 because there are some rounds where neither win.):
 * 
 * Repeat each SIM strategy 1 time:
 * 
 * SIM: 35.625 wins out of 100 rounds
 * Computer: 47.625 wins out of 100 rounds
 * 
 * Repeat each SIM strategy 3 times:
 * 
 * SIM: 30.375 wins out of 100 rounds
 * Computer: 51.125 wins out of 100 rounds
 * 
 * Repeat SIM strategy 5 times
 * 
 * SIM: 21.75 wins out of 100 rounds
 * Computer: 61.75 wins out of 100 rounds
 * 
 * Reflection:
 * 
 * It is clear that once the step 5 AI was implemented, the Computer was able to beat out the SIM much more 
 * easily. This is simply because the AI knew one of the potential strategies the SIM would be using:
 * the reflector strategy. If the AI noticed the SIM reflected the AI's last two moves, the AI would automatically 
 * predict its next move to be a reflection and would play its counter. As shown above, as we increased the amount 
 * of times a strategy was repeated (meaning the more times the reflect strategy was used for the SIM), the number of
 * wins for the Computer went up. This is because the computer was able to identify if the SIM was using the reflect 
 * strategy early and would continuously out smart the SIM multiple times until the strategy swapped back to rotator –
 * simply, the more times a strategy was repeated, the more times the AI was able to figure out when the SIM was using 
 * reflect and counter it. 
 * 
 *
 *
 *
 *
 * Step 4 Test Statistic:
 * (Results shown are the mean winning distributions of each Player after 8 games of RPSLK where each game ran
 * 100 times. Each strategy repeats itself an x amount of times until swapping the to the other strategy.
 * The wins of each Player don't add up to 100 because there are some rounds where neither win.):
 * 
 * Repeat each SIM strategy 1 time:
 * 
 * SIM: 38.75 wins out of 100 rounds
 * Computer: 36.25 wins out of 100 rounds
 * 
 * Repeat each SIM strategy 3 times:
 * 
 * SIM: 37.625 wins out of 100 rounds
 * Computer: 38.25 wins out of 100 rounds
 * 
 * Repeat SIM strategy 5 times
 * 
 * SIM: 36.875 wins out of 100 rounds
 * Computer: 37.625 wins out of 100 rounds
 * 
 * Reflection: 
 * 
 * No matter how many times the simulator repeats a strategy, their number of wins
 * is similar to the number of wins to the computer as both are practically using a random algorithm to 
 * throw their moves. Indeed, the SIM isn't completely random as it follows a strategy, but at this level 
 * of the program, the Computer does not have a built AI to notice the strategy the SIM is using. 
 * 
 * Although the Computer has a built in AI to attempt to predict the Human's move (or in this
 * case the SIM's move), this AI that was programmed in step 2 is basically useless in this scenario 
 * as a SIM human wouldn't show a tendency to one specific move. 
 *
 */

public class RPSLKGame {
	/**
	 * @param inGameRunning set to true when the game is running and false when it stops. 
	 * @param humanPlayer represents the Player of type Human. Could either be a real human or a simulated one.
	 * @param computerPlayer represents the Player of of type Computer.
	 * @param gameModeChoice allows to system to differentiate between the actual game (real human vs Computer) 
	 * and the simulated game (simulated human vs Computer). 
	 * 
	 * Depending on gameModeChoice, this constructor will run either the regular version of the game
	 * (1 for real Human vs Computer) or a simulated version of the game (2 for sim vs Computer). For 
	 * each round until isGameRunning is set to false, this program continues to prompt the Human for an
	 * input (if the Human is simulated then the inputs for it are automated) to play his or her move.
	 * The method didHumanWin() checks who won that round and increments their score by one. This continues
	 * to repeat until the user says stop or, if the game is simulated, when the number of elapsed rounds 
	 * equals the number of times the user specified the simulation to run.
	 * 
	 */
	public RPSLKGame(boolean inIsGameRunning, Human humanPlayer, Computer computerPlayer, int gameModeChoice) {
		isGameRunning = inIsGameRunning; //set to true when starting the game				
		
		theHuman =  humanPlayer; //represents the "Human" player. Could be instantiated as a real
								 //human or a simulated one depending on what the user chose.
		
		theComputer = computerPlayer;
		
		Scanner input = new Scanner(System.in);
		
		while (isGameRunning) {
			System.out.println(gameUpdateMessage());
			
			switch (gameModeChoice) { //This allows the computer differentiate which game mode is being played.
									  //case 1 is real human vs Computer and 2 is sim human vs Computer
			
			case 1: // if the game mode is set to human vs Computer
				
				String humanMoveInput = input.nextLine();
				
				if (analyzeHumanInput(humanMoveInput) == CONTINUE) { //if user inputs a valid String 
					
					if (gameModeChoice == 1) {
						theHuman.enterMove(humanMoveInput);
					}
					
					
					theComputer.enterMove();
					didHumanWin();
					
					numberOfRounds++;
				}
				
				else if (analyzeHumanInput(humanMoveInput) == STOP) {
					isGameRunning = false;
					//print end game message
				}
				else {
					System.out.println("\n\nInvalid input. Please try again.");
				}
				
				break;
			
			
			case 2: // if the game mode is set to simulated human vs Computer
				
				theHuman.enterMove();
				theComputer.enterMove();
				
				didHumanWin();
				
				if (theHuman.getTotalNumOfRounds() == numberOfRounds) //this is the last round, stop the program
					isGameRunning = false;

				numberOfRounds++;
				break;
				
			
			}
			
			
		}
		
	}
	
	
	
	/**

	 * @return returns 20 if the human wins, 21 if it loses, and 22 if there is a draw. the RPSLK round and false if it loses. 
	 * This method checks the moves each Player played (the Human and the Computer) and compares them to 
	 * see who won, lost, or tied the round. 
	 * 
	 * This method first identifies the Human's move and then compares it with the Computer's. Every move has two moves it 
	 * can beat and two it can lose two. This method compares the Human's move's strengths and weaknesses to what the Computer
	 * played to see who won. If the Computer's move matches one of the Human's move's strengths, the Human wins.
	 * If the Computer's move matches one of Human's move's weaknesses, the Human loses. 
	 * 
	 * E.G: The Human chooses Spock and the Computer chooses Paper. The Computer did not choose one of Spock's two 
	 * strengths, Rock or Scissors, so the program checks if the Computer chose either Paper or Lizard – it did! The 
	 * program thus returns LOSE (for the Human). 
	 */
	public int didHumanWin() {
		String theComputerMoveName = theComputer.getMove().getName();
		
		String theHumanMoveName = theHuman.getMove().getName();
		
		String[] theHumanMoveStrengthsNames = theHuman.getMove().getStrengths();
		String[] theHumanMoveWeaknessesNames = theHuman.getMove().getWeaknesses();
		
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("You chose " + theHumanMoveName + " and the Mr. Computer chose " + theComputerMoveName + ".");
		
		
		//IF THE HUMAN WINS
		//if one of the Human's move's strengths beats the Computer's move
		//It is an 'or' statement because each move has two moves it can beat. 
		if (theHumanMoveStrengthsNames[0].equals(theComputerMoveName)
				|| theHumanMoveStrengthsNames[1].equals(theComputerMoveName)) {
			
			theHuman.winRound();
			
			
			System.out.println("\n"+ theHumanMoveName + " beats " + theComputerMoveName + "!!"
							+ "\n\nA win for " +  theHuman.getName()
							+ "\n");
			
			return WIN;
			
		}
		
		//IF THE COMPUTER WINS
		//if one of the Human's move's weaknesses loses to the Computer's move
		//It is an 'or' statement because each move has two weaknesses
		else if (theHumanMoveWeaknessesNames[0].equals(theComputerMoveName) 
				|| theHumanMoveWeaknessesNames[1].equals(theComputerMoveName)) {
			
			theComputer.winRound();
			
			
			System.out.println("\n"+ theHumanMoveName + " loses to " + theComputerMoveName + "!!"
					+ "\n\nA win for " + theComputer.getName() 
					+ "\n");
			
			return LOSE;
		}
		
		//if the Human has the same move as the Computer (draw)
		else  {
			System.out.println("\nBoth players chose " + theHumanMoveName + "!"
							+ "\n\nDraw!"
							+ "\n");
			return DRAW;
		}
		
		
	}
	
	
	
	
	
	/**
	 *
	 * @param paramHumanMove this argument holds the String that is checked to see if the 
	 * inputed String from the Human player is valid or not. Inputting 'r', 's', or 'p' 
	 * would return true while anything else false.
	 * @return these values are integers because they help the compiler recognize the type of 
	 * input the user gave. If it is valid, the method returns CONTINUE, or 10. If it is invalid, 
	 * the method returns INVALID_INPUT, or 12
	 * 
	 * CONTINUE is equal to 10 and means the program can continue playing the game.
	 * STOP is equal to 11 and means the program will stop playing the game.
	 * INVALID_INPUT is equal 12 and means the user inputed an invalid input and must try again.
	 * 
	 * This method checks if the input from the human is an acceptable String. It can either be 
	 * a move ('r', 'p', 's', etc.) or 'stop' to stop the program. 
	 */
	public int analyzeHumanInput(String paramHumanMove) {

		if (paramHumanMove.equals("r")) { //this is to check...
			return CONTINUE;
		}
		else if (paramHumanMove.equals("p")) {
			return CONTINUE;
		}
		else if (paramHumanMove.equals("s")) {
			return CONTINUE;
		}
		else if (paramHumanMove.equals("l")) {
			return CONTINUE;
		}
		else if (paramHumanMove.equals("k")) {
			return CONTINUE;
		}
		else if (paramHumanMove.equals("stop")) {
			return STOP;
		}
		else {//if the input is invalid, rerun the while loop.
			
			return INVALID_INPUT;
		}
		
	}
	
	/**
	 * 
	 * @return this method returns a String containing the message that will be displayed to the user
	 * after every round of rock, paper, scissors. It will update the user with the score and the 
	 * number of rounds elapsed. 
	 */
	public String gameUpdateMessage() {
		
		return "The score is currently:"
				+ "\n\n" + theHuman.getName() + ": " + theHuman.getRoundsWon()
				+ "\n" + theComputer.getName() + ": " + theComputer.getRoundsWon()
				+ "\n\nAfter a total of " + numberOfRounds + " rounds. "
				+ "\n--------------------------------------------------------------------------------------\n"
				+ "\nPlease input 'r' for Rock, 'p' for Paper, 's' for Scissors, 'l' for Lizard', or 'k' for Spock.";
	
	}
	
	
	
	private final int CONTINUE = 10;
	private final int STOP = 11;
	private final int INVALID_INPUT = 12;
	
	private final int WIN = 20;
	private final int LOSE = 21;
	private final int DRAW = 22;
	
	
	private int numberOfRounds;
	private boolean isGameRunning;
	
	private Human theHuman;
	private Computer theComputer;
	
	
	
}
