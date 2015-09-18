package cellsociety_team11;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class GOLCell extends Cell{
	public static final double CELL_SIZE = 70;
	
	private static final int DEAD_STATE = 0;
	private static final int LIVE_STATE = 1;
	
	SquareCellGUI myCellGUI;

	GOLCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		
		myCellGUI = new SquareCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
	}
	
	@Override
	public void determineNextState() { // This should be split up into smaller functions.
		GOLState nextState = new GOLState(1);
		int numLiveNeighbors = getNeighborsInState(nextState).size();
		
		if (numLiveNeighbors == 3) {
			myState.setNextState(LIVE_STATE);
		} else if(numLiveNeighbors != 2) {
			myState.setNextState(DEAD_STATE);
		}
	}
	
	public void goToNextState() {
		super.goToNextState();
		myCellGUI.updateState(myState);
	}
	
	public void remove() {
		myCellGUI.remove();
	}
}
