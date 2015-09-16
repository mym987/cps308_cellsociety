package cellsociety_team11;

import java.util.ArrayList;
import java.util.Map;

public class SquareGrid extends Grid {

	SquareGrid(int c, int r, Map<Location, Cell> cells) {
		super(c, r, cells);
	}
//	private double myWidthPixels;  //could move later
//	private double myHeightPixels;
//
//	SquareGrid(int c, int r, Map<Location, Cell> cells) {
//		super(c, r, cells);
//		//createGrid();
//		myWidthPixels = myNumCols * GOLCell.CELL_SIZE;
//		myHeightPixels = myNumRows * GOLCell.CELL_SIZE;
//	}
//	
//
//	public void setNeighbors() {
//		
//		for (Location l : myCells.keySet()) {
//			Cell cell = myCells.get(l);
//			ArrayList<Cell> neighbors = new ArrayList<Cell>();
//			for (int i = -1; i < 2; i++) {
//				for (int j = -1; j < 2; j++) {
//					if (i != 0 || j != 0) {
//						Location neighborLoc = new Location(cell.getLocation().getX() + i,
//								cell.getLocation().getY() + j);
//						if (neighborLoc.isValid()) {
//							neighbors.add(cell);
//						}
//					}
//				}
//			}
//			cell.setNeighborCells(neighbors);
//		}
//			
//	}
}
