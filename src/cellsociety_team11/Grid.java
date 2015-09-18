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
		for (Cell c : myCells.values()) {
			c.determineNextState();
		}
	}
	
	private void goToNextStates() {
		for (Cell c : myCells.values()) {
			c.goToNextState();
		}
	}
	
	private void removeCells() {
		for (Cell c : myCells.values()) {
			c.remove();
		}
	}

}
