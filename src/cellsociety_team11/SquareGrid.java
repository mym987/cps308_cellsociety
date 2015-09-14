package cellsociety_team11;

import java.util.ArrayList;
import java.util.Map;

public class SquareGrid extends Grid {

	private double myWidthPixels;  //could move later
	private double myHeightPixels;

	Cell[][] myGrid;


	SquareGrid(int c, int r, Map<Location, Cell> cells) {
		super(c, r, cells);
		//createGrid();
		myWidthPixels = myNumCols * GOLCell.CELL_SIZE;
		myHeightPixels = myNumRows * GOLCell.CELL_SIZE;
	}

//	protected void createGrid() {
//
//		Location loc = new Location(myNumCols, myNumRows);
//
//		for (int x = 0; x < myNumRows; x++) {
//			for (int y = 0; y < myNumCols; y++) {
//				loc = new Location(x, y);
//				Cell cell = new GOLCell(loc);
//				myCells.put(loc, cell);
//			}
//		}
//
//	}

	public void setNeighbors() {

		for (Cell[] cellArray : myGrid) {
			for (Cell cell : cellArray) {
				ArrayList<Cell> neighbors = new ArrayList<Cell>();
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (i != 0 || j != 0) {
							Location neighborLoc = new Location(cell.getLocation().getX() + i,
									cell.getLocation().getY() + j);
							if (neighborLoc.isValid()) {
								neighbors.add(myGrid[i][j]);
							}
						}
					}
				}
				cell.setNeighborCells(neighbors);
			}
		}
	}
}
