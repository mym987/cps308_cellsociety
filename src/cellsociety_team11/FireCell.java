package cellsociety_team11;

import gui.CellSocietyGUI;

public class FireCell extends Cell{
	public static final double CELL_SIZE = 70;

	private Integer[] myStateInts = {0,1,2}; 

	FireCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
	}

	@Override
	public void determineNextState() {
		// TODO Auto-generated method stub
	}

	@Override
	public void goToNextState() {
		// TODO Auto-generated method stub
		
	}

}
