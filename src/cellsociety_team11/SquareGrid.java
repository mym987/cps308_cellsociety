package cellsociety_team11;

import java.util.ArrayList;

public class SquareGrid extends Grid {

	private double myWidth;
	private double myHeight;

	public static int myNumRows = 5; // hardcoded and public rn
	public static int myNumCols = 5; // hardcoded and public rn

	GOLCell[][] myGrid;

	public SquareGrid() {
		super();
		createGrid();
		myWidth = myNumCols * GOLCell.CELL_SIZE;
		myHeight = myNumRows * GOLCell.CELL_SIZE;
	}

	public int getNumRows() {
		return myNumRows;
	}

	public int getNumCols() {
		return myNumCols;
	}

	protected void createGrid() {

		Location loc = new Location(myHeight, myHeight);

		// if (myWidth != this.getWidth() || myHeight != this.getHeight()) {
		// this.setWidth(myWidth);
		// this.setHeight(myHeight);

		for (int x = 0; x < myNumRows; x++) {
			for (int y = 0; y < myNumCols; y++) {
				loc = new Location(x, y);
				Cell cell = new GOLCell(loc);
				myCells.add(cell);
			}
		}

		// }
	}

	public void setNeighbors() {

		for (GOLCell[] cellArray : myGrid) {
			for (GOLCell cell : cellArray) {
				ArrayList<GOLCell> neighbors = new ArrayList<GOLCell>();
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
