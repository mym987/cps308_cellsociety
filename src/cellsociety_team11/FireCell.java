package cellsociety_team11;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class FireCell extends Cell{
	public static final double CELL_SIZE = 70;

	private Integer[] myStateInts = {0,1,2};
	SquareCellGUI myCellGUI;

	FireCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		myCellGUI = new SquareCellGUI(CSGUI, l);
	}

	@Override
	public void determineNextState() {
		// TODO Auto-generated method stub
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
