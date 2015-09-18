package cellsociety_team11;

import java.util.ArrayList;
import java.util.Map;

public class SquareGrid extends Grid {

	SquareGrid(int c, int r, Map<Location, Cell> cells) {
		super(c, r, cells);
	}

	public void setNeighbors() {

		for (Cell cell : myCells.values()) {
			ArrayList<Cell> neighbors = new ArrayList<Cell>();
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (i != 0 || j != 0) {
						Location neighborLoc = new Location(cell.getLocation().getX() + i,
								cell.getLocation().getY() + j, myNumRows, myNumCols);
						if (neighborLoc.isValid()) {
							Cell newcell = myCells.get(neighborLoc);
							if(newcell == null) {
								System.out.println("fuck");
							}
							neighbors.add(newcell);
						}
					}
				}
			}
			cell.setNeighborCells(neighbors);
		}

	}
}
