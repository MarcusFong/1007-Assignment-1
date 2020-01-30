/**
 * 
 * @author Marcus
 *
 * This class represents a real human user of the game RPSLK. Instantiating this class into an 
 * object would allow the user to take in input from the console to play a move in  the game RPSLK. 
 * In doing this, his or her score and the moves he or she throws is recorded. 
 */
public class Human extends Player{
	
	public Human() {
		
	}
	
	public Human (String inName, GameHistory inRecordedMoves) {
		name = inName;
		recordedMoves = inRecordedMoves;
	}
	
	
	
	/**
	 * This method plays a MoveType for the current round for the Player and records it in GameHistory. 
	 * See below for the key regarding how each character represents each MoveType. 
	 * 
	 * 'r': Rock
	 * 'p': Paper
	 * 's': Scissor
	 * 'l': Lizard
	 * 'k': Spock
	 * @param inMove is the parameter that checks with move the Human wants to play depending 
	 * on what he or she inputted (either r, p, s, l, or k).
	 */
	public void enterMove(String inMove) {
		
		if (inMove.equals("r")) {// play Rock
			currentMove = new Rock();
			recordedMoves.incrementHumanMoveCount(0);
		}
		else if (inMove.equals("p")) {//play Paper
			currentMove = new Paper();
			recordedMoves.incrementHumanMoveCount(1);
		}
		else if (inMove.equals("s")) {// play Scissor
			currentMove = new Scissor();
			recordedMoves.incrementHumanMoveCount(2);
		}
		else if (inMove.equals("l")) {//play Lizard
			currentMove= new Lizard();
			recordedMoves.incrementHumanMoveCount(3);
		}
		else if (inMove.equals("k")) {//play Spock
			currentMove = new Spock();
			recordedMoves.incrementHumanMoveCount(4);
		}
		
		recordedMoves.addToHumanMoveHistory(currentMove);
		
	}
	/**
	 * This method is placed here for the SimulatedHuman class so that when enterMove() is called 
	 * for an object that is instantiated with a SimulatedHuman constructor, the SimulatedHuman's enterMove()
	 * method is called instead. This method WON'T be used by an object instantiated with a Human constructor. 
	 */
	public void enterMove() {}
	public int getTotalNumOfRounds() {return -1;}

	
	
	
	
	
	public void winRound() {
		roundsWon++;
	}
	public int getRoundsWon() {
		return roundsWon;
	}
	
	public MoveType getMove() {
		return currentMove;
	}
	
	/**
	 * This method returns the number of times a specific move has been played by the Human. Because the 
	 * number of times each move is played is stored in an array for each move in the GameHistory class, 
	 * the parameter whichMove of type int represents the index of each respective move count. 
	 * 
	 * 0 == Rock count
	 * 1 == Paper count
	 * 2 == Scissor count
	 * 3 == Lizard count
	 * 4 == Spock count
	 * 
	 * @return the number of times a specific move has been played by the Human.
	 */
	public int getMoveCount(int whichMove) {
		return recordedMoves.getHumanMoveCount(whichMove);
	}

	
	public String getName() {
		return name;
	}
	 
	
	
	private String name;
	private int roundsWon;
	private MoveType currentMove; //is either Rock, Paper, Scissor, Lizard, or Spock
	private GameHistory recordedMoves;

	



}
