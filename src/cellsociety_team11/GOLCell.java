package cellsociety_team11;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class GOLCell extends Cell{
	public static final double CELL_SIZE = 70;
	
	private Integer[] myStateInts = {0,1};  //0=dead, 1=live 
											// Maybe this should be an ENUM to make it more readable?
	
	SquareCellGUI myCellGUI;

	GOLCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		
		myCellGUI = new SquareCellGUI(CSGUI, l);
	}
	
	@Override
	public void determineNextState() { // This should be split up into smaller functions.
		GOLState nextState = new GOLState(0);
		int numLiveNeighbors = getNeighborsInState(nextState).size();
		
		if(numLiveNeighbors > 3 || numLiveNeighbors < 2) {
			myState.setNextState(myStateInts[0]);
		} else if (numLiveNeighbors == 3) {
			myState.setNextState(myStateInts[1]);
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
