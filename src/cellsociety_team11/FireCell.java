package cellsociety_team11;

public class FireCell extends Cell{
	public static final double CELL_SIZE = 70;

	private Integer[] myStateInts = {0,1,2}; 

	FireCell(State s, Location l) {
		super(s, l);
	}

	@Override
	public State determineNextState() {
		// TODO Auto-generated method stub
		return null;
	}

}
