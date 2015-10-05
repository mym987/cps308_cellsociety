package cell;

import location.Location;
import state.GOLState;
import state.State;

import gui.CellGUI;
import gui.CSViewer;

public class GOLCell extends Cell{
	
	private static final int DEAD_STATE = 0;
	private static final int LIVE_STATE = 1;
	private static final int NUM_STATES = 2;

	public GOLCell(State s, Location l, CSViewer CSGUI) {
		super(s, NUM_STATES, l, CSGUI);
		
		myCellGUI = CellGUI.makeCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
		addClickListener();
	}
	
	/**
	 * Determine the next state for the cell to go to
	 */
	@Override
	public void determineNextState() { // This should be split up into smaller functions.
		int numLiveNeighbors = getNeighborsInState(new GOLState(LIVE_STATE)).size();
		if(isInState(LIVE_STATE)){	
			if(numLiveNeighbors == 2 || numLiveNeighbors == 3){
				myState.setNextState(LIVE_STATE);
			}else{
				myState.setNextState(DEAD_STATE);
			}
		}else if(isInState(DEAD_STATE)){
			if (numLiveNeighbors == 3)
				myState.setNextState(LIVE_STATE);
		}
	}
}
