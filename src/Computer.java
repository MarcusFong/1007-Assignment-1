import java.util.Random;
import java.util.Arrays;
/**
 * 
 * @author Marcus
 *
 * This class represents the Computer player in the game RPSLK. All of its move throws are automated
 * and are based off of 3 strategies: either the revenge strategy, the record strategy, or the random
 * strategy. Depending on the history of throws done by itself and the Human, the Computer attempts to 
 * guess which strategy is the best in predicting the counter to the Human's next move (see below for an 
 * explanation of each strategy). The number of wins the Computer has is stored in this class along with
 * its history of moves from the object recordedMoves.
 * 
 * Revenge strategy: This strategy KNOWS that the Human could potentially be reflecting the Computer's moves 
 * back onto us, meaning if the Computer plays Rock the previous round the Human will "reflect" that
 * and will play Rock the current round. Knowing this, if the Computer notices that the Human is "reflecting"
 * the Computer's moves, the Computer will automatically play its previous's move's counter to 
 * potentially win the round. 
 * 
 * Record strategy: This strategy looks at the number of times each move was played by the Human and checks
 * to see if the Human has a tendency to play a certain one. If the computer notices a tendency towards a
 * certain move, the Computer automatically plays its counter to potentially win the round. 
 * 
 * Random strategy: This strategy is the common strategy used by the Computer and will run only if the Revenge
 * and Record strategies fail to notice any pattern. This simply makes the Computer throw one of five possible moves
 * at an equally random rate (each move has a 20% chance of being played). 
 *  
 *  
 *  asdasdasdasd testetsetsetsetstetestet
 *
 */
public class Computer extends Player {

	/**
	 * 
	 * The GameHistory object is saved as recordedMoves to be referenced throughout the class to access the 
	 * history and count of moves done by each Player. 
	 * 
	 * @param inRecordedMoves stores the GameHistory object that allows the Computer
	 * to access the history of moves done by the Human and itself.
	 */
	public Computer(GameHistory inRecordedMoves) {

		
		recordedMoves = inRecordedMoves;
		
	}
	
	/**
	 * This method helps the computer choose a strategy that will ultimately lead it to choosing a move for that
	 * current round. 
	 * 
	 * If a human pattern is found (where he/she shows a tendency to favor one more over the other, see method 
	 * javadoc for details), use the recorder strategy.
	 * Else, use the regular random strategy where each move throw is evenly weighted: 1/5 chance for each throw.

	 */
	public void enterMove() {
		
		//System.out.println(recordedMoves.getComputerMoveHistory().size());
			
		
		if (recordedMoves.getRoundsElapsed() > 2 && foundReflectorStrat()) { 
			System.out.println("USING REVENGE");
			
			useRevengeStrategy();
			
		}
		
		
		else if (getHumanPreferedMove() >= 0 && getHumanPreferedMove() <=4) {
			System.out.println("USING RECORDER");
			
			useRecorderStrategy();
		}
		else {
			System.out.println("USING RANDOM");
			
			useRandomStrategy();
		}
		
		recordedMoves.addToComputerMoveHistory(currentMove); //add the Computer's chosen move to its move history
		recordedMoves.incrementComputerMoveCount(currentMove.getMoveIndex());


			
	}
		
	
	
		
	/**
	 * This method plays one of the moves (Rock, Paper, Scissor, Lizard, or Spock) at an equally 
	 * random basis. Each move has a 20% chance of being played. The random number created is a double
	 * from range 0 <= randomNum < 1. Each move has a range of 0.20 to be chosen. For example,
	 * Rock is 0 <= randomNum < 0.20
	 * Paper is 0.20 <= randomNum 0.40
	 * and so on.
	 */
	public void useRandomStrategy() {
		double randomNum = RANDOM_GENERATOR.nextDouble();
		if (randomNum >= 0 && randomNum < moveProbabilites[0]) {//if 0 <= randomNum < 0.20
			playRock();
		}
		else if (randomNum >= moveProbabilites[0] && randomNum < moveProbabilites[1]) {//if 0.20 <= randomNum < 0.40
			playPaper();
	
		}
		else if (randomNum >= moveProbabilites[1] && randomNum < moveProbabilites[2]) {//if 0.40 <= randomNum < 0.60
			playScissor();
	
		}
		
		else if (randomNum >= moveProbabilites[2] && randomNum < moveProbabilites[3]) {//if 0.60 <= randomNum < 0.80
			playLizard();
	
		}
			
		else if (randomNum >= moveProbabilites[3] && randomNum < moveProbabilites[4]) {//if 0 <= randomNum < 1.00
			playSpock();
				
		}
		
	}
	
	
	
