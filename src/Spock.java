/**
 * 
 * @author Marcus
 *
 * This class represents the Spock class. See the javadoc written in MoveType for details. 
 */
public class Spock extends MoveType {

	public Spock() {
		NAME = "SPOCK";
	}
	
	public String getName() {
		return NAME;
	}

	public String[] getStrengths() {
		return STRENGTHS;
	}

	public String[] getWeaknesses() {
		return WEAKNESSES;
	}
	
	public int getMoveIndex() {
		return 4;
	}
	
	
	private final String NAME;
	private final String[] STRENGTHS = {"ROCK", "SCISSOR"};
	private final String[] WEAKNESSES = {"LIZARD", "PAPER"};



}
