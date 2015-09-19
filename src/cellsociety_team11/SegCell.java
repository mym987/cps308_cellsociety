package cellsociety_team11;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;
import javafx.scene.paint.Color;

public class SegCell extends Cell {
	public static final double CELL_SIZE = 70;
	
	private static final int EMPTY_STATE = 0;
	private static final int BLUE_STATE = 1;
	private static final int YELLOW_STATE = 2;
	public static final double PERCENT_SIMILAR = 0.30;   //set to what we want
	SquareCellGUI myCellGUI;
	
	private Integer[] myStateInts = {0,1,2}; 

	SegCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);

		myCellGUI = new SquareCellGUI(CSGUI, l);

	}

	@Override
	public void determineNextState() {
		double percentSimilar = getNeighborsInState(myState).size()/8;
		if (percentSimilar < PERCENT_SIMILAR) {
			myState.setNextState(EMPTY_STATE);
			getRandomEmpty().getState().setNextState(myState.getStateInt());
		}
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
