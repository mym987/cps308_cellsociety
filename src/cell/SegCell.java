/**
 * @author Mike Ma
 */
package cell;

import location.Location;
import state.SegState;
import state.State;

import gui.CellGUI;
import gui.CSViewer;

public class SegCell extends Cell {
	public static final double CELL_SIZE = 70;

	private static final int EMPTY_STATE = 0;
	private static final int BLUE_STATE = 1;
	private static final int YELLOW_STATE = 2;
	private static final int NUM_STATES = 3;
	
	private double mySimilarity = 0.50;

	public SegCell(State s, Location l, CSViewer CSGUI) {
		super(s, NUM_STATES, l, CSGUI);

		myCellGUI = CellGUI.makeCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
		addClickListener();
	}
	
	public void setSimilarity(double similarity) {
		mySimilarity = similarity;
	}

        /**
         * Determine the next state for the cell to go to
         */
	@Override
	public void determineNextState() {
	}
	
        /**
         * Determine the next state for the cell to go to
         */
	public void determineNextState(State s){
		myState.setNextState(s.getStateInt());
	}

	public boolean isSatisfied() {
		if (getState().getStateInt() == EMPTY_STATE)
			return false;
		int otherState = BLUE_STATE + YELLOW_STATE - getState().getStateInt();
		int sameNeighborSize = getNeighborsInState(myState).size();
		int otherNeighborSize = getNeighborsInState(new SegState(otherState)).size();
		return ((double)sameNeighborSize)/(sameNeighborSize+otherNeighborSize)>=mySimilarity;
	}
	
	public boolean isEmpty() {
		return getState().getStateInt() == EMPTY_STATE;
	}

}
