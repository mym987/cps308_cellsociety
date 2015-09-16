package cellsociety_team11;

import java.util.Map;

public class PredGrid extends Grid{

	PredGrid(int c, int r, Map<Location, Cell> cells) {
		super(c, r, cells);
	}

	public static PredCell getCell(Location l){
		return (PredCell) myCells.get(l);
	}
}
