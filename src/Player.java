/**
 * 
 * @author Marcus
 *
 * I chose to create this abstract class to help encapsulate all three potential Players (Human, Computer, and
 * SimulatedHuman) in the RPSLK game. Although the way in which they throw their moves are different, they share other
 * abilities such as winning a round or returning their current moven they chose to play.  
 *
 */
public abstract class Player {
	
	public Player() {
		
		
	}
	
	public abstract String getName();
	public abstract void winRound();
	public abstract int getRoundsWon();
	public abstract MoveType getMove();
	
	public abstract int getMoveCount(int whichMove);

	
}