	/**
	 * This method is used to check if the using the recorder strategy would be wise for the computer.
	 * If the Human does shows a tendency to prefer a certain move, this method will notice this return the move. 
	 * The Computer defines a Human's "tendency" to a certain move if the difference between the 
	 * count of the most common move and the count of any other move is greater than two. I chose two 
	 * because I felt like anything bigger would be too uncommon for a human to repeat a move that many times 
	 * and anything less would be too small. 
	 * 
	 *  E.G if the Human has played Rock 8 times, Paper 5 times, and Scissor 4 times, the method will return
	 *  0 (for Rock) because 8-5>2 and 8-4>2. 
	 * 
	 * @return 0 for Rock, 1 for Paper, 2 for Scissor. 
	 */
	public int getHumanPreferedMove() {
		
		int[] humanMoveCounts = recordedMoves.getHumanMoveCounts();
		
		int maxCount = 0;
		int commonestMoveIndex = 0;
		
		int secondMaxCount = 0;
		
		
		for (int i = 0; i < humanMoveCounts.length; i++) {// this finds the index of the most common move used by the Human. 
			if (maxCount <= humanMoveCounts[i]) {
				commonestMoveIndex = i;
				maxCount = humanMoveCounts[i];
				
			}
		}
		
		for (int i = 0; i < humanMoveCounts.length; i++) {// this finds the count of the second most common move used by the Human. 
			if (secondMaxCount <= humanMoveCounts[i] && i != commonestMoveIndex) {
				
				secondMaxCount = humanMoveCounts[i];
				
			}
		}
		
		/*
		System.out.println("rock count: " + humanMoveCounts[0]);
		System.out.println("paper count: " + humanMoveCounts[1]);
		System.out.println("scissor count: " + humanMoveCounts[2]);
		System.out.println("lizard count: " + humanMoveCounts[3]);
		System.out.println("spock count: " + humanMoveCounts[4]);
		*/
	
		
		if (maxCount - secondMaxCount > 2) {//if the biggest count is greater by more than 2, return its index
			return commonestMoveIndex;
		}
		
		return -1;//return -1 if the biggest count is not greater than the second biggest count by more than 2
		

		
	}
	

