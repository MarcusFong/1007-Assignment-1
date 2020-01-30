/**
 * 
 * @author Marcus
 *
 * This class represents the Rock class. See the javadoc written in MoveType for details. 
 */
public class Rock extends MoveType {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
	public String[] getStrengths() {
		return STRENGTHS;
	}
	
	
	public String[] getWeaknesses() {
		return WEAKNESSES;
	}
	
	public int getMoveIndex() {
		return 0;
	}
	
	private String name = "ROCK";
	private String[] STRENGTHS = {"SCISSOR","LIZARD"};
	private String[] WEAKNESSES = {"PAPER", "SPOCK"};


	
}
