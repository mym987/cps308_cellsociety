package cellsociety_team11;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;
import javafx.scene.paint.Color;

public class SegCell extends Cell {
	public static final double CELL_SIZE = 70;
	SquareCellGUI myCellGUI;
	
	private Integer[] myStateInts = {0,1,2}; 

	SegCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);

		myCellGUI = new SquareCellGUI(CSGUI, l);

	}

	@Override
	public void determineNextState() {
		// TODO Auto-generated method stub
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