	/**
	 * This method chooses a move the Computer will play for this round depending
	 * on what getHumanPreferedMove() returns. getHumanPreferedMove() returns the move that the human is showing 
	 * a tendency to use more, so this method plays randomly selects and plays one of the two counter 
	 * moves to that move.  
	 * 
	 * E.G: If the human shows a tendency to play Rock, this function will randomly select either Paper
	 * or Spock to play for the next round. 
	 * 
	 */
	public void useRecorderStrategy() {
		
		boolean randomBoolean = RANDOM_GENERATOR.nextBoolean();
		
		switch(getHumanPreferedMove()) {
		
		case 0: //Found that Human tends to use Rock so randomly play either Paper or Spock
			if (randomBoolean)
				playPaper();
			else
				playSpock();
			break;
			
		case 1: //Found that Human tends to use Paper so randomly play either Scissor or Lizard
			if (randomBoolean)
				playScissor();
			else 
				playLizard();
			break;
			
		case 2: //Found that Human tends to use Scissor so randomly play either Rock or Spock
			if (randomBoolean)
				playRock();
			else
				playSpock();
			break;
			
		case 3: //Found that Human tends to play Lizard so randomly play either Rock or Scissors
			if (randomBoolean)
				playRock();
			else 
				playScissor();
			break;
			
		case 4: //Found that Human tends to play Spock so randomly play either Paper or Lizard
			if (randomBoolean)
				playPaper();
			else
				playLizard();
			break;
		}
		
	}
	

	
	/**
	 * 
	 * @return true if the computer believes the Human is using the reflector strategy and false if not.
	 * 
	 * This method is used to check whether or not using the revenge strategy would be wise.
	 * 
	 *  Using recordedMoves to access the history of moves done by both the Human and Human, this method
	 *  checks to see if the Human reflected the Computer's second and third last moves. If it did, then 
	 *  the program concludes that is likely that the Human is using a reflector strategy. 
	 */
	public boolean foundReflectorStrat() {
		
		
		int humanMoveHistorySize = recordedMoves.getHumanMoveHistory().size();
		int computerMoveHistorySize = recordedMoves.getComputerMoveHistory().size();
		
		
		/*
		 * The program subtracts the size by two to get the Human's last move and three for the Human's second last 
		 * move because the Human plays his or her move before the computer so its history of moves is one element larger.
		 * Retrieving the element at .size()-1 would return the move the Human is playing the current round.  
		 */
		MoveType humanLastMove = recordedMoves.getHumanMoveHistory().get(humanMoveHistorySize - 2); 
		MoveType humanSecondLastMove = recordedMoves.getHumanMoveHistory().get(humanMoveHistorySize - 3);
		
		MoveType computerSecondLastMove = recordedMoves.getComputerMoveHistory().get(computerMoveHistorySize - 2);
		MoveType computerThirdLastMove = recordedMoves.getComputerMoveHistory().get(computerMoveHistorySize - 3);
		
		
		
		/*
		 * If the human's last move equaled the computer's second last move and
		 * if the human's second last move equaled the computer's third last move,
		 * then the computer would have enough evidence to believe that the Human
		 * (who is most likely a sim) is using the reflector strategy. 
		 */
		if (humanLastMove.getClass().equals(computerSecondLastMove.getClass())  //if found using reflector strategy in human
				&& humanSecondLastMove.getClass().equals(computerThirdLastMove.getClass())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Assuming the Human is using the reflector strategy, this method is thus able to predict the
	 * Human's next move and plays its counter to win the round. 
	 * 
	 * If the Computer knows that the Human is using the reflector strategy, this method is called
	 * to counteract that. Assuming the Human will use the reflector strategy for this round (as it
	 * did for the last two rounds), this method gets the weaknesses of its previous move (which is 
	 * the move the Human is likely to play next) and plays either one of the weaknesses at an equally
	 * random basis to predict and counteract the Human's next move. Granted, this does not guarantee a
	 * win for the Computer as it is possible that for the current round, the Human could swap strategies, 
	 * despite them using the reflector strategy for the past two rounds. 
	 */
	public void useRevengeStrategy() {
		//System.out.println("USING REVENGE STRAT");

		int computerMoveHistorySize = recordedMoves.getComputerMoveHistory().size();
		MoveType computerLastMove = recordedMoves.getComputerMoveHistory().get(computerMoveHistorySize-1);
			
		String[] lastMoveWeaknesses = computerLastMove.getWeaknesses();
		
		boolean rand = RANDOM_GENERATOR.nextBoolean();
			
		if (rand) {
			//System.out.println(lastMoveWeaknesses[0]+ " weakness 0)";
			
			playStringMove(lastMoveWeaknesses[0]);		
		}
		else {
			//System.out.println(lastMoveWeaknesses[1] + " weakness 1");
			
			playStringMove(lastMoveWeaknesses[1]);
		}	
	}
	
	
	/**
	 * 
	 * @param input stores the String value of a move
	 * 
	 * Because the array of weaknesses of each move is stored as a String and currentMove is type MoveType, 
	 * this simple helper method allows useRevengeStrategy() to play the move the it thinks is best 
	 * to beat out the player.  
	 */
	public void playStringMove(String input) {
		switch(input) {
		
		case "ROCK":
			playRock();
			break;
			
		case "PAPER":
			playPaper();
			break;
			
		case "SCISSOR":
			playScissor();
			break;
			
		case "LIZARD":
			playLizard();
			break;
			
		case "SPOCK":
			playSpock();
			break;
			
		}

	}
	
	
	
	/**
	 * This method helps simplify the process in which the computers play a certain move. 
	 */
	public void playRock() {
		currentMove = new Rock();
	}
	/**
	 * This method helps simplify the process in which the computers play a certain move. 
	 */
	public void playPaper() {
		currentMove = new Paper();
	}
	/**
	 * This method helps simplify the process in which the computers play a certain move. 
	 */
	public void playScissor() {
		currentMove = new Scissor();
	}
	/**
	 * This method helps simplify the process in which the computers play a certain move. 
	 */
	public void playLizard() {
		currentMove = new Lizard();
	}
	/**
	 * This method helps simplify the process in which the computers play a certain move. 
	 */
	public void playSpock() {
		currentMove = new Spock();
	}
	
	
	
	/**
	 * This is called in RPSLKGame when the Computer wins a round.
	 */
	public void winRound() {
		roundsWon++;
	}
	/**
	 * Returns the number of Computer rounds won.
	 */
	public int getRoundsWon() {
		return roundsWon;
	}
	
	
	/**
	 * @return NAME of the computer
	 */
	public String getName() {
		return NAME;
	}
	/**
	 * This returns the computer is going to play in the current round.
	 */
	public MoveType getMove() {
		
		return currentMove;
	
	}
	
	public int getMoveCount(int whichMove) {
		return recordedMoves.getComputerMoveCount(whichMove);
	}
	

	
	
	

	
	
	private final String NAME = "Mr. Computer";
	private final Random RANDOM_GENERATOR = new Random();

	private GameHistory recordedMoves;
	private int roundsWon;
	private MoveType currentMove;
	/**
	 * All moves have a 20% chance of being played, and the reason I set some of them to 0.4, 0.6, 0.8, etc.
	 * is because the random number generator I use to throw a random move returns a number from 0 to 1. 
	 * So, depending on where it lands, each move has an equal chance at being thrown. 
	 */
	private double[] moveProbabilites = {0.20, 0.40, 0.60, 0.80, 1.00};


	

}
