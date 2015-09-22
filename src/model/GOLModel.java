package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import location.Location;
import state.GOLState;
import cell.Cell;
import cell.GOLCell;
import grid.SquareGrid;
import grid.TriangleGrid;
import gui.CellSocietyGUI;

public class GOLModel extends Model {
	
	private Map<Location,Cell> myCells;
	
	public GOLModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
		myCells = new HashMap<>();
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells, CellSocietyGUI CSGUI) {
		myCells.clear();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			Cell cell = new GOLCell(new GOLState(state), new Location(x,y, getWidth(), getHeight()), CSGUI);
			myCells.put(cell.getLocation(), cell);
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		TriangleGrid grid = new TriangleGrid(getWidth(), getHeight(), myCells);
		grid.setNeighbors();
		setMyGrid(grid);
	}
}
