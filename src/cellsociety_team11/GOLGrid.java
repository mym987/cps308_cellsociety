package cellsociety_team11;

import java.util.Map;

public class GOLGrid extends Grid{
	
	GOLGrid(int c, int r, Map<Location, Cell> cells) {
		super(c, r, cells);
	}
	
	public static GOLCell getCell(Location l){
		return (GOLCell) myCells.get(l);
	}

}
