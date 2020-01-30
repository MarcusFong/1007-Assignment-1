/**
 * 
 * @author Marcus
 *
 * This class represents the Paper class. See the javadoc written in MoveType for details. 
 */
public class Paper extends MoveType{

	@Override
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
		return 1;
	}

	
	private final String NAME = "PAPER";
	private final String[] STRENGTHS = {"ROCK","SPOCK"};
	private final String[] WEAKNESSES = {"SCISSOR","LIZARD"};
	


}
