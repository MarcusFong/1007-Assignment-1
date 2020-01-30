import java.util.ArrayList;

/**
 * 
 * This class is used to store the history of moves for both the Human and Computer Player types. Below
 * are a number of helper methods that the Human, Computer, SimulatedHuman call to help with their programs. 
 * In addition to storing the history of moves for both Player types, this class also stores the number of times 
 * each move was played by each player. For example, when the Human plays Rock and the Computer plays Paper,
 * the each move is stored in its Player's respective history and the number of times each move was played 
 * is as well. I chose to represent both Player's move's history in one class because the same instantiation of this class
 * is stored in both Players, and I believe it would make accessing either its own or the other Player's history 
 * much more easy. 
 * 
 * 
 * Below you will notice that the history of moves is stored as an ArrayList as opposed to a different
 * data structure. I chose to store the history of moves for each Player as an ArrayList as for 
 * some of the strategies the SimulatedHuman and Computer use require them to retrieve elements not only
 * from the back of the array. In addition, the size, or number of rounds the game will run, is undetermined 
 * when playing vs a Human so this data structure would need to be able to change size easily (despite the rip).
 * 
 * On the other hand, I chose to store the count of moves for each Player as an int array because 1) accessing
 * each element of the array wouldn't require as much hassle and 2) there are a finite number of moves. 
 * 
 * @author Marcus
 *
 */
public class GameHistory {
	
	public GameHistory() {
		
	}
	
	/**
	 * This method allows a Human object add a move to its record of moves.
	 * @param inMove The MoveType that will be added.
	 */
	public void addToHumanMoveHistory(MoveType inMove) {
		humanMoveHistory.add(inMove);
	}
	
	/**
	 * This method allows a Computer object add a move to its record of moves.
	 * @param inMove The MoveType that will be added.
	 */
	public void addToComputerMoveHistory(MoveType inMove) {
		computerMoveHistory.add(inMove);
	}
	
	/**
	 * 
	 * @return humanMoveHistory an ArrayList of all recorded moves done by the Human.
	 */
	public ArrayList<MoveType> getHumanMoveHistory(){
		return humanMoveHistory;
	}
	/**
	 * 
	 * @return an ArrayList of all recorded moves done by the Computer.
	 */
	public ArrayList<MoveType> getComputerMoveHistory(){
		return computerMoveHistory;
	}
	/**
	 * 
	 * @return an array of the counts of each move from the Human.
	 */
	public int[] getHumanMoveCounts() {
		return humanMoveCounts;
	}
	/**
	 * 
	 * @return an array of the counts of each move from the Human.
	 */
	public int[] getComputerMoveCounts() {
		return computerMoveCounts;
	}

	/**
	 * 
	 * @param whichMove is an int and it represents a move.
	 * @return the number of times a human played a certain move.
	 */
	public int getHumanMoveCount(int whichMove) {
		return humanMoveCounts[whichMove];
	}
	/**
	 * 
	 * @param whichMove is an int and it represents a move.
	 * @return the number of times a computer played a certain move.
	 */
	public int getComputerMoveCount(int whichMove) {
		return computerMoveCounts[whichMove];
	}
	
	
	
	/**
	 * When the Human plays a move of type MoveType, the count of that respective move will be incremented
	 * though this method below. 
	 * @param whichMove this parameter represents which move count will be incremented. 
	 */
	public void incrementHumanMoveCount(int whichMove) {
		humanMoveCounts[whichMove]++;
	}
	
	/**
	 * When the Computer plays a move of type MoveType, the count of that respective move will be incremented
	 * though this method below. 
	 * @param whichMove this parameter represents which move count will be incremented. 
	 */
	public void incrementComputerMoveCount(int whichMove) {
		computerMoveCounts[whichMove]++;
	}
	
	/**
	 * 
	 * @return the number of rounds elapsed. This returns the size of the computerMoveHistory because its size
	 * matches the number of rounds that have elapsed.
	 */
	public int getRoundsElapsed() {
		return computerMoveHistory.size();
	}

	
	
	private ArrayList<MoveType> humanMoveHistory = new ArrayList<MoveType>();
	private ArrayList<MoveType> computerMoveHistory = new ArrayList<MoveType>();
		
	private int[] computerMoveCounts = new int[5];//index 0 is for Rock, 1 for Paper, 2 for Scissor, 3 for Lizard, 4 for Spock
	
	private int[] humanMoveCounts = new int[5];//index 0 is for Rock, 1 for Paper, 2 for Scissor, 3 for Lizard, 4 for Spock



}
