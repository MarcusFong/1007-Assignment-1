/**
 * 
 * @author Marcus
 *
 * This class represents the Lizard class. See the javadoc written in MoveType for details. 
 */
public class Lizard extends MoveType{

	public Lizard() {
		
		NAME = "LIZARD";
		
	}
	
	public String getName() {
		return NAME;
	}

	public String[] getStrengths() {
		return STRENGTHS;
	}

	@Override
	public String[] getWeaknesses() {
		return WEAKNESSES;
	}
	
	public int getMoveIndex() {
		return 3;
	}

	private final String NAME;
	private final String[] STRENGTHS = {"PAPER","SPOCK"};
	private final String[] WEAKNESSES = {"SCISSOR","ROCK"};
	

}
