/**
 * 
 * @author Marcus
 *
 * I chose to represent each possible move under this MoveType class as all of them share the same traits but 
 * with different parameters. For example, all moves have moves that it can beat and moves that it will lose to.
 * By storing their strengths and weaknesses in one class, checking for the winner of each potential combination of 
 * moves is much more simpler. 
 */
public abstract class MoveType {	
	
	public MoveType() {
		
	}
	
	public abstract String getName();
	/**
	 * 
	 * @return the Strings of the moves that this move will beat.
	 */
	public abstract String[] getStrengths();
	/**
	 * 
	 * @return the Strings of the moves that this move will lose to. 
	 */
	public abstract String[] getWeaknesses();
	/**
	 * I often represent each move in an array where the order is always Rock, Paper, Scissor, Lizard, and Spock.
	 * So, to simplify code and to enhance clarity, I assigned an index to each MoveType. 
	 * E.G; 0 to Rock, 1 to Paper, 2 to Scissor, etc.
	 * 
	 * @return the index that corresponds to the move.
	 */
	public abstract int getMoveIndex();
	

}
