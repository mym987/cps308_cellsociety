package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public class GOLModel extends Model {

	private SquareGrid myGrid;
	
	GOLModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
	}
	
	public void step() {
		for(Cell cell: myCellMap.values()) {
			cell.determineNextState();
			cell.goToNextState();
		}
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells, CellSocietyGUI CSGUI) {
		myCellMap = new HashMap<>();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("X"));
			int y = Integer.parseInt(map.get("Y"));
			int state = Integer.parseInt(map.get("State"));
			Cell cell = new GOLCell(new GOLState(state), new Location(x,y, getRows(), getColumns()), CSGUI);
			myCellMap.put(cell.getLocation(), cell);
		});
		// Why locations are double??!!
			// Because that's what the methods to draw take
		myGrid = new SquareGrid(getColumns(), getRows(), myCellMap);
	}

	@Override
	public Grid getMyGrid() {
		return myGrid;
	}
}
