/**
 * 
 * @author Marcus
 *
 * This class represents the Scissor class. See the javadoc written in MoveType for details. 
 */
public class Scissor extends MoveType {

	public Scissor() {
		NAME = "SCISSOR";
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
		return 2;
	}
	
	
	private final String NAME;
	private final String[] STRENGTHS = {"PAPER", "LIZARD"};
	private final String[] WEAKNESSES = {"ROCK", "SPOCK"};




}
