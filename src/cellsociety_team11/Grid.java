package cellsociety_team11;

import java.util.Map;

public class Grid {
	
	protected int myNumCols;
	protected int myNumRows;
	protected Map<Location, Cell> myCells;
	
	Grid(int c, int r, Map<Location, Cell> cells){
		myNumCols = c;
		myNumRows = r;
		myCells = cells;
	}
	
	public int getNumRows() {
		return myNumRows;
	}

	public int getNumCols() {
		return myNumCols;
	}
	
	public void step() {
		determineNextStates();
		goToNextStates();
	}
	
	private void determineNextStates() {
		for (Location l : myCells.keySet()) {
			Cell c = myCells.get(l);
			c.determineNextState();
		}
	}
	
	private void goToNextStates() {
		for(Location l : myCells.keySet()) {
			Cell c = myCells.get(l);
			c.goToNextState();
		}
	}

}
