package cellsociety_team11;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class SegCell extends Cell {
	public static final double CELL_SIZE = 70;

	private static final int EMPTY_STATE = 0;
	private static final int BLUE_STATE = 1;
	private static final int YELLOW_STATE = 2;
	public static final double PERCENT_SIMILAR = 0.70; //set to what we want
	SquareCellGUI myCellGUI;

	SegCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);

		myCellGUI = new SquareCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
	}

	@Override
	public void determineNextState() {
	}
	
	public void determineNextState(State s){
		myState.setNextState(s.getStateInt());
	}

	public boolean isSatisfied() {
		if (getState().getStateInt() == EMPTY_STATE)
			return false;
		int otherState = BLUE_STATE + YELLOW_STATE - getState().getStateInt();
		int sameNeighborSize = getNeighborsInState(myState).size();
		int otherNeighborSize = getNeighborsInState(new SegState(otherState)).size();
		return ((double)sameNeighborSize)/(sameNeighborSize+otherNeighborSize)>=PERCENT_SIMILAR;
	}
	
	public boolean isEmpty() {
		return getState().getStateInt() == EMPTY_STATE;
	}

	public void remove() {
		myCellGUI.remove();
	}

	@Override
	public void goToNextState() {
		super.goToNextState();
		myCellGUI.updateState(myState);
	}

}
