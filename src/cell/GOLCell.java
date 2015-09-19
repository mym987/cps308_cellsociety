package cell;

import location.Location;
import state.GOLState;
import state.State;
import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class GOLCell extends Cell{
	
	private static final int DEAD_STATE = 0;
	private static final int LIVE_STATE = 1;
	
	SquareCellGUI myCellGUI;

	public GOLCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		
		myCellGUI = new SquareCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
	}
	
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
	@Override
	public void goToNextState() {
		super.goToNextState();
		myCellGUI.updateState(myState);
	}
	
	public void remove() {
		myCellGUI.remove();
	}
}
