/**
 * 
 * This class represents the simulated version of the Human class. It represents one of the two 
 * Players in the RPSLKGame. The way this class functions is similar to that of the Human class, 
 * but the way in which it throws its moves is quite different. The simulated human has 2 strategies (rotator
 * and reflector) and will do each for a repeatStrategy amount of times before switching the next. This continues until
 * the number of rounds elapsed equals totalNumberOfRounds. 
 * 
 * Rotator: This strategy chooses the SIM's moves by rotating along the list of possible moves (first rock, then
 * paper, then scissor, lizard, spock, rock, paper, etc.). It moves to next move on the list after the end of each round.
 * 
 * Reflector: This strategy chooses the SIM's moves by reflecting the move the Computer did the previous round. For example,
 * if the Computer played Spock, the SIM will play Spock this round, reflecting the move. 
 * 
 * 
 * I decided to make this class extend the Human class as many method calls in both GameHistory and RPSLKGame
 * are for type Human so this allows me to reuse code and methods. In addition, both of them have very similar
 * properties and both act as the "Human" in the game, except that this "Human" has automated moves. 
 * 
 * @author Marcus
 *
 */
public class SimulatedHuman extends Human{

	public SimulatedHuman(GameHistory inRecordedMoves, int inRepeatStrategy, int inTotalNumberOfRounds) {
		name = "The Sim";
		
		recordedMoves = inRecordedMoves;
		repeatStrategy = inRepeatStrategy;
		
		strategyUseCount = 0; //keeps track of the number of times the current strategy has been used before
							  //it swaps over
		totalNumberOfRounds = inTotalNumberOfRounds;
		
		onStrategyOne = true;
	}
	
	
	/**
	 * 
	 * This method plays helps the simulated human choose which of the two strategies to use and stores 
	 * the chose move for the current round. Each time a certain strategy is used the count of that
	 *  strategy is incremented by one. After a repeatStrategy amount of times, the SimulatedHuman
	 * swaps to the other strategy and uses it for repeatStrategy amount of times before swapping back over.
	 * 
	 */
	public void enterMove() {
		
		if (onStrategyOne) { //use strategy one (Rotator strategy)
			
			System.out.println("USING STRATEGY ONE!");
			
			currentMove = useRotatorStrategy();	
			strategyUseCount++;

		}
		
		else if (!onStrategyOne){ //use strategy two (Reflector strategy)
			
			System.out.println("USING STRATEGY TWO!");
			
			currentMove = useReflectorStrategy();
			strategyUseCount++;

		}
		
		if (strategyUseCount == repeatStrategy) { //after using a strategy for repeatStrategy amount of times, set 
												  //strategyUseCount back to 0 and swap to using the other strategy.
			
			onStrategyOne = !onStrategyOne; //swaps to the other strategy 
			strategyUseCount = 0;
		
		}
		 
		recordedMoves.addToHumanMoveHistory(currentMove); //save the move in the humanMoveHistory ArrayList.
		recordedMoves.incrementHumanMoveCount(currentMove.getMoveIndex()); //every move has an index that corresponds to its move
																		   //0 is for rock, 1 is for paper, 2 is for scissor, etc.
		
		numberOfRoundsElapsed++;
	}
	
	
	
	/**
	 * This function helps the SimualtedHuman choose a move based off the "Rotator" strategy. The Rotator strategy
	 * simply rotates along the 5 possible moves of this game, Rock then Paper then Scissor, Lizard, Spock, Rock,
	 * Paper, Scissor, etc. and plays each one, moving onto the next one in the list for the next round.
	 * 
	 * 
	 * @return variable MoveType that represents the Sim's next move based on the "Rotator" strategy.
	 */
	public MoveType useRotatorStrategy() {

		switch (rotaterIndex) {
		
		case 0:
			rotaterIndex++;
			return new Rock();
		
		case 1:
			rotaterIndex++;
			return new Paper();
			
		case 2:
			rotaterIndex++;
			return new Scissor();
			
		case 3:
			rotaterIndex++;
			return new Lizard();
			
		case 4:
			rotaterIndex = 0; //reset to 0 for next round so the strategy can continue "rotating" 
							  //through all possible moves. 
			return new Spock();
			
		default:
			return null;
		}
		
			
		
	}
	/**
	 * This method helps the SimulatedHuman choose a move based off the "Reflector" strategy. The reflector
	 * strategy simply "reflects" the move that was just played by the Computer in the previous round. For
	 * example, if the Computer just played Paper in the last round, this method will return Paper and will
	 * make the sim play Paper. The field recordedMoves stores the history of moves done by the Computer, so
	 * it uses that ArrayList to get the Computer's previous move to be reflected.
	 * 
	 * @return variable MoveType that represent's the Sim's next move based on the "Reflector" strategy.
	 */
	
	public MoveType useReflectorStrategy() {
		int size = recordedMoves.getComputerMoveHistory().size();
		
		MoveType computerPreviousMove = recordedMoves.getComputerMoveHistory().get(size-1);
		
		return computerPreviousMove; //the last index of the Computer's move history was its 
																   //previous move. 
		
	}
	
	/**
	 * @return the name of the SIM
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * this function is called everytime the SIM wins a round.
	 */
	public void winRound() {
		roundsWon++;
	}
	
	/**
	 * Returns the number of rounds won by the SIM.
	 */
	public int getRoundsWon() {
		return roundsWon;
	}
	
	/**
	 * Returns the currentMove the SIM is going to play.
	 */
	public MoveType getMove() {
		return currentMove;
	}
	
	/**
	 * @return the number of rounds the SIM is going to run for.
	 */
	public int getTotalNumOfRounds() {
		return totalNumberOfRounds;
	}
	
	/**
	 * @return the number of rounds elapsed so far
	 */
	public int getRoundCount() {
		return numberOfRoundsElapsed;
	}
	
	
	/**
	 * 
	 * 
	 * @param whichMove this parameter helps the compiler recognize which move count to return. 
	 * 0 == Rock count
	 * 1 == Paper count
	 * 2 == Scissor count
	 * 3 == Lizard count
	 * 4 == Spock count
	 */
	public int getMoveCount(int whichMove) {
		
		return recordedMoves.getHumanMoveCount(whichMove);


	}
	
	
	
	private String name;
	private int repeatStrategy;
	private GameHistory recordedMoves;
	private int roundsWon;
	private MoveType currentMove; //is either Rock, Paper, Scissor, Lizard, or Spock

	
	
	private int strategyUseCount;
	private boolean onStrategyOne;
	private int rotaterIndex;
	
	private int totalNumberOfRounds;
	private int numberOfRoundsElapsed;


}
