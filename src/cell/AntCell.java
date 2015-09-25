package cell;

import java.util.Map;

import gui.CellSocietyGUI;
import location.Location;
import state.State;

public class AntCell extends AbstractCell{
	private static final int CAPACITY = 10;
	
	private static final int EMPTY_STATE = 0;
	private static final int ANT_STATE = 1;
	private static final int FOOD_STATE = 2;
	private static final int NUM_STATES = 3;
	
	private int myFoodPheromone;
	private int myHomePheromone;

	AntCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, NUM_STATES, l, CSGUI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void determineNextState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

}
