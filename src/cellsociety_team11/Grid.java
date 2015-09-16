package cellsociety_team11;

import java.util.Map;

public class Grid {

	private double myWidthPixels;  //could move later
	private double myHeightPixels;
	
	protected static int myNumCols;
	protected static int myNumRows;
	protected Map<Location, Cell> myCells;

//	//different model cells
//	private Cell[] myPossibleCells = { 
//			new GOLCell(state, loc);
//	};

	//	Group group = new Group();

	// private GridPane gp = new GridPane();
	
	Grid(int c, int r, Map<Location, Cell> cells){
		myNumCols = c;
		myNumRows = r;
		myCells = cells;
		myWidthPixels = myNumCols * GOLCell.CELL_SIZE;
		myHeightPixels = myNumRows * GOLCell.CELL_SIZE;
	}
	
	public static int getNumRows() {
		return myNumRows;
	}

	public static int getNumCols() {
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
